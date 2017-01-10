package rs.ac.uns.sw.xml.repository;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.document.DocumentPatchBuilder;
import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.io.JAXBHandle;
import com.marklogic.client.io.SearchHandle;
import com.marklogic.client.io.marker.DocumentPatchHandle;
import com.marklogic.client.query.QueryManager;
import com.marklogic.client.query.StringQueryDefinition;
import com.marklogic.client.query.StructuredQueryBuilder;
import com.marklogic.client.query.StructuredQueryDefinition;
import com.marklogic.client.util.EditableNamespaceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rs.ac.uns.sw.xml.config.MarkLogicConstants;
import rs.ac.uns.sw.xml.domain.Amendments;
import rs.ac.uns.sw.xml.domain.Law;
import rs.ac.uns.sw.xml.util.search_wrapper.SearchResult;
import rs.ac.uns.sw.xml.util.RDFExtractorUtil;
import rs.ac.uns.sw.xml.util.RepositoryUtil;
import rs.ac.uns.sw.xml.util.ResultHandler;

import static rs.ac.uns.sw.xml.util.RDFExtractorUtil.PARLIAMENT_NAMED_GRAPH_URI;

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
        String data = handler.getContentHandle().toString();

        RDFExtractorUtil.writeMetadata(data, databaseClient, PARLIAMENT_NAMED_GRAPH_URI);
        documentManager.write(getDocumentId(law.getName()), handler.getMetadata(), handler.getContentHandle());

        // Return same element because of speed
        return law;
    }

    public Law updateLawWithAmendments(Amendments amendments) {
        //final Amendment amendment = amendments.getBody().getAmandman().get(0);

        DocumentPatchBuilder patchBuilder = documentManager.newPatchBuilder();

        EditableNamespaceContext namespaces = new EditableNamespaceContext();
        namespaces.put("aman", "http://www.parlament.gov.rs/schema/amandman");
        namespaces.put("pred", "http://www.parlament.gov.rs/rdf_schema/skupstina#");
        namespaces.put("elem", "http://www.parlament.gov.rs/schema/elementi");
        //namespaces.put("prop", "http://www.parlament.gov.rs/schema/propis");
        namespaces.put("fn", "http://www.w3.org/2005/xpath-functions");

        patchBuilder.setNamespaces(namespaces);

        String xpath = "/aman:amandmani/aman:body";

        //patchBuilder.insertFragment(path, DocumentPatchBuilder.Position.AFTER, "<a></a>");
        patchBuilder.delete(xpath);

        DocumentPatchHandle documentPatchHandle = patchBuilder.build();
        documentManager.patch("/amendments/name0.xml", documentPatchHandle);

        return null;
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