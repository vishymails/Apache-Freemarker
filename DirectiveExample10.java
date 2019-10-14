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

public class DirectiveExample10 {

    public static void main(String[] args) throws IOException,
            TemplateException {

        Configuration cfg = new Configuration(new Version("2.3.29"));

        cfg.setClassForTemplateLoading(DirectiveExample10.class, "/");
        cfg.setDefaultEncoding("UTF-8");

        Template template = cfg.getTemplate("basictemplate/templates/test5.ftl");

        Map<String, Object> root = new HashMap<>();
      
     root.put("user", "Vishwanath Rao");
     
     
     
 //IMPLEMENT USER DEFINED DIRECTIVES
     
     
     root.put("upper", new UpperDirective());
     

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
