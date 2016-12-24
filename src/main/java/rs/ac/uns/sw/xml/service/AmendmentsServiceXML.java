package rs.ac.uns.sw.xml.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.sw.xml.domain.Amendments;
import rs.ac.uns.sw.xml.repository.AmendmentsRepositoryXML;

@Service
public class AmendmentsServiceXML {

    @Autowired
    AmendmentsRepositoryXML repositoryXML;

    public Amendments create(Amendments amendments) {
        return repositoryXML.save(amendments);
    }
}
