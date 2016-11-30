package rs.ac.uns.sw.xml.util;

import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;

@Component
public class Transformers {

    private static final String ROOT_PATH_XSL   = "classpath:xsl-templates/";
    private static final String XSL_SUFIX       = "-template.xsl";

    public Transformers() {
        super();
    }

    // TODO - .xml -> .pdf
    public static void toPdf(String xml, String xsl) {
        // FIXME Not sure this is right params
    }

     // @param xml   '.xml' string that needs to be converted to '.xhtml'
     // @param xsl   Name of '.xsl' file
    public static void toHtml(String xml, String filename) throws TransformerException, IOException, SAXException, ParserConfigurationException {

        // Initialize Transformer instance
        StreamSource transformSource = new StreamSource(ResourceUtils.getFile(ROOT_PATH_XSL + filename + XSL_SUFIX));
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(transformSource);
        transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        // Generate XHTML
        transformer.setOutputProperty(OutputKeys.METHOD, "xhtml");

        // Transform DOM to HTML
        DOMSource source = new DOMSource(buildDocument(xml));
        FileOutputStream oFile = new FileOutputStream(new File(filename + ".html"), false);
        StreamResult result = new StreamResult(oFile);
        transformer.transform(source, result);
    }

    private static Document buildDocument(String xml) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        // factory.setNamespaceAware(true);
        factory.setIgnoringComments(true);
        factory.setIgnoringElementContentWhitespace(true);

        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new InputSource(new StringReader(xml)));
        return document;
    }
}
