package rs.ac.uns.sw.xml.repository;


import com.marklogic.client.DatabaseClient;
import com.marklogic.client.document.DocumentDescriptor;
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

import static rs.ac.uns.sw.xml.config.MarkLogicConstants.Prefixes.USERS_PREF;

@Component
public class UserRepositoryXML {

    public static final String USERNAME = "korisnicko_ime";
    @Autowired
    XMLDocumentManager documentManager;

    @Autowired
    QueryManager queryManager;

    @Autowired
    DatabaseClient databaseClient;

    public AppUser save(AppUser document) {
        RepositoryUtil.ResultDocumentHandler handler = RepositoryUtil.documentHandle(MarkLogicConstants.Collections.USERS, AppUser.class);

        handler.getContentHandle().set(document);

        documentManager.write(getDocumentId(document.getId()), handler.getMetadata(), handler.getContentHandle());

        return findById(document.getId());
    }

    public boolean userExists(Long id) {
        DocumentDescriptor descriptor = documentManager.exists(getDocumentId(id));
        if (descriptor != null) {
            return true;
        }
        return false;
    }

    public AppUser findById(Long id) {
        if (userExists(id)) {
            JAXBHandle contentHandle = RepositoryUtil.getObjectHandle(AppUser.class);
            JAXBHandle result = documentManager.read(getDocumentId(id), contentHandle);

            return (AppUser) result.get(AppUser.class);
        }
        return null;
    }

    public AppUser findOneByUsername(String username) {
        StructuredQueryBuilder qb = queryManager.newStructuredQueryBuilder();

        // Defining namespace mappings
        EditableNamespaceContext namespaces = new EditableNamespaceContext();
        namespaces.put(USERS_PREF, MarkLogicConstants.Namespaces.USERS);

        qb.setNamespaces(namespaces);

        QueryDefinition query = qb.and(
                qb.collection("/api_users.xml"),

                // search by element with elementName=email, namespace=http://www.parlament.gov.rs/schema/korisnici, prefix=kor
                qb.word(qb.element(new QName(MarkLogicConstants.Namespaces.USERS, USERNAME, USERS_PREF)), username)
        );

        DocumentPage page = documentManager.search(query, 1);

        for (DocumentRecord doc : page) {
            AppUser content = doc.getContentAs(AppUser.class);
            return content;
        }

        return null;
    }

    private String getDocumentId(Long id) {
        return String.format("/api_users/%s.xml", id);
    }

}
