package rs.ac.uns.sw.xml.config;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;
import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.io.JAXBHandle;
import com.marklogic.client.query.QueryManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rs.ac.uns.sw.xml.domain.Glava;

import javax.xml.bind.JAXBException;

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
            DatabaseClientFactory.getHandleRegistry()
                    .register(JAXBHandle.newFactory(Glava.class));
        }
        catch (JAXBException e) {
            e.printStackTrace();
        }

        return DatabaseClientFactory.newClient(host, port, username, password, DatabaseClientFactory.Authentication.DIGEST);
    }

    @Bean
    public QueryManager getQueryManager() {
        return getDatabaseClient().newQueryManager();
    }

    @Bean
    public XMLDocumentManager getXMLDocumentManager() {
        return getDatabaseClient().newXMLDocumentManager();
    }

}
