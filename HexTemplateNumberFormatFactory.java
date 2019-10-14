package basictemplate;
import java.util.Locale;

import freemarker.core.Environment;
import freemarker.core.InvalidFormatParametersException;
import freemarker.core.TemplateFormatUtil;
import freemarker.core.TemplateNumberFormat;
import freemarker.core.TemplateNumberFormatFactory;
import freemarker.core.UnformattableValueException;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateNumberModel;
import freemarker.template.utility.NumberUtil;

public class HexTemplateNumberFormatFactory extends TemplateNumberFormatFactory {

    public static final HexTemplateNumberFormatFactory INSTANCE
            = new HexTemplateNumberFormatFactory();

    private HexTemplateNumberFormatFactory() {
        // Defined to decrease visibility
    }

    @Override
    public TemplateNumberFormat get(String params, Locale locale, Environment env)
            throws InvalidFormatParametersException {
        TemplateFormatUtil.checkHasNoParameters(params);
        return HexTemplateNumberFormat.INSTANCE;
    }

    private static class HexTemplateNumberFormat extends TemplateNumberFormat {

        private static final HexTemplateNumberFormat INSTANCE = new HexTemplateNumberFormat();

        private HexTemplateNumberFormat() { }

        @Override
        public String formatToPlainText(TemplateNumberModel numberModel)
                throws UnformattableValueException, TemplateModelException {
            Number n = TemplateFormatUtil.getNonNullNumber(numberModel);
            try {
                return Integer.toHexString(NumberUtil.toIntExact(n));
            } catch (ArithmeticException e) {
                throw new UnformattableValueException(n + " doesn't fit into an int");
            }
        }

        @Override
        public boolean isLocaleBound() {
            return false;
        }

        @Override
        public String getDescription() {
            return "hexadecimal int";
        }

    }

}