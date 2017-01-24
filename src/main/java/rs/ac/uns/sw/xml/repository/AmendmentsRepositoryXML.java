package rs.ac.uns.sw.xml.repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.marklogic.client.DatabaseClient;
import com.marklogic.client.document.DocumentDescriptor;
import com.marklogic.client.document.DocumentPatchBuilder;
import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.io.JAXBHandle;
import com.marklogic.client.io.JacksonHandle;
import com.marklogic.client.io.SearchHandle;
import com.marklogic.client.io.marker.DocumentPatchHandle;
import com.marklogic.client.query.QueryManager;
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
import rs.ac.uns.sw.xml.util.*;
import rs.ac.uns.sw.xml.util.search_wrapper.SearchResult;
import rs.ac.uns.sw.xml.util.voting_wrapper.VotingObject;

import java.util.List;

import static rs.ac.uns.sw.xml.util.Constants.Resources.USERS;
import static rs.ac.uns.sw.xml.util.PartialUpdateUtil.createNamespaces;
import static rs.ac.uns.sw.xml.util.PartialUpdateUtil.makeCollectionPath;
import static rs.ac.uns.sw.xml.util.PredicatesConstants.*;
import static rs.ac.uns.sw.xml.util.RDFExtractorUtil.PARLIAMENT_NAMED_GRAPH_URI;
import static rs.ac.uns.sw.xml.util.RDFExtractorUtil.handleResults;

@Component
public class AmendmentsRepositoryXML {

    @Autowired
    XMLDocumentManager documentManager;

    @Autowired
    QueryManager queryManager;

    @Autowired
    DatabaseClient databaseClient;

    public Amendments save(Amendments amendments) {
        RepositoryUtil.ResultDocumentHandler handler = RepositoryUtil.documentHandle(MarkLogicConstants.Collections.AMENDMENTS, Amendments.class);

        handler.getContentHandle().set(amendments);
        String data = handler.getContentHandle().toString();

        RDFExtractorUtil.writeMetadata(data, databaseClient, PARLIAMENT_NAMED_GRAPH_URI);

        documentManager.write(getDocumentId(amendments.getId()), handler.getMetadata(), handler.getContentHandle());

        return amendments;
    }

    public boolean amendmentsExists(String id) {
        DocumentDescriptor descriptor = documentManager.exists(getDocumentId(id));
        if (descriptor != null) {
            return true;
        }
        return false;
    }

    public Amendments findAmendmentById(String id) {
        if (amendmentsExists(id)) {
            JAXBHandle contentHandle = RepositoryUtil.getObjectHandle(Amendments.class);
            JAXBHandle result = documentManager.read(getDocumentId(id), contentHandle);

            return (Amendments) result.get(Amendments.class);
        }
        return null;
    }

    public SearchResult findAll() {
        ResultHandler handler = new ResultHandler(Amendments.class, documentManager);

        StructuredQueryBuilder builder = queryManager.newStructuredQueryBuilder();
        StructuredQueryDefinition criteria = builder.collection(MarkLogicConstants.Collections.AMENDMENTS);

        SearchHandle result = new SearchHandle();
        queryManager.search(criteria, result);

        return handler.toSearchResult(result, null);
    }

    public void deleteAmendments(String id) {
        documentManager.delete(getDocumentId(id));

        databaseClient.release();
    }

    public Amendments updateAmendmentsStatus(String id, String status) {
        DocumentPatchBuilder patchBuilder = documentManager.newPatchBuilder();
        patchBuilder.setNamespaces(createNamespaces());

        final String amendmentsStatusXPath = "aman:amandmani/aman:head/aman:status";

        patchBuilder.replaceFragment(amendmentsStatusXPath, status);

        DocumentPatchHandle patchHandle = patchBuilder.build();
        documentManager.patch(getDocumentId(id), patchHandle);

        SPARQLQueryManager sparqlQueryManager = databaseClient.newSPARQLQueryManager();
        RDFUpdateUtil.updateRDFStringObject(id, Constants.Resources.AMENDMENTS, AMENDMENTS_STATUS, status, sparqlQueryManager);

        return findAmendmentById(id);
    }

    private String getDocumentId(String value) {
        return String.format("/amendments/%s.xml", value);
    }


    public SearchResult findAmendmentsByProposer(String username) {
        SPARQLQueryManager sparqlQueryManager = databaseClient.newSPARQLQueryManager();

        String queryDefinition = "PREFIX xs: <http://www.w3.org/2001/XMLSchema#> " +
                "SELECT * FROM <" + PARLIAMENT_NAMED_GRAPH_URI + "> WHERE { " +
                "?law <" + VOTES_FOR + "> ?votes_for . " +
                "?law <" + VOTES_AGAINST + "> ?votes_against . " +
                "?law <" + VOTES_NEUTRAL + "> ?votes_neutral . " +
                "?law <" + DATE_OF_PROPOSAL + "> ?proposal_date . " +
                "?law <" + DATE_OF_VOTING + "> ?voting_date . " +
                "?law <" + AMENDMENTS_STATUS + "> ?status . " +
                "?law <" + SUGGESTED + "> <" + USERS + username + "> . }";

        SPARQLQueryDefinition query = sparqlQueryManager
                .newQueryDefinition(queryDefinition);

        JacksonHandle resultsHandle = new JacksonHandle();
        resultsHandle.setMimetype(SPARQLMimeTypes.SPARQL_JSON);

        resultsHandle = sparqlQueryManager.executeSelect(query, resultsHandle);
        JsonNode node = resultsHandle.get().path("results").path("bindings");

        ResultHandler handler = new ResultHandler(Amendments.class, documentManager);
        return handler.toSearchResult(node);
    }

    public Amendments updateAmendmentsVotes(String id, VotingObject voting) {
        DocumentPatchBuilder patchBuilder = documentManager.newPatchBuilder();
        patchBuilder.setNamespaces(createNamespaces());

        final String amenVotesForXPath = "aman:amandmani/aman:head/aman:glasova_za";
        final String amenVotesAgainstXPath = "aman:amandmani/aman:head/aman:glasova_protiv";
        final String amenVotesNeutralXPath = "aman:amandmani/aman:head/aman:glasova_suzdrzani";

        patchBuilder.replaceFragment(amenVotesForXPath, voting.getVotesFor());
        patchBuilder.replaceFragment(amenVotesAgainstXPath, voting.getVotesAgainst());
        patchBuilder.replaceFragment(amenVotesNeutralXPath, voting.getVotesNeutral());

        DocumentPatchHandle patchHandle = patchBuilder.build();
        documentManager.patch(makeCollectionPath(id, "amendments"), patchHandle);

        SPARQLQueryManager sparqlQueryManager = databaseClient.newSPARQLQueryManager();
        RDFUpdateUtil.updateVotingTriples(id, Constants.Resources.AMENDMENTS, voting, sparqlQueryManager);

        return findAmendmentById(id);
    }
}
