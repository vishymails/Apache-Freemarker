package basictemplate;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class XMLParserExample26 {

    public static void main(String[] args) throws IOException,
            TemplateException, SAXException, ParserConfigurationException {

        Configuration cfg = new Configuration(new Version("2.3.23"));

        cfg.setClassForTemplateLoading(XMLParserExample26.class, "/");
        cfg.setDefaultEncoding("UTF-8");

        Template template = cfg.getTemplate("basictemplate/templates/test19.ftl");

        Map root = new HashMap();
        root.put(
                "doc",
                freemarker.ext.dom.NodeModel.parse(new File("src/basictemplate/book.xml")));
        
        try (StringWriter out = new StringWriter()) {
            
            template.process(root, out);
            System.out.println(out.getBuffer().toString());

            out.flush();
        }
    }
}
