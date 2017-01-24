package rs.ac.uns.sw.xml.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.io.JAXBHandle;
import com.marklogic.client.io.SearchHandle;
import com.marklogic.client.query.MatchDocumentSummary;
import com.marklogic.client.query.MatchLocation;
import com.marklogic.client.query.MatchSnippet;
import rs.ac.uns.sw.xml.util.search_wrapper.Metadata;
import rs.ac.uns.sw.xml.util.search_wrapper.SearchObject;
import rs.ac.uns.sw.xml.util.search_wrapper.SearchResult;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static rs.ac.uns.sw.xml.util.DateUtil.DATE_FORMAT;

public class ResultHandler {

    private static final String MESSAGE = "Unable to create Product Handle...";
    public static final String EMPTY_STRING = "";

    private Class targetClass;

    private XMLDocumentManager documentManager;

    public ResultHandler(Class targetClass, XMLDocumentManager documentManager) {
        this.targetClass = targetClass;
        this.documentManager = documentManager;
    }

    public SearchResult toSearchResult(SearchHandle resultHandler, JsonNode properties) {
        List<SearchObject> products = new ArrayList<>();

        for (MatchDocumentSummary summary : resultHandler.getMatchResults()) {
            List<Object> preview = new ArrayList<>();

            for (final MatchLocation location : summary.getMatchLocations()) {
                for (final MatchSnippet snippet : location.getSnippets()) {
                    String text = snippet.getText().trim();
                    if (!EMPTY_STRING.equals(text)) {
                        preview.add(text);
                    }
                }
            }

            Metadata metadata = null;
            boolean exists = false;

            if (properties != null) {
                for (final JsonNode property : properties) {
                    final String path = property.path("law").path("value").asText();

                    if (path.endsWith(formatPath(summary.getPath()))) {
                        exists = true;

                        // Votes
                        final Integer votesFor = property.path("votes_for").path("value").asInt();
                        final Integer votesAgaist = property.path("votes_against").path("value").asInt();
                        final Integer votesNeutral = property.path("votes_neutral").path("value").asInt();

                        // Dates
                        Date proposalDate = null;
                        Date votingDate = null;
                        try {
                            proposalDate = DATE_FORMAT.parse(property.path("proposal_date").path("value").asText());
                            votingDate = DATE_FORMAT.parse(property.path("voting_date").path("value").asText());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        // Status
                        final String status = property.path("status").path("value").asText();

                        metadata = new Metadata(votesFor, votesAgaist, votesNeutral, proposalDate, votingDate, status);

                        break;
                    }
                }
            }

            JAXBHandle contentHandle = getProductHandle();
            documentManager.read(summary.getUri(), contentHandle);

            if (exists || properties == null) {
                products.add(new SearchObject(summary.getConfidence(), summary.getPath(), preview, metadata));
            }
        }

        return new SearchResult(products);
    }

    public SearchResult toSearchResult(JsonNode properties) {
        List<SearchObject> products = new ArrayList<>();

        Metadata metadata = null;
        for (final JsonNode property : properties) {
            final String path = property.path("law").path("value").asText();

            // Votes
            final Integer votesFor = property.path("votes_for").path("value").asInt();
            final Integer votesAgaist = property.path("votes_against").path("value").asInt();
            final Integer votesNeutral = property.path("votes_neutral").path("value").asInt();

            // Dates
            Date proposalDate = null;
            Date votingDate = null;
            try {
                proposalDate = DATE_FORMAT.parse(property.path("proposal_date").path("value").asText());
                votingDate = DATE_FORMAT.parse(property.path("voting_date").path("value").asText());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            // Status
            final String status = property.path("status").path("value").asText();

            metadata = new Metadata(votesFor, votesAgaist, votesNeutral, proposalDate, votingDate, status);

            products.add(new SearchObject(null, path, null, metadata));
        }

        return new SearchResult(products);
    }

    private JAXBHandle getProductHandle() {
        try {
            JAXBContext context = JAXBContext.newInstance(targetClass);
            return new JAXBHandle(context);
        } catch (JAXBException e) {
            throw new RuntimeException(MESSAGE, e);
        }
    }

    private String formatPath(final String path) {
        return (path.split("\"")[1]).split("\\.")[0];
    }
}
