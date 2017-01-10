package rs.ac.uns.sw.xml.util;

import com.marklogic.client.io.DocumentMetadataHandle;
import com.marklogic.client.io.JAXBHandle;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

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
            throw new RuntimeException(getExceptionMessage(neededClass), e);
        }
    }

    public static String toXmlString(Object object, Class neededClass) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(neededClass);
        Marshaller marshaller = context.createMarshaller();

        StringWriter writer = new StringWriter();
        marshaller.marshal(object, writer);

        return writer.toString();
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

    private static String getExceptionMessage(Class neededClass) {
        return "Unable to create " + neededClass.getName() + " Handle...";
    }
}
