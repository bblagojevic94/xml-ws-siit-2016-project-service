package rs.ac.uns.sw.xml;


import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;
import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.io.JAXBHandle;
import com.marklogic.client.query.QueryManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import rs.ac.uns.sw.xml.domain.Glava;

import javax.xml.bind.JAXBException;

@SpringBootApplication
public class XmlWsProject2016Application {

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
            System.out.println("Database Client initialized...");
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

    public static void main(String[] args) {
        SpringApplication.run(XmlWsProject2016Application.class, args);
    }
}
