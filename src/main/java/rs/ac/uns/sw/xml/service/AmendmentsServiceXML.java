package rs.ac.uns.sw.xml.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.sw.xml.domain.Amendments;
import rs.ac.uns.sw.xml.repository.AmendmentsRepositoryXML;

import java.util.List;

import static rs.ac.uns.sw.xml.util.PredicatesConstants.SUGGESTED;

@Service
public class AmendmentsServiceXML {

    @Autowired
    AmendmentsRepositoryXML repositoryXML;

    public Amendments create(Amendments amendments) {
        return repositoryXML.save(amendments);
    }

    public List<String> findByProposer(String proposer) { return repositoryXML.findAmendmentsByProposer(proposer);}
}
