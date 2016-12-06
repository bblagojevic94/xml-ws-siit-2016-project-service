package rs.ac.uns.sw.xml.util;

import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

@Component
public class Transformers {

    private static final String ROOT_PATH_XSL   = "classpath:xsl-templates/";
    private static final String XSL_SUFIX       = "-template.xsl";

    private static final String CONFIG_FILE_PATH = "classpath:conf/fop.xconf";

    private static final String PDF_OUTPUT_FILE = "generated/propis.pdf";

    public Transformers() {
        super();
    }

    public static InputStreamResource toPdf(String xml) throws IOException, TransformerException, SAXException {

        // Initialize FOP factory object
        FopFactory fopFactory = FopFactory.newInstance(ResourceUtils.getFile(CONFIG_FILE_PATH));

        // Setup the XSLT transformer factory
        TransformerFactory transformerFactory = TransformerFactory.newInstance();

        // Point to the XSL-FO file
        File xslFile = ResourceUtils.getFile(ROOT_PATH_XSL + "propis-pdf-transformer-fo.xsl");

        // Create transformation source
        StreamSource transformSource = new StreamSource(xslFile);

        // Initialize the transformation subject
        StreamSource source = new StreamSource(new StringReader(xml));

        // Initialize user agent needed for the transformation
        FOUserAgent userAgent = fopFactory.newFOUserAgent();

        // Create the output stream to store the results
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();

        // Initialize the XSL-FO transformer object
        Transformer xslFoTransformer = transformerFactory.newTransformer(transformSource);

        // Construct FOP instance with desired output format
        Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, userAgent, outStream);

        // Resulting SAX events
        Result res = new SAXResult(fop.getDefaultHandler());

        // Start XSLT transformation and FOP processing
        xslFoTransformer.transform(source, res);

        // Generate PDF file
        File pdfFile = new File(PDF_OUTPUT_FILE);
        if (!pdfFile.getParentFile().exists()) {
            System.out.println("[INFO] A new directory is created: " + pdfFile.getParentFile().getAbsolutePath() + ".");
            pdfFile.getParentFile().mkdir();
        }

        OutputStream out = new BufferedOutputStream(new FileOutputStream(pdfFile));
        out.write(outStream.toByteArray());

        out.close();

        return new InputStreamResource(new FileInputStream(new File(PDF_OUTPUT_FILE)));
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

        factory.setNamespaceAware(true);
        factory.setIgnoringComments(true);
        factory.setIgnoringElementContentWhitespace(true);

        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new InputSource(new StringReader(xml)));
        return document;
    }
}
