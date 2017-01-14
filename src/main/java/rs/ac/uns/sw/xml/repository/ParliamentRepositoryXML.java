package rs.ac.uns.sw.xml.repository;


import com.marklogic.client.DatabaseClient;
import com.marklogic.client.document.XMLDocumentManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rs.ac.uns.sw.xml.config.MarkLogicConstants;
import rs.ac.uns.sw.xml.domain.Parliament;
import rs.ac.uns.sw.xml.util.RepositoryUtil;

@Component
public class ParliamentRepositoryXML {

    @Autowired
    XMLDocumentManager documentManager;

    @Autowired
    DatabaseClient databaseClient;

    public Parliament save(Parliament parliament) {
        RepositoryUtil.ResultDocumentHandler handler = RepositoryUtil.documentHandle(MarkLogicConstants.Collections.PARLIAMENTS, Parliament.class);

        handler.getContentHandle().set(parliament);
        String data = handler.getContentHandle().toString();

        documentManager.write(getDocumentId(parliament.getId()), handler.getMetadata(), handler.getContentHandle());

        return parliament;
    }

    private String getDocumentId(String value) {
        return String.format("/parliaments/%s.xml", value);
    }
}
