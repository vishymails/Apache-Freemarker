package basictemplate;
import freemarker.core.TemplateNumberFormatFactory;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class CustomDateAndTime15 {

    public static void main(String[] args) throws IOException,
            TemplateException {

        Configuration cfg = new Configuration(new Version("2.3.29"));
        
        Map<String, TemplateNumberFormatFactory> customNumberFormats = new HashMap<>();
        
        customNumberFormats.put("hex", HexTemplateNumberFormatFactory.INSTANCE);
        
        
        cfg.setCustomNumberFormats(customNumberFormats);
        
        //below line can be given to set it as default format instead of declaring in template
        //if you define here no need to mention in template.
       //cfg.setNumberFormat("@hex");
        
        cfg.setClassForTemplateLoading(CustomDateAndTime15.class, "/");
        cfg.setDefaultEncoding("UTF-8");

        Template template = cfg.getTemplate("basictemplate/templates/test8.ftl");

        Map<String, Object> templateData = new HashMap<>();
        templateData.put("msg", "Today is a beautiful day");

        try (StringWriter out = new StringWriter()) {
            
            template.process(templateData, out);
            System.out.println(out.getBuffer().toString());

            out.flush();
        }
    }
}
