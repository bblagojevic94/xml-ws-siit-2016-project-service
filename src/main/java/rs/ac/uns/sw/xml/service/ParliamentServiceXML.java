package rs.ac.uns.sw.xml.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.sw.xml.domain.Parliament;
import rs.ac.uns.sw.xml.repository.ParliamentRepositoryXML;

@Service
public class ParliamentServiceXML {

    @Autowired
    ParliamentRepositoryXML repositoryXML;

    public Parliament create(Parliament parliament) {
        return repositoryXML.save(parliament);
    }

    public Parliament findOneById(String id) {
        return repositoryXML.findParliamentById(id);
    }
}
