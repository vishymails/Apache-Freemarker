package basictemplate;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import freemarker.core.Environment;
import freemarker.core.InvalidFormatParametersException;
import freemarker.core.TemplateDateFormat;
import freemarker.core.TemplateDateFormatFactory;
import freemarker.core.TemplateFormatUtil;
import freemarker.core.UnformattableValueException;
import freemarker.core.UnparsableValueException;
import freemarker.template.TemplateDateModel;
import freemarker.template.TemplateModelException;

public class EpochMillisTemplateDateFormatFactory extends TemplateDateFormatFactory {

    public static final EpochMillisTemplateDateFormatFactory INSTANCE
            = new EpochMillisTemplateDateFormatFactory();

    private EpochMillisTemplateDateFormatFactory() {
        // Defined to decrease visibility
    }

    @Override
    public TemplateDateFormat get(String params, int dateType,
            Locale locale, TimeZone timeZone, boolean zonelessInput,
            Environment env)
            throws InvalidFormatParametersException {
        TemplateFormatUtil.checkHasNoParameters(params);
        return EpochMillisTemplateDateFormat.INSTANCE;
    }

    private static class EpochMillisTemplateDateFormat extends TemplateDateFormat {

        private static final EpochMillisTemplateDateFormat INSTANCE
                = new EpochMillisTemplateDateFormat();

        private EpochMillisTemplateDateFormat() { }

        @Override
        public String formatToPlainText(TemplateDateModel dateModel)
                throws UnformattableValueException, TemplateModelException {
            return String.valueOf(TemplateFormatUtil.getNonNullDate(dateModel).getTime());
        }

        @Override
        public boolean isLocaleBound() {
            return false;
        }

        @Override
        public boolean isTimeZoneBound() {
            return false;
        }

        @Override
        public Date parse(String s, int dateType) throws UnparsableValueException {
            try {
                return new Date(Long.parseLong(s));
            } catch (NumberFormatException e) {
                throw new UnparsableValueException("Malformed long");
            }
        }

        @Override
        public String getDescription() {
            return "millis since the epoch";
        }

    }

}