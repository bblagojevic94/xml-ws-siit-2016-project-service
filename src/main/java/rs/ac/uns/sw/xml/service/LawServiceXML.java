package rs.ac.uns.sw.xml.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.sw.xml.domain.Amendments;
import rs.ac.uns.sw.xml.domain.Law;
import rs.ac.uns.sw.xml.util.search_wrapper.SearchResult;
import rs.ac.uns.sw.xml.repository.LawRepositoryXML;
import rs.ac.uns.sw.xml.util.MetaSearchWrapper;

import javax.xml.transform.stream.StreamResult;

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

    public SearchResult getAll() {
        return repositoryXML.findAll();
    }

    public Law getOneById(String id) {
        return repositoryXML.findLawById(id);
    }

    public JsonNode searchLaws(MetaSearchWrapper searchWrapper) {
        return repositoryXML.searchMetadata(searchWrapper);
    }

    public SearchResult getAllByQuery(String query) {
        return repositoryXML.findAllByQuery(query);
    }

    public JsonNode getMetadataJSON() {
        return repositoryXML.getMetadataJSON();
    }

    public StreamResult getMetadataTriples() {
        return repositoryXML.getMetadataTriples();
    }
}
