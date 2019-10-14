package basictemplate;
import freemarker.core.TemplateDateFormatFactory;
import freemarker.core.TemplateNumberFormatFactory;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class CustomDateAndTime17 {

    public static void main(String[] args) throws IOException,
            TemplateException {

        Configuration cfg = new Configuration(new Version("2.3.29"));
        
        Map<String, TemplateDateFormatFactory> customDateFormats = new HashMap<>();
        
        customDateFormats.put("epoch", EpochMillisTemplateDateFormatFactory.INSTANCE);
        
        
        cfg.setCustomDateFormats(customDateFormats);
        
        //below line can be given to set it as default format instead of declaring in template
        //if you define here no need to mention in template.
       //cfg.setDateTimeFormat("@epoch");
   
        
        cfg.setClassForTemplateLoading(CustomDateAndTime17.class, "/");
        cfg.setDefaultEncoding("UTF-8");

        Template template = cfg.getTemplate("basictemplate/templates/test10.ftl");

        Map<String, Object> templateData = new HashMap<>();
        templateData.put("msg", "Today is a beautiful day");

        try (StringWriter out = new StringWriter()) {
            
            template.process(templateData, out);
            System.out.println(out.getBuffer().toString());

            out.flush();
        }
    }
}
