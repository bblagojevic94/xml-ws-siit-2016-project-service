package rs.ac.uns.sw.xml.util;


import com.marklogic.client.semantics.SPARQLQueryDefinition;
import com.marklogic.client.semantics.SPARQLQueryManager;
import rs.ac.uns.sw.xml.util.voting_wrapper.VotingObject;

import static rs.ac.uns.sw.xml.util.PredicatesConstants.*;
import static rs.ac.uns.sw.xml.util.RDFExtractorUtil.PARLIAMENT_NAMED_GRAPH_URI;

public class RDFUpdateUtil {

    public static void updateVotingTriples(String id, String resource, VotingObject voting, SPARQLQueryManager sparqlQueryManager) {
        updateRDFIntObject(id, resource, VOTES_FOR, voting.getVotesFor(), sparqlQueryManager);
        updateRDFIntObject(id, resource, VOTES_AGAINST, voting.getVotesAgainst(), sparqlQueryManager);
        updateRDFIntObject(id, resource, VOTES_NEUTRAL, voting.getVotesNeutral(), sparqlQueryManager);
    }

    public static void updateRDFIntObject(String id, String resource, String predicate, int value, SPARQLQueryManager sparqlQueryManager) {
        String queryDefinition =
                "PREFIX xs: <http://www.w3.org/2001/XMLSchema#> \n" +
                        " WITH <" + PARLIAMENT_NAMED_GRAPH_URI + ">" +
                        " DELETE { <" + resource + id + "> <" + predicate + ">  ?o} " +
                        " INSERT { <" + resource + id + "> <" + predicate + "> '" + value + "'^^xs:int }" +
                        " WHERE  { <" + resource + id + "> <" + predicate + ">  ?o}";
        SPARQLQueryDefinition query = sparqlQueryManager
                .newQueryDefinition(queryDefinition);

        sparqlQueryManager.executeUpdate(query);
    }

    public static void updateRDFStringObject(String id, String resource, String predicate, String value, SPARQLQueryManager sparqlQueryManager) {
        String queryDefinition =
                "PREFIX xs: <http://www.w3.org/2001/XMLSchema#> \n" +
                        " WITH <" + PARLIAMENT_NAMED_GRAPH_URI + ">" +
                        " DELETE { <" + resource + id + "> <" + predicate + ">  ?o} " +
                        " INSERT { <" + resource + id + "> <" + predicate + "> '" + value + "' }" +
                        " WHERE  { <" + resource + id + "> <" + predicate + ">  ?o}";
        SPARQLQueryDefinition query = sparqlQueryManager
                .newQueryDefinition(queryDefinition);

        sparqlQueryManager.executeUpdate(query);
    }
}
