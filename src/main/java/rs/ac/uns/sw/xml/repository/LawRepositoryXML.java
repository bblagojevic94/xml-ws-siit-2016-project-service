package rs.ac.uns.sw.xml.repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.marklogic.client.DatabaseClient;
import com.marklogic.client.document.DocumentPatchBuilder;
import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.io.DOMHandle;
import com.marklogic.client.io.JAXBHandle;
import com.marklogic.client.io.JacksonHandle;
import com.marklogic.client.io.SearchHandle;
import com.marklogic.client.io.marker.DocumentPatchHandle;
import com.marklogic.client.query.QueryManager;
import com.marklogic.client.query.StringQueryDefinition;
import com.marklogic.client.query.StructuredQueryBuilder;
import com.marklogic.client.query.StructuredQueryDefinition;
import com.marklogic.client.semantics.SPARQLMimeTypes;
import com.marklogic.client.semantics.SPARQLQueryDefinition;
import com.marklogic.client.semantics.SPARQLQueryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rs.ac.uns.sw.xml.config.MarkLogicConstants;
import rs.ac.uns.sw.xml.domain.Amendment;
import rs.ac.uns.sw.xml.domain.Amendments;
import rs.ac.uns.sw.xml.domain.Law;
import rs.ac.uns.sw.xml.util.MetaSearchWrapper;
import rs.ac.uns.sw.xml.util.RDFExtractorUtil;
import rs.ac.uns.sw.xml.util.RepositoryUtil;
import rs.ac.uns.sw.xml.util.ResultHandler;
import rs.ac.uns.sw.xml.util.search_wrapper.SearchResult;

import javax.xml.bind.JAXBException;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.List;

import static rs.ac.uns.sw.xml.util.DateUtil.DATE_FORMAT;
import static rs.ac.uns.sw.xml.util.PartialUpdateUtil.*;
import static rs.ac.uns.sw.xml.util.PredicatesConstants.*;
import static rs.ac.uns.sw.xml.util.RDFExtractorUtil.PARLIAMENT_NAMED_GRAPH_URI;
import static rs.ac.uns.sw.xml.util.RDFExtractorUtil.transformTriples;

@Component
public class LawRepositoryXML {

    public static final String DELETING = "brisanje";
    public static final String CHANGING = "izmjena";
    public static final String ADDING = "dopuna";

    public static final String RESULTS = "results";
    public static final String BINDINGS = "bindings";

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
        documentManager.write(getDocumentId(law.getId()), handler.getMetadata(), handler.getContentHandle());

        // Return same element because of speed
        return law;
    }

    public Law updateLawWithAmendments(Amendments amendments) {

        final String lawId = amendments.getHead().getPropis().getRef().getId();

        DocumentPatchBuilder patchBuilder;
        DocumentPatchHandle patchHandle;

        String element;

        final List<Amendment> amendmentList = amendments.getBody().getAmandman();

        for (final Amendment amendment: amendmentList) {

            final String type = amendment.getHead().getRjesenje();
            final String ref = amendment.getHead().getPredmet().getRef().getId();
            final String xpath = makeXPath(ref);

            // Remove last two characters of XPath ('//')
            final String xpathTrimmed = xpath.substring(0, xpath.length() - 2);

            patchBuilder = documentManager.newPatchBuilder();
            patchBuilder.setNamespaces(createNamespaces());

            switch (type) {
                case DELETING:
                    patchBuilder.delete(xpathTrimmed);
                    break;

                case CHANGING:
                    try {
                        element = createXMLbyRegulation(amendment.getBody().getOdredba());
                        patchBuilder.replaceFragment(xpathTrimmed, element);
                    } catch (JAXBException e) {
                        e.printStackTrace();
                    }
                    break;

                case ADDING:
                    try {
                        element = createXMLbyRegulation(amendment.getBody().getOdredba());
                        patchBuilder.insertFragment(xpathTrimmed, DocumentPatchBuilder.Position.AFTER, element);
                    } catch (JAXBException e) {
                        e.printStackTrace();
                    }
                    break;
            }

            patchHandle = patchBuilder.build();
            documentManager.patch(makeCollectionPath(lawId), patchHandle);
        }

        return findLawById(lawId);
    }

    public Law updateLawStatus(String id, String status) {
        DocumentPatchBuilder patchBuilder = documentManager.newPatchBuilder();
        patchBuilder.setNamespaces(createNamespaces());

        final String lawStatusXPath = "propis:propis/propis:head/propis:status";

        patchBuilder.replaceFragment(lawStatusXPath, status);

        DocumentPatchHandle patchHandle = patchBuilder.build();
        documentManager.patch(makeCollectionPath(id), patchHandle);

        return findLawById(id);
    }

    public Law findLawById(String id) {
        JAXBHandle contentHandle = RepositoryUtil.getObjectHandle(Law.class);
        JAXBHandle result = documentManager.read(getDocumentId(id), contentHandle);

        return (Law) result.get(Law.class);
    }

    public void deleteLaw(String id) {
        documentManager.delete(getDocumentId(id));

        databaseClient.release();
    }

    public SearchResult findAll() {

        ResultHandler handler = new ResultHandler(Law.class, documentManager);

        StructuredQueryBuilder builder = queryManager.newStructuredQueryBuilder();
        StructuredQueryDefinition criteria = builder.collection(MarkLogicConstants.Collections.LAWS);

        SearchHandle result = new SearchHandle();
        queryManager.search(criteria, result);

        return handler.toSearchResult(result, null);
    }

    public SearchResult findAllByQueryAndMetadata(String query, MetaSearchWrapper searchWrapper) {
        ResultHandler handler = new ResultHandler(Law.class, documentManager);

        if (query == null) {
            return handler.toSearchResult(searchMetadata(searchWrapper));
        }

        StringQueryDefinition queryDefinition = queryManager.newStringDefinition();
        queryDefinition.setCriteria(query);
        queryDefinition.setCollections(MarkLogicConstants.Collections.LAWS);

        SearchHandle result = new SearchHandle();
        queryManager.search(queryDefinition, result);


        JsonNode properties = null;
        try {
            if (isMetadataSearchActive(searchWrapper))
                properties = searchMetadata(searchWrapper);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return handler.toSearchResult(result, properties);
    }

    public JsonNode getMetadataJSON() {
        SPARQLQueryManager sparqlQueryManager = databaseClient.newSPARQLQueryManager();

        String queryDefinition = "PREFIX xs: <http://www.w3.org/2001/XMLSchema#> " +
                "SELECT * FROM <" + PARLIAMENT_NAMED_GRAPH_URI + "> WHERE { ?s ?p ?o}";

        SPARQLQueryDefinition query = sparqlQueryManager
                .newQueryDefinition(queryDefinition);

        JacksonHandle resultsHandle = new JacksonHandle();
        resultsHandle.setMimetype(SPARQLMimeTypes.SPARQL_JSON);

        resultsHandle = sparqlQueryManager.executeSelect(query, resultsHandle);
        return resultsHandle.get().path(RESULTS).path(BINDINGS);
    }

    public String getMetadataTriples() {
        SPARQLQueryManager sparqlQueryManager = databaseClient.newSPARQLQueryManager();

        String queryDefinition = "PREFIX xs: <http://www.w3.org/2001/XMLSchema#> " +
                "SELECT * FROM <" + PARLIAMENT_NAMED_GRAPH_URI + "> WHERE { ?s ?p ?o}";

        SPARQLQueryDefinition query = sparqlQueryManager
                .newQueryDefinition(queryDefinition);

        DOMHandle resultsHandle = new DOMHandle();
        resultsHandle = sparqlQueryManager.executeSelect(query, resultsHandle);

        StringWriter writer = new StringWriter();
        transformTriples(resultsHandle.get(), writer);
        return writer.getBuffer().toString();
    }

    private String getDocumentId(String value) {
        return String.format("/laws/%s.xml", value);
    }

    private JsonNode searchMetadata(MetaSearchWrapper searchWrapper) {
        SPARQLQueryManager sparqlQueryManager = databaseClient.newSPARQLQueryManager();

        String queryDefinition = "PREFIX xs: <http://www.w3.org/2001/XMLSchema#> " +
                "SELECT * FROM <" + PARLIAMENT_NAMED_GRAPH_URI + "> WHERE { " +
                "?law <" + VOTES_FOR + "> ?votes_for . " +
                "?law <" + VOTES_AGAINST + "> ?votes_against . " +
                "?law <" + VOTES_NEUTRAL + "> ?votes_neutral . " +
                "?law <" + DATE_OF_PROPOSAL + "> ?proposal_date . " +
                "?law <" + DATE_OF_VOTING + "> ?voting_date . " +
                "?law <" + LAW_STATUS + "> ?status . ";

        String criteria = "";
        if (searchWrapper.getStartVotesFor() != null)
            criteria += "?votes_for >= " + searchWrapper.getStartVotesFor() + " && ";
        if (searchWrapper.getEndVotesFor() != null)
            criteria += "?votes_for <= " + searchWrapper.getEndVotesFor() + " && ";
        if (searchWrapper.getStartVotesAgainst() != null)
            criteria += "?votes_against >= " + searchWrapper.getStartVotesAgainst() + " && ";
        if (searchWrapper.getEndVotesAgainst() != null)
            criteria += "?votes_against <= " + searchWrapper.getEndVotesAgainst() + " && ";
        if (searchWrapper.getStartVotesNeutral() != null)
            criteria += "?votes_neutral >= " + searchWrapper.getStartVotesNeutral() + " && ";
        if (searchWrapper.getEndVotesNeutral() != null)
            criteria += "?votes_neutral <= " + searchWrapper.getEndVotesNeutral() + " && ";
        if (searchWrapper.getStartDateOfProposal() != null)
            criteria += "?proposal_date >= \"" + DATE_FORMAT.format(searchWrapper.getStartDateOfProposal()) + "\"^^xs:date && ";
        if (searchWrapper.getEndDateOfProposal() != null)
            criteria += "?proposal_date <= \"" + DATE_FORMAT.format(searchWrapper.getEndDateOfProposal()) + "\"^^xs:date && ";
        if (searchWrapper.getStartDateOfVoting() != null)
            criteria += "?voting_date >= \"" + DATE_FORMAT.format(searchWrapper.getStartDateOfVoting()) + "\"^^xs:date && ";
        if (searchWrapper.getEndDateOfVoting() != null)
            criteria += "?voting_date <= \"" + DATE_FORMAT.format(searchWrapper.getEndDateOfVoting()) + "\"^^xs:date && ";
        if (searchWrapper.getStatus() != null)
            criteria += "?status = \"" + searchWrapper.getStatus() + "\"^^xs:string && ";

        if (!criteria.equals("")) {
            criteria = "FILTER (" + criteria.substring(0, criteria.length() - 3) + ")";
        }
        queryDefinition += criteria + " }";

        SPARQLQueryDefinition query = sparqlQueryManager
                .newQueryDefinition(queryDefinition);

        JacksonHandle resultsHandle = new JacksonHandle();
        resultsHandle.setMimetype(SPARQLMimeTypes.SPARQL_JSON);

        resultsHandle = sparqlQueryManager.executeSelect(query, resultsHandle);
        return resultsHandle.get().path("results").path("bindings");
    }

    private boolean isMetadataSearchActive(MetaSearchWrapper obj) throws IllegalAccessException {
        for (Field f : obj.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            if (f.get(obj) != null) {
                return true;
            }
        }

        return false;
    }
}