package rs.ac.uns.sw.xml.repository;


import com.marklogic.client.DatabaseClient;
import com.marklogic.client.document.DocumentPatchBuilder;
import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.io.JAXBHandle;
import com.marklogic.client.io.marker.DocumentPatchHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rs.ac.uns.sw.xml.config.MarkLogicConstants;
import rs.ac.uns.sw.xml.domain.Parliament;
import rs.ac.uns.sw.xml.domain.Ref;
import rs.ac.uns.sw.xml.util.RepositoryUtil;

import javax.xml.bind.JAXBException;

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

    public Parliament findParliamentById(String id) {
        JAXBHandle contentHandle = RepositoryUtil.getObjectHandle(Parliament.class);
        JAXBHandle result = documentManager.read(getDocumentId(id), contentHandle);

        return (Parliament) result.get(Parliament.class);
    }

    private String getDocumentId(String value) {
        return String.format("/parliaments/%s.xml", value);
    }
}
