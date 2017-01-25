package rs.ac.uns.sw.xml.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.sw.xml.domain.Amendment;
import rs.ac.uns.sw.xml.domain.Amendments;
import rs.ac.uns.sw.xml.repository.AmendmentsRepositoryXML;
import rs.ac.uns.sw.xml.util.search_wrapper.SearchResult;
import rs.ac.uns.sw.xml.util.voting_wrapper.VotingObject;

import java.util.List;

@Service
public class AmendmentsServiceXML {

    @Autowired
    AmendmentsRepositoryXML repositoryXML;

    public Amendments create(Amendments amendments) {
        return repositoryXML.save(amendments);
    }

    public SearchResult findByProposer(String proposer) {
        return repositoryXML.findAmendmentsByProposer(proposer);
    }

    public void deleteAmendmentsById(String id) {
        repositoryXML.deleteAmendments(id);
    }

    public Amendments updateAmendmentsStatus(String id, String status) {
        return repositoryXML.updateAmendmentsStatus(id, status);
    }

    public Amendments getOneById(String id) {
        return repositoryXML.findAmendmentById(id);
    }

    public boolean amendmentsExists(String id) {
        return repositoryXML.amendmentsExists(id);
    }

    public Amendments updateVoting(String id, VotingObject voting){
        return repositoryXML.updateAmendmentsVotes(id, voting);
    }

    public SearchResult findByLaw(String lawId) {
        return repositoryXML.findByLaw(lawId);
    }

    public String getMetadataJSON(String amendmentsId){
        return repositoryXML.getMetadataJSONByAmendment(amendmentsId);
    }

    public String getMetadataXML(String amendmentsId){
        return repositoryXML.getMetadataXMLByAmendment(amendmentsId);
    }

}
