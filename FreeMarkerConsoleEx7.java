package basictemplate;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class FreeMarkerConsoleEx7 {

    public static void main(String[] args) throws IOException,
            TemplateException {

        Configuration cfg = new Configuration(new Version("2.3.29"));

        cfg.setClassForTemplateLoading(FreeMarkerConsoleEx7.class, "/");
        cfg.setDefaultEncoding("UTF-8");

        // IMPORTANT : you can have .ftlh extension also
        
        /*
         * This gives you a Template instance that was created by reading 
         * /where/you/store/templates/test.ftlh and parsing it.
			The Template instance stores the template in parsed form, and not as text. 
			If the template is missing or syntactically incorrect, getTemplate will throw exception instead.


			Configuration caches Template instances, so when you call cfg.getTemplate("test.ftlh") 
			next time, it probably won't read and parse the template file again, just returns the 
			same Template instance as for the first time.
         */
        
        
        Template template = cfg.getTemplate("basictemplate/templates/test3.ftl");

        Map<String, Object> root = new HashMap<>();
        
        //variables used to fill values to the templates goes as TemplateModel api of FTL lib
        

     // Put string "user" into the root
     root.put("user", "Vishwanath Rao");
     
     
    
     
     
     
     // Create the "latestProduct" hash. We use a JavaBean here, but it could be a Map too.
     Product latest = new Product();
     latest.setUrl("products/greenmouse.html");
     latest.setName("green mouse");
     // and put it into the root
     root.put("latestProduct", latest);
     
     Writer out = new OutputStreamWriter(System.out);
     template.process(root, out);
     
     
    }
}
