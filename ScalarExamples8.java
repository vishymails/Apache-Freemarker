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

public class ScalarExamples8 {

    public static void main(String[] args) throws IOException,
            TemplateException {

        Configuration cfg = new Configuration(new Version("2.3.29"));

        cfg.setClassForTemplateLoading(ScalarExamples8.class, "/");
        cfg.setDefaultEncoding("UTF-8");

        Template template = cfg.getTemplate("basictemplate/templates/test4.ftl");

        Map<String, Object> root = new HashMap<>();
      
     root.put("user", "Vishwanath Rao");
     
     
     
 //IMPLEMENT USER DEFINED METHODS 
     
     root.put("indexOf", new IndexOfMethod());

     

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
