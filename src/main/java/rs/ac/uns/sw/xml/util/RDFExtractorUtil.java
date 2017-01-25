package rs.ac.uns.sw.xml.util;


import com.fasterxml.jackson.databind.JsonNode;
import com.marklogic.client.DatabaseClient;
import com.marklogic.client.io.DOMHandle;
import com.marklogic.client.io.JacksonHandle;
import com.marklogic.client.io.StringHandle;
import com.marklogic.client.semantics.GraphManager;
import com.marklogic.client.semantics.RDFMimeTypes;
import org.apache.xalan.processor.TransformerFactoryImpl;
import org.springframework.scheduling.annotation.Async;
import org.w3c.dom.Node;

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static rs.ac.uns.sw.xml.util.PredicatesConstants.*;

public class RDFExtractorUtil {

    private static final String XSLT_FILE = "./src/main/resources/xsl/grddl/grddl.xsl";
    public static final String PARLIAMENT_NAMED_GRAPH_URI = "skupstina/metadata";

    public static void extractMetadata(InputStream in, OutputStream out) throws IOException, TransformerException {
        // Initialize the transformation
        TransformerFactoryImpl transformerFactory = new TransformerFactoryImpl();
        StreamSource transformSource = new StreamSource(new File(XSLT_FILE));
        Transformer grddlTransformer = transformerFactory.newTransformer(transformSource);

        // Set the indentation properties
        grddlTransformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
        grddlTransformer.setOutputProperty(OutputKeys.INDENT, "yes");

        // Initialize streams src and dest streams
        StreamSource source = new StreamSource(in);
        StreamResult result = new StreamResult(out);

        // Apply the transformation
        grddlTransformer.transform(source, result);
    }

    @Async
    public static void writeMetadata(String data, DatabaseClient databaseClient, String graphURI) {
        GraphManager graphManager = databaseClient.newGraphManager();
        graphManager.setDefaultMimetype(RDFMimeTypes.RDFXML);

        try {
            // Creating src and dest streams
            InputStream in = new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8));
            OutputStream out = new ByteArrayOutputStream();

            // Extracting metadata from xml document
            RDFExtractorUtil.extractMetadata(in, out);

            // Write data to MarkLogic
            StringHandle handle = new StringHandle(out.toString())
                    .withMimetype(RDFMimeTypes.RDFXML);


            graphManager.merge(graphURI, handle);

            // Checking if writing is well done
            // System.out.println(readMetaData(databaseClient, graphURI));

            in.close();
            out.close();
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readMetaData(DatabaseClient databaseClient, String graphURI) throws IOException {
        GraphManager graphManager = databaseClient.newGraphManager();
        graphManager.setDefaultMimetype(RDFMimeTypes.NTRIPLES);

        DOMHandle domHandle = new DOMHandle();
        // Retrieve RDF triplets in format (RDF/XML) other than default
        graphManager.read(graphURI, domHandle).withMimetype(RDFMimeTypes.RDFXML);
        OutputStream out = new ByteArrayOutputStream();
        transform(domHandle.get(), out);
        out.close();

        return out.toString();
    }

    /**
     * Serializes DOM tree to an arbitrary OutputStream.
     *
     * @param node a node to be serialized
     * @param out  an output stream to write the serialized
     *             DOM representation to
     */
    public static void transform(Node node, OutputStream out) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();

            // Creating instance for serialization DOM model
            Transformer transformer = transformerFactory.newTransformer();

            // Indentation configuration
            transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(node);
            StreamResult result = new StreamResult(out);
            transformer.transform(source, result);

        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }


    public static List<String> handleResults(JacksonHandle resultsHandle) {
        JsonNode tuples = resultsHandle.get().path("results").path("bindings");
        List<String> list = new ArrayList<>();

        System.out.println(tuples);
        StringBuilder builder = new StringBuilder();
        for (JsonNode row : tuples) {
            System.out.println(row);
            String subject = row.path("s").path("value").asText();
            String predicate = row.path("p").path("value").asText();
            String object = row.path("o").path("value").asText();

            if (!subject.equals("")) {
                list.add(subject);
            }
        }

        return list;
    }

    public static void transformTriples(Node node, StringWriter out) {
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(node);

            StreamResult result = new StreamResult(out);
            transformer.transform(source, result);

        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public static String fullQuerryForLaw(){
        String queryDefinition = "PREFIX xs: <http://www.w3.org/2001/XMLSchema#> " +
                "SELECT * FROM <" + PARLIAMENT_NAMED_GRAPH_URI + "> WHERE { " +
                "?law <" + VOTES_FOR + "> ?votes_for . " +
                "?law <" + VOTES_AGAINST + "> ?votes_against . " +
                "?law <" + VOTES_NEUTRAL + "> ?votes_neutral . " +
                "?law <" + DATE_OF_PROPOSAL + "> ?proposal_date . " +
                "?law <" + DATE_OF_VOTING + "> ?voting_date . " +
                "?law <" + LAW_STATUS + "> ?status . " +
                "?law <" + SUGGESTED + ">  ?proposer . }" ;
        return queryDefinition;
    }
}