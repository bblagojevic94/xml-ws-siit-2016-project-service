package rs.ac.uns.sw.xml.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.sw.xml.domain.Glava;
import rs.ac.uns.sw.xml.domain.wrapper.SearchResult;
import rs.ac.uns.sw.xml.repository.GlavaRepositoryXML;

@Service
public class GlavaServiceXML {

    @Autowired
    GlavaRepositoryXML repositoryXML;

    public Glava create(Glava g) {
        return repositoryXML.save(g);
    }

    public SearchResult getAll() {
        return repositoryXML.findAll();
    }

    public SearchResult findByOdjeljakContains(String query) {
        return repositoryXML.findByOdjeljakContains(query);
    }
}
