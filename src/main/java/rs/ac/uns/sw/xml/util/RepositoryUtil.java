package rs.ac.uns.sw.xml.util;

import com.marklogic.client.io.DocumentMetadataHandle;
import com.marklogic.client.io.JAXBHandle;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

public class RepositoryUtil {

    public static ResultDocumentHandler documentHandle(String collection, Class neededClass) {
        DocumentMetadataHandle metadata = new DocumentMetadataHandle();
        metadata.getCollections().add(collection);

        JAXBHandle contentHandle = getObjectHandle(neededClass);

        return new ResultDocumentHandler(metadata, contentHandle);
    }


    public static JAXBHandle getObjectHandle(Class neededClass) {
        try {
            JAXBContext context = JAXBContext.newInstance(neededClass);
            return new JAXBHandle(context);
        } catch (JAXBException e) {
            throw new RuntimeException("Unable to create User Handle...", e);
        }
    }

    public static class ResultDocumentHandler{

        private DocumentMetadataHandle metadata;
        private JAXBHandle contentHandle;

        public ResultDocumentHandler(DocumentMetadataHandle metadata, JAXBHandle contentHandle) {
            this.metadata = metadata;
            this.contentHandle = contentHandle;
        }

        public DocumentMetadataHandle getMetadata() {
            return metadata;
        }

        public void setMetadata(DocumentMetadataHandle metadata) {
            this.metadata = metadata;
        }

        public JAXBHandle getContentHandle() {
            return contentHandle;
        }

        public void setContentHandle(JAXBHandle contentHandle) {
            this.contentHandle = contentHandle;
        }
    }
}
