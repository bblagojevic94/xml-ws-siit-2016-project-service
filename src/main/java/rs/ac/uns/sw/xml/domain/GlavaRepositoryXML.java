package rs.ac.uns.sw.xml.domain;

import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.io.DocumentMetadataHandle;
import com.marklogic.client.io.JAXBHandle;
import com.marklogic.client.query.QueryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

@Component
public class GlavaRepositoryXML {

    public static final String COLLECTION_REF = "/glave.xml";

    @Autowired
    XMLDocumentManager documentManager;

    @Autowired
    QueryManager queryManager;

    public Glava save(Glava document) {
        DocumentMetadataHandle metadata = new DocumentMetadataHandle();
        metadata.getCollections().add(COLLECTION_REF);
        System.out.println(metadata.getCollections());

        JAXBHandle contentHandle = getProductHandle();
        System.out.println("ContentHandle");
        if (contentHandle == null)
            System.out.println("Jest enull");

        contentHandle.set(document);

        System.out.println("test");
        System.out.println(getDocumentId(document.getId()));
        documentManager.write(getDocumentId(document.getId()), metadata, contentHandle);

        return document;
    }

    public Glava findById(String id) {
        JAXBHandle contentHandle = getProductHandle();
        JAXBHandle result = documentManager.read(getDocumentId(id), contentHandle);
        return (Glava) result.get(Glava.class);
    }

    private JAXBHandle getProductHandle() {
        try {
            JAXBContext context = JAXBContext.newInstance(Glava.class);
            return new JAXBHandle(context);
        } catch (JAXBException e) {
            // throw new RuntimeException("Unable to create...", e);
            return null;
        }
    }

    private String getDocumentId(String id) {
        return String.format("/glave/%s.xml", id);
    }
}
