package basictemplate;
import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.Map;

import freemarker.core.Environment;
import freemarker.template.SimpleNumber;
import freemarker.template.TemplateBooleanModel;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateNumberModel;

/**
 * FreeMarker user-defined directive for repeating a section of a template,
 * optionally with separating the output of the repetations with
 * <tt>&lt;hr></tt>-s.
 *
 *
 * <p><b>Directive info</b></p>
 *
 * <p>Parameters:
 * <ul>
 *   <li><code>count</code>: The number of repetations. Required!
 *       Must be a non-negative number. If it is not a whole number then it will
 *       be rounded <em>down</em>.
 *   <li><code>hr</code>: Tells if a HTML "hr" element could be printed between
 *       repetations. Boolean. Optional, defaults to <code>false</code>.
 * </ul>
 *
 * <p>Loop variables: One, optional. It gives the number of the current
 *    repetation, starting from 1.
 *
 * <p>Nested content: Yes
 */
public class RepeatDirective implements TemplateDirectiveModel {

    private static final String PARAM_NAME_COUNT = "count";
    private static final String PARAM_NAME_HR = "hr";

    public void execute(Environment env,
            Map params, TemplateModel[] loopVars,
            TemplateDirectiveBody body)
            throws TemplateException, IOException {

        // ---------------------------------------------------------------------
        // Processing the parameters:

        int countParam = 0;
        boolean countParamSet = false;
        boolean hrParam = false;

        Iterator paramIter = params.entrySet().iterator();
        while (paramIter.hasNext()) {
            Map.Entry ent = (Map.Entry) paramIter.next();

            String paramName = (String) ent.getKey();
            TemplateModel paramValue = (TemplateModel) ent.getValue();

            if (paramName.equals(PARAM_NAME_COUNT)) {
                if (!(paramValue instanceof TemplateNumberModel)) {
                    throw new TemplateModelException(
                            "The \"" + PARAM_NAME_HR + "\" parameter "
                            + "must be a number.");
                }
                countParam = ((TemplateNumberModel) paramValue)
                        .getAsNumber().intValue();
                countParamSet = true;
                if (countParam < 0) {
                    throw new TemplateModelException(
                            "The \"" + PARAM_NAME_HR + "\" parameter "
                            + "can't be negative.");
                }
            } else if (paramName.equals(PARAM_NAME_HR)) {
                if (!(paramValue instanceof TemplateBooleanModel)) {
                    throw new TemplateModelException(
                            "The \"" + PARAM_NAME_HR + "\" parameter "
                            + "must be a boolean.");
                }
                hrParam = ((TemplateBooleanModel) paramValue)
                        .getAsBoolean();
            } else {
                throw new TemplateModelException(
                        "Unsupported parameter: " + paramName);
            }
        }
        if (!countParamSet) {
                throw new TemplateModelException(
                        "The required \"" + PARAM_NAME_COUNT + "\" paramter"
                        + "is missing.");
        }

        if (loopVars.length > 1) {
                throw new TemplateModelException(
                        "At most one loop variable is allowed.");
        }

        // Yeah, it was long and boring...

        // ---------------------------------------------------------------------
        // Do the actual directive execution:

        Writer out = env.getOut();
        if (body != null) {
            for (int i = 0; i < countParam; i++) {
                // Prints a <hr> between all repetations if the "hr" parameter
                // was true:
                if (hrParam && i != 0) {
                    out.write("<hr>");
                }

                // Set the loop variable, if there is one:
                if (loopVars.length > 0) {
                    loopVars[0] = new SimpleNumber(i + 1);
                }

                // Executes the nested body (same as <#nested> in FTL). In this
                // case we don't provide a special writer as the parameter:
                body.render(env.getOut());
            }
        }
    }

}