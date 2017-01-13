package rs.ac.uns.sw.xml.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.sw.xml.domain.Amendments;
import rs.ac.uns.sw.xml.repository.AmendmentsRepositoryXML;

import java.util.List;

@Service
public class AmendmentsServiceXML {

    @Autowired
    AmendmentsRepositoryXML repositoryXML;

    public Amendments create(Amendments amendments) {
        return repositoryXML.save(amendments);
    }

    public List<String> findByProposer(String proposer) { return repositoryXML.findAmendmentsByProposer(proposer);}

    public void deleteAmendmentsById(String id) {
        repositoryXML.deleteAmendments(id);
    }

    public Amendments updateAmendmentsStatus(String id, String status) {
        return repositoryXML.updateAmendmentsStatus(id, status);
    }

    public Amendments getOneById(String id) {
        return repositoryXML.findAmendmentById(id);
    }
}
