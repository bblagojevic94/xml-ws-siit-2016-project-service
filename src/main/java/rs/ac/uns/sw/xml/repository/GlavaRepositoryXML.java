package rs.ac.uns.sw.xml.repository;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.eval.EvalResult;
import com.marklogic.client.eval.EvalResultIterator;
import com.marklogic.client.eval.ServerEvaluationCall;
import com.marklogic.client.io.DocumentMetadataHandle;
import com.marklogic.client.io.JAXBHandle;
import com.marklogic.client.io.SearchHandle;
import com.marklogic.client.query.MatchDocumentSummary;
import com.marklogic.client.query.QueryManager;
import com.marklogic.client.query.StructuredQueryBuilder;
import com.marklogic.client.query.StructuredQueryDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;
import rs.ac.uns.sw.xml.domain.Glava;
import rs.ac.uns.sw.xml.domain.wrapper.GlavaSearchResult;
import rs.ac.uns.sw.xml.util.Transformers;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

@Component
public class GlavaRepositoryXML {

    public static final String COLLECTION_REF = "/glave.xml";

    @Autowired
    XMLDocumentManager documentManager;

    @Autowired
    QueryManager queryManager;

    @Autowired
    DatabaseClient databaseClient;


    // BASIC REPOSITORY FUNCTIONS
    public Glava save(Glava document) {
        DocumentMetadataHandle metadata = new DocumentMetadataHandle();

        // Add Collection
        metadata.getCollections().add(COLLECTION_REF);

        JAXBHandle contentHandle = getProductHandle();
        contentHandle.set(document);

        documentManager.write(getDocumentId(document.getId()), metadata, contentHandle);

        return findById(document.getId());
    }

    public Glava findById(String id) {
        JAXBHandle contentHandle = getProductHandle();
        JAXBHandle result = documentManager.read(getDocumentId(id), contentHandle);
        return (Glava) result.get(Glava.class);
    }

    public GlavaSearchResult findAll() {
        StructuredQueryBuilder builder = queryManager.newStructuredQueryBuilder();
        StructuredQueryDefinition criteria = builder.collection(COLLECTION_REF);

        SearchHandle result = new SearchHandle();
        queryManager.search(criteria, result);

        try {
            JAXBContext context = JAXBContext.newInstance(GlavaSearchResult.class);
            Marshaller marshaller = context.createMarshaller();

            StringWriter sw = new StringWriter();
            marshaller.marshal(toSearchResult(result), sw);
            String xmlString = sw.toString();

            Transformers.toPdf(xmlString, "glave");
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return toSearchResult(result);
    }


    // ADVANCED REPOSITORY FUNCTIONS
    // Sample XQuery evaluation on server
    public GlavaSearchResult findByOdjeljakContains(String query) {
        String xquery = "for $x in collection(\"/glave.xml\")/glava\n" +
                "where contains($x/odjeljak, \"" + query + "\")\n" +
                "return $x";

        ServerEvaluationCall invoker = databaseClient.newServerEval();

        // Invoke the query
        invoker.xquery(xquery);

        // Interpret the results
        EvalResultIterator response = invoker.eval();

        System.out.print("[INFO] Response: ");

        if (response.hasNext()) {

            for (EvalResult result : response) {
                System.out.println("\n" + result.getString());
            }
        } else {
            System.out.println("your query returned an empty sequence.");
        }

        return null;
    }


    // PRIVATE HELPER FUNCTIONS
    private GlavaSearchResult toSearchResult(SearchHandle resultHandler) {
        List<Glava> products = new ArrayList<>();
        for (MatchDocumentSummary summary : resultHandler.getMatchResults()) {
            JAXBHandle contentHandle = getProductHandle();
            documentManager.read(summary.getUri(), contentHandle);
            products.add((Glava) contentHandle.get(Glava.class));
        }
        return new GlavaSearchResult(products);
    }

    private JAXBHandle getProductHandle() {
        try {
            JAXBContext context = JAXBContext.newInstance(Glava.class);
            return new JAXBHandle(context);
        } catch (JAXBException e) {
            throw new RuntimeException("Unable to create Product Handle...", e);
        }
    }

    private String getDocumentId(String id) {
        return String.format("/glave/%s.xml", id);
    }
}
