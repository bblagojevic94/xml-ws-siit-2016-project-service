package rs.ac.uns.sw.xml.repository;


import com.marklogic.client.DatabaseClient;
import com.marklogic.client.document.DocumentDescriptor;
import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.io.JAXBHandle;
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
        documentManager.write(getDocumentId(parliament.getId()), handler.getMetadata(), handler.getContentHandle());

        return parliament;
    }

    public boolean parliamentExists(String id) {
        DocumentDescriptor descriptor = documentManager.exists(getDocumentId(id));
        if (descriptor != null) {
            return true;
        }
        return false;
    }

    public Parliament findParliamentById(String id) {
        if (parliamentExists(id)) {
            JAXBHandle contentHandle = RepositoryUtil.getObjectHandle(Parliament.class);
            JAXBHandle result = documentManager.read(getDocumentId(id), contentHandle);

            return (Parliament) result.get(Parliament.class);
        }
        return null;
    }

    private String getDocumentId(String value) {
        return String.format("/parliaments/%s.xml", value);
    }
}
