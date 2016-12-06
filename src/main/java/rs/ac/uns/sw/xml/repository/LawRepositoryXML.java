package rs.ac.uns.sw.xml.repository;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.io.DocumentMetadataHandle;
import com.marklogic.client.io.JAXBHandle;
import com.marklogic.client.io.SearchHandle;
import com.marklogic.client.query.MatchDocumentSummary;
import com.marklogic.client.query.QueryManager;
import com.marklogic.client.query.StructuredQueryBuilder;
import com.marklogic.client.query.StructuredQueryDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rs.ac.uns.sw.xml.config.MarkLogicConstants;
import rs.ac.uns.sw.xml.domain.Law;
import rs.ac.uns.sw.xml.domain.wrapper.SearchResult;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.List;

@Component
public class LawRepositoryXML {

    @Autowired
    XMLDocumentManager documentManager;

    @Autowired
    QueryManager queryManager;

    @Autowired
    DatabaseClient databaseClient;

    public Law save(Law law) {
        DocumentMetadataHandle metadata = new DocumentMetadataHandle();

        metadata.getCollections().add(MarkLogicConstants.COLLECTION_LAWS);

        JAXBHandle contentHandle = getProductHandle();
        contentHandle.set(law);

        documentManager.write(getDocumentId(law.getName()), metadata, contentHandle);

        return findLawByName(law.getName());
    }

    public Law findLawByName(String name) {
        JAXBHandle contentHandle = getProductHandle();
        JAXBHandle result = documentManager.read(getDocumentId(name), contentHandle);

        return (Law) result.get(Law.class);
    }

    public SearchResult findAll() {
        StructuredQueryBuilder builder = queryManager.newStructuredQueryBuilder();
        StructuredQueryDefinition criteria = builder.collection(MarkLogicConstants.COLLECTION_LAWS);

        SearchHandle result = new SearchHandle();
        queryManager.search(criteria, result);

        return toSearchResult(result);
    }

    private JAXBHandle getProductHandle() {
        try {
            JAXBContext context = JAXBContext.newInstance(Law.class);
            return new JAXBHandle(context);
        } catch (JAXBException e) {
            throw new RuntimeException("Unable to create Product Handle...", e);
        }
    }

    private SearchResult toSearchResult(SearchHandle resultHandler) {
        List<Object> products = new ArrayList<>();
        for (MatchDocumentSummary summary : resultHandler.getMatchResults()) {
            JAXBHandle contentHandle = getProductHandle();
            documentManager.read(summary.getUri(), contentHandle);
            products.add(contentHandle.get(Law.class));
        }
        return new SearchResult(products);
    }

    private String getDocumentId(String value) {
        return String.format("/laws/%s.xml", value);
    }

}