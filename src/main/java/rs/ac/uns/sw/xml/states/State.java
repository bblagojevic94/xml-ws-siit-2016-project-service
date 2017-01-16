package rs.ac.uns.sw.xml.states;


import org.springframework.http.ResponseEntity;
import rs.ac.uns.sw.xml.domain.Amendments;
import rs.ac.uns.sw.xml.domain.Law;
import rs.ac.uns.sw.xml.domain.Parliament;
import rs.ac.uns.sw.xml.util.voting_wrapper.VotingObject;

public interface State {

    ResponseEntity<?> suggestLaw(Law law, Parliament parliament);

    ResponseEntity<?> suggestAmendments(Amendments amendments, Parliament parliament);

    ResponseEntity<?> updateLawStatus(String id, String status, Parliament parliament);

    ResponseEntity<?> updateAmendmentStatus(String id, String status, Parliament parliament);

    ResponseEntity<?> updateLawVoting(String id, VotingObject voting);

    ResponseEntity<?> updateAmendmentVoting(String id, VotingObject voting);
}
