package rs.ac.uns.sw.xml.config;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;
import com.marklogic.client.document.DocumentPatchBuilder;
import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.io.JAXBHandle;
import com.marklogic.client.query.QueryManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rs.ac.uns.sw.xml.domain.Amendments;
import rs.ac.uns.sw.xml.domain.AppUser;
import rs.ac.uns.sw.xml.domain.Law;
import rs.ac.uns.sw.xml.domain.Parliament;

import javax.xml.bind.JAXBException;

import static com.marklogic.client.DatabaseClientFactory.Authentication.DIGEST;

@Configuration
public class MarkLogicConfiguration {

    @Value("${marklogic.host}")
    private String host;

    @Value("${marklogic.port}")
    private int port;

    @Value("${marklogic.user}")
    private String username;

    @Value("${marklogic.pass}")
    private String password;

    @Bean
    public DatabaseClient getDatabaseClient() {
        try {
            DatabaseClientFactory.HandleFactoryRegistry registry = DatabaseClientFactory.getHandleRegistry();

            registry.register(JAXBHandle.newFactory(Law.class));
            registry.register(JAXBHandle.newFactory(AppUser.class));
            registry.register(JAXBHandle.newFactory(Amendments.class));
            registry.register(JAXBHandle.newFactory(Parliament.class));
        }
        catch (JAXBException e) {
            e.printStackTrace();
        }

        //noinspection deprecation
        return DatabaseClientFactory.newClient(host, port, username, password, DIGEST);
    }

    @Bean
    public QueryManager getQueryManager() {
        return getDatabaseClient().newQueryManager();
    }

    @Bean
    public XMLDocumentManager getXMLDocumentManager() {
        return getDatabaseClient().newXMLDocumentManager();
    }

    @Bean
    public DocumentPatchBuilder getDocumentPatchBuilder() {
        return getXMLDocumentManager().newPatchBuilder();
    }

}
