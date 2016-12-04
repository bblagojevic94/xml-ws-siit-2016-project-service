package rs.ac.uns.sw.xml.repository;

import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.io.DocumentMetadataHandle;
import com.marklogic.client.io.JAXBHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rs.ac.uns.sw.xml.config.MarkLogicConstants;
import rs.ac.uns.sw.xml.domain.Law;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

@Component
public class LawRepositoryXML {

    @Autowired
    XMLDocumentManager documentManager;

    public Law save(Law law) {
        DocumentMetadataHandle metadata = new DocumentMetadataHandle();

        metadata.getCollections().add(MarkLogicConstants.COLLECTION_LAWS);

        JAXBHandle contentHandle = getProductHandle();
        contentHandle.set(law);

        documentManager.write(getDocumentId(law.getName()), metadata, contentHandle);

        return findLawByName(law.getName());
    }

    public Law findLawByName(String name) {
        JAXBHandle contentHandle = getProductHandle();
        JAXBHandle result = documentManager.read(getDocumentId(name), contentHandle);

        return (Law) result.get(Law.class);
    }

    private JAXBHandle getProductHandle() {
        try {
            JAXBContext context = JAXBContext.newInstance(Law.class);
            return new JAXBHandle(context);
        } catch (JAXBException e) {
            throw new RuntimeException("Unable to create Product Handle...", e);
        }
    }

    private String getDocumentId(String value) {
        return String.format("/laws/%s.xml", value);
    }

}