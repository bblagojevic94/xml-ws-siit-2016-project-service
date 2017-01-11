package rs.ac.uns.sw.xml.repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.marklogic.client.DatabaseClient;
import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.io.DOMHandle;
import com.marklogic.client.io.JAXBHandle;
import com.marklogic.client.io.JacksonHandle;
import com.marklogic.client.io.SearchHandle;
import com.marklogic.client.query.QueryManager;
import com.marklogic.client.query.StructuredQueryBuilder;
import com.marklogic.client.query.StructuredQueryDefinition;
import com.marklogic.client.semantics.SPARQLMimeTypes;
import com.marklogic.client.semantics.SPARQLQueryDefinition;
import com.marklogic.client.semantics.SPARQLQueryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Node;
import rs.ac.uns.sw.xml.config.MarkLogicConstants;
import rs.ac.uns.sw.xml.domain.Law;
import rs.ac.uns.sw.xml.domain.wrapper.SearchResult;
import rs.ac.uns.sw.xml.util.MetaSearchWrapper;
import rs.ac.uns.sw.xml.util.RDFExtractorUtil;
import rs.ac.uns.sw.xml.util.RepositoryUtil;
import rs.ac.uns.sw.xml.util.ResultHandler;

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.OutputStream;

import static rs.ac.uns.sw.xml.util.DateUtil.DATE_FORMAT;
import static rs.ac.uns.sw.xml.util.PredicatesConstants.*;
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
        documentManager.write(getDocumentId(law.getId()), handler.getMetadata(), handler.getContentHandle());

        // Return same element because of speed
        return law;
    }

    public Law findLawById(String id) {
        JAXBHandle contentHandle = RepositoryUtil.getObjectHandle(Law.class);
        JAXBHandle result = documentManager.read(getDocumentId(id), contentHandle);

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

    private String getDocumentId(String value) {
        return String.format("/laws/%s.xml", value);
    }

    public JsonNode searchMetadata(MetaSearchWrapper searchWrapper) {
        SPARQLQueryManager sparqlQueryManager = databaseClient.newSPARQLQueryManager();

        String queryDefinition = "PREFIX xs: <http://www.w3.org/2001/XMLSchema#> " +
                "SELECT ?law FROM <" + PARLIAMENT_NAMED_GRAPH_URI + "> WHERE { " +
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

    public JsonNode getMetadataJSON() {
        SPARQLQueryManager sparqlQueryManager = databaseClient.newSPARQLQueryManager();

        String queryDefinition = "PREFIX xs: <http://www.w3.org/2001/XMLSchema#> " +
                "SELECT * FROM <" + PARLIAMENT_NAMED_GRAPH_URI + "> WHERE { ?s ?p ?o}";

        SPARQLQueryDefinition query = sparqlQueryManager
                .newQueryDefinition(queryDefinition);

        JacksonHandle resultsHandle = new JacksonHandle();
        resultsHandle.setMimetype(SPARQLMimeTypes.SPARQL_JSON);

        resultsHandle = sparqlQueryManager.executeSelect(query, resultsHandle);
        return resultsHandle.get().path("results").path("bindings");
    }

    public StreamResult getMetadataTriples() {
        SPARQLQueryManager sparqlQueryManager = databaseClient.newSPARQLQueryManager();

        String queryDefinition = "PREFIX xs: <http://www.w3.org/2001/XMLSchema#> " +
                "SELECT * FROM <" + PARLIAMENT_NAMED_GRAPH_URI + "> WHERE { ?s ?p ?o}";

        SPARQLQueryDefinition query = sparqlQueryManager
                .newQueryDefinition(queryDefinition);

        DOMHandle resultsHandle = new DOMHandle();
        resultsHandle = sparqlQueryManager.executeSelect(query, resultsHandle);
        StreamResult results = transform(resultsHandle.get(), System.out);
        return results;
    }

    public static StreamResult transform(Node node, OutputStream out) {
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();

            transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(node);

            StreamResult result = new StreamResult(out);
            transformer.transform(source, result);

            return result;
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerFactoryConfigurationError e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        return null;
    }
}