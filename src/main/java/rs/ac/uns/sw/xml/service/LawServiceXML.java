package rs.ac.uns.sw.xml.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.sw.xml.domain.Law;
import rs.ac.uns.sw.xml.domain.wrapper.SearchResult;
import rs.ac.uns.sw.xml.repository.LawRepositoryXML;

@Service
public class LawServiceXML {

    @Autowired
    LawRepositoryXML repositoryXML;

    public Law create(Law law) {
        return repositoryXML.save(law);
    }

    public SearchResult getAll() {
        return repositoryXML.findAll();
    }

    public Law getOneByName(String name) {
        return repositoryXML.findLawByName(name);
    }

}
