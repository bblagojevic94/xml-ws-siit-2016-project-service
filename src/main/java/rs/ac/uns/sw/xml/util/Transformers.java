package rs.ac.uns.sw.xml.util;

import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
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

    @Value("classpath:conf/fop.xconf")
    private Resource configFile;

    private String name;

    public Transformers() {
        super();
    }

    public Transformers(String name) throws FileNotFoundException {
        this.name = name;
    }

    public InputStreamResource toPdf(String xml)
            throws IOException, TransformerException, SAXException {

        final FopFactory fopFactory = FopFactory.newInstance(configFile.getFile());
        final TransformerFactory transformerFactory = TransformerFactory.newInstance();

        // Init Transform file, Source file and Output Stream
        final StreamSource transformSource = new StreamSource(initPdfTransformerFile());
        final StreamSource source = new StreamSource(new StringReader(xml));
        final FOUserAgent userAgent = fopFactory.newFOUserAgent();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        // Set transformer and initialize parameters
        final Transformer xslFoTransformer = transformerFactory.newTransformer(transformSource);
        final Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, userAgent, outputStream);
        final Result result = new SAXResult(fop.getDefaultHandler());

        // Perform transform
        xslFoTransformer.transform(source, result);

        return new InputStreamResource(new ByteArrayInputStream(outputStream.toByteArray()));
    }

    public String toHtml(String xml)
            throws TransformerException, IOException, SAXException, ParserConfigurationException {

        // Init Transform file, Source file and Output Stream
        final StreamSource transformSource = new StreamSource(initHtmlTransformerFile());
        final TransformerFactory factory = TransformerFactory.newInstance();
        StringWriter writer = new StringWriter();

        // Set Transformer and set parameters
        final Transformer transformer = factory.newTransformer(transformSource);
        transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        // Generate XHTML
        transformer.setOutputProperty(OutputKeys.METHOD, "xhtml");

        // Transform DOM to HTML and perform transform
        final DOMSource source = new DOMSource(buildDocument(xml));
        final StreamResult result = new StreamResult(writer);
        transformer.transform(source, result);

        return writer.toString();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    private File initPdfTransformerFile() throws FileNotFoundException {
        return ResourceUtils.getFile(pdfRoot + this.name + pdfExtension);
    }

    private File initHtmlTransformerFile() throws FileNotFoundException {
        return ResourceUtils.getFile(xhtmlRoot + this.name + xhtmlExtension);
    }

    @Value("${xmlws.xsl.root.pdf}")
    private String pdfRoot;

    @Value("${xmlws.xsl.extension.pdf}")
    private String pdfExtension;

    @Value("${xmlws.xsl.root.xhtml}")
    private String xhtmlRoot;

    @Value("${xmlws.xsl.extension.xhtml}")
    private String xhtmlExtension;
}
