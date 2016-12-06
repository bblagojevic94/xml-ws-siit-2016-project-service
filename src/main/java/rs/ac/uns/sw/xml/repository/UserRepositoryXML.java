package rs.ac.uns.sw.xml.repository;


import com.marklogic.client.DatabaseClient;
import com.marklogic.client.document.DocumentPage;
import com.marklogic.client.document.DocumentRecord;
import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.io.JAXBHandle;
import com.marklogic.client.query.QueryDefinition;
import com.marklogic.client.query.QueryManager;
import com.marklogic.client.query.StructuredQueryBuilder;
import com.marklogic.client.util.EditableNamespaceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rs.ac.uns.sw.xml.config.MarkLogicConstants;
import rs.ac.uns.sw.xml.domain.AppUser;
import rs.ac.uns.sw.xml.util.RepositoryUtil;

import javax.xml.namespace.QName;

import static rs.ac.uns.sw.xml.config.MarkLogicConstants.*;

@Component
public class UserRepositoryXML {

    @Autowired
    XMLDocumentManager documentManager;

    @Autowired
    QueryManager queryManager;

    @Autowired
    DatabaseClient databaseClient;

    public AppUser save(AppUser document) {
        RepositoryUtil.ResultDocumentHandler handler = RepositoryUtil.documentHandle(COLLECTION_USERS, AppUser.class);

        handler.getContentHandle().set(document);

        documentManager.write(getDocumentId(document.getId()), handler.getMetadata(), handler.getContentHandle());

        return findById(document.getId());
    }

    public AppUser findById(Long id) {
        JAXBHandle contentHandle = RepositoryUtil.getObjectHandle(AppUser.class);
        JAXBHandle result = documentManager.read(getDocumentId(id), contentHandle);
        return (AppUser) result.get(AppUser.class);
    }

    private String getDocumentId(Long id) {
        return String.format("/api_users/%s.xml", id);
    }

    public AppUser findByEmail(String email) {
        StructuredQueryBuilder qb = queryManager.newStructuredQueryBuilder();

        // Defining namespace mappings
        EditableNamespaceContext namespaces = new EditableNamespaceContext();
        namespaces.put("kor", MarkLogicConstants.Namespaces.USERS);

        qb.setNamespaces(namespaces);

        QueryDefinition query = qb.and(
                qb.collection("/api_users.xml"),
                // search by element with elementName=email, namespace=http://www.parlament.gov.rs/schema/korisnici, prefix=kor
                qb.word(qb.element(new QName(MarkLogicConstants.Namespaces.USERS, "email", "kor")), email)
        );

        DocumentPage page = documentManager.search(query, 1);

        for (DocumentRecord doc : page) {
            AppUser content = doc.getContentAs(AppUser.class);
            return content;
        }

        return null;
    }


}
