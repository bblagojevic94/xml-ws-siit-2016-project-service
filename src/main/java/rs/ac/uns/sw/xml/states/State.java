package rs.ac.uns.sw.xml.states;


import org.springframework.http.ResponseEntity;
import rs.ac.uns.sw.xml.domain.Amendments;
import rs.ac.uns.sw.xml.domain.Law;
import rs.ac.uns.sw.xml.domain.Parliament;

public interface State {

    ResponseEntity<?> suggestLaw(Law law, Parliament parliament);

    ResponseEntity<?> suggestAmendments(Amendments amendments, Parliament parliament);

    ResponseEntity<?> withdrawalLaw(Law law);

    ResponseEntity<?> withdrawalAmendments(Amendments amendments);

    ResponseEntity<?> voting(int votesFor, int votesAgainst, int votesNeutral);
}
