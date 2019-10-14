package basictemplate;
import freemarker.cache.ConditionalTemplateConfigurationFactory;
import freemarker.cache.FileExtensionMatcher;
import freemarker.cache.FileNameGlobMatcher;
import freemarker.cache.FirstMatchTemplateConfigurationFactory;
import freemarker.cache.OrMatcher;
import freemarker.cache.PathGlobMatcher;
import freemarker.core.HTMLOutputFormat;
import freemarker.core.RTFOutputFormat;
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

public class TemplateConfigExample14 {

    public static void main(String[] args) throws IOException,
            TemplateException {

        Configuration cfg = new Configuration(new Version("2.3.29"));

        cfg.setClassForTemplateLoading(TemplateConfigExample14.class, "/");
        cfg.setDefaultEncoding("UTF-8");
        
        
        
        TemplateConfiguration tcHTML = new TemplateConfiguration();
        tcHTML.setOutputFormat(HTMLOutputFormat.INSTANCE);

        TemplateConfiguration tcXML = new TemplateConfiguration();
        tcXML.setOutputFormat(XMLOutputFormat.INSTANCE);

        TemplateConfiguration tcRTF = new TemplateConfiguration();
        tcRTF.setOutputFormat(RTFOutputFormat.INSTANCE);

        cfg.setTemplateConfigurations(
                new FirstMatchTemplateConfigurationFactory(
                        new ConditionalTemplateConfigurationFactory(
                                new FileExtensionMatcher("xml"),
                                tcXML),
                        new ConditionalTemplateConfigurationFactory(
                                new OrMatcher(
                                        new FileExtensionMatcher("html"),
                                        new FileExtensionMatcher("htm")),
                                tcHTML),
                        new ConditionalTemplateConfigurationFactory(
                                new FileExtensionMatcher("rtf"),
                                tcRTF)
                ).allowNoMatch(true)
        );
        
        
        
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
