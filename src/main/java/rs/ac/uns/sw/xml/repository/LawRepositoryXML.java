package rs.ac.uns.sw.xml.repository;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.io.JAXBHandle;
import com.marklogic.client.io.SearchHandle;
import com.marklogic.client.query.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rs.ac.uns.sw.xml.config.MarkLogicConstants;
import rs.ac.uns.sw.xml.domain.Law;
import rs.ac.uns.sw.xml.domain.wrapper.SearchResult;
import rs.ac.uns.sw.xml.util.RepositoryUtil;
import rs.ac.uns.sw.xml.util.ResultHandler;

@Component
public class LawRepositoryXML {

    @Autowired
    XMLDocumentManager documentManager;

    @Autowired
    QueryManager queryManager;

    @Autowired
    DatabaseClient databaseClient;

    public Law save(Law law) {
        RepositoryUtil.ResultDocumentHandler handler = RepositoryUtil.documentHandle(MarkLogicConstants.Collections.LAWS, Law.class);

        handler.getContentHandle().set(law);

        documentManager.write(getDocumentId(law.getName()), handler.getMetadata(), handler.getContentHandle());

        // Return same element because of speed
        return law;
    }

    public Law findLawByName(String name) {
        JAXBHandle contentHandle = RepositoryUtil.getObjectHandle(Law.class);
        JAXBHandle result = documentManager.read(getDocumentId(name), contentHandle);

        return (Law) result.get(Law.class);
    }

    public SearchResult findAll() {

        ResultHandler handler = new ResultHandler(Law.class, documentManager);

        StructuredQueryBuilder builder = queryManager.newStructuredQueryBuilder();
        StructuredQueryDefinition criteria = builder.collection(MarkLogicConstants.Collections.LAWS);

        SearchHandle result = new SearchHandle();
        queryManager.search(criteria, result);

        return handler.toSearchResult(result);
    }

    public SearchResult findAllByQuery(String query) {

        ResultHandler handler = new ResultHandler(Law.class, documentManager);

        StringQueryDefinition queryDefinition = queryManager.newStringDefinition();
        queryDefinition.setCriteria(query);
        queryDefinition.setCollections(MarkLogicConstants.Collections.LAWS);

        SearchHandle result = new SearchHandle();
        queryManager.search(queryDefinition, result);

        return handler.toSearchResult(result);
    }

    private String getDocumentId(String value) {
        return String.format("/laws/%s.xml", value);
    }

}