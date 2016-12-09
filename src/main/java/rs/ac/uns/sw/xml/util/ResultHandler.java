package rs.ac.uns.sw.xml.util;

import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.io.JAXBHandle;
import com.marklogic.client.io.SearchHandle;
import com.marklogic.client.query.MatchDocumentSummary;
import rs.ac.uns.sw.xml.domain.wrapper.SearchResult;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.List;

public class ResultHandler {

    private static final String MESSAGE = "Unable to create Product Handle...";

    private Class targetClass;

    private XMLDocumentManager documentManager;

    public ResultHandler(Class targetClass, XMLDocumentManager documentManager) {
        this.targetClass = targetClass;
        this.documentManager = documentManager;
    }

    public SearchResult toSearchResult(SearchHandle resultHandler) {
        List<Object> products = new ArrayList<>();
        for (MatchDocumentSummary summary: resultHandler.getMatchResults()) {
            JAXBHandle contentHandle = getProductHandle();
            documentManager.read(summary.getUri(), contentHandle);
            products.add(contentHandle.get(targetClass));
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
}
