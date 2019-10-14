package basictemplate;
import freemarker.cache.ConditionalTemplateConfigurationFactory;
import freemarker.cache.FileExtensionMatcher;
import freemarker.core.TemplateConfiguration;
import freemarker.core.XMLOutputFormat;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class TemplateConfigExample13 {

    public static void main(String[] args) throws IOException,
            TemplateException {

        Configuration cfg = new Configuration(new Version("2.3.29"));

        cfg.setClassForTemplateLoading(TemplateConfigExample13.class, "/");
        cfg.setDefaultEncoding("UTF-8");
        
        
        
        TemplateConfiguration tcUTF8XML = new TemplateConfiguration();
        tcUTF8XML.setEncoding("utf-8");
        tcUTF8XML.setOutputFormat(XMLOutputFormat.INSTANCE);
		
		cfg.setTemplateConfigurations(
        new ConditionalTemplateConfigurationFactory(
                new FileExtensionMatcher("xml"),
                tcUTF8XML));

        /*
         SAME CAN BE OBTAINED VIA PROPERTIES FILE 
         
          templateConfigurations = \
    ConditionalTemplateConfigurationFactory( \
        FileExtensionMatcher("xml"), \
        TemplateConfiguration( \
            encoding = "utf-8", \
            outputFormat = XMLOutputFormat() \
        ) \
    )
		*/
        cfg.setTemplateExceptionHandler(new MyTemplateExceptionHandler());

        Template template = cfg.getTemplate("basictemplate/templates/test7.ftl");

        Map<String, Object> templateData = new HashMap<>();
        templateData.put("msg", "Today is a beautiful day");

        try (StringWriter out = new StringWriter()) {
            
            template.process(templateData, out);
            System.out.println(out.getBuffer().toString());

            out.flush();
        }
    }
}
