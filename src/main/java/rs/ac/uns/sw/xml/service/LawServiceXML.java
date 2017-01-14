package rs.ac.uns.sw.xml.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.sw.xml.domain.Amendments;
import rs.ac.uns.sw.xml.domain.Law;
import rs.ac.uns.sw.xml.repository.LawRepositoryXML;
import rs.ac.uns.sw.xml.util.MetaSearchWrapper;
import rs.ac.uns.sw.xml.util.search_wrapper.SearchResult;

@Service
public class LawServiceXML {

    @Autowired
    LawRepositoryXML repositoryXML;

    public Law create(Law law) {
        return repositoryXML.save(law);
    }

    public Law updateWithAmendments(Amendments amendments) {
        return repositoryXML.updateLawWithAmendments(amendments);
    }

    public void deleteLawById(String id) {
        repositoryXML.deleteLaw(id);
    }

    public Law updateLawStatus(String id, String status) {
        return repositoryXML.updateLawStatus(id, status);
    }

    public SearchResult getAll() {
        return repositoryXML.findAll();
    }

    public Law getOneById(String id) {
        return repositoryXML.findLawById(id);
    }

    public SearchResult getAllByQueryAndMetadata(String query, MetaSearchWrapper searchWrapper) {
        return repositoryXML.findAllByQueryAndMetadata(query, searchWrapper);
    }

    public JsonNode getMetadataJSON() {
        return repositoryXML.getMetadataJSON();
    }

    public String getMetadataTriples() {
        return repositoryXML.getMetadataTriples();
    }
}
