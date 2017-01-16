package rs.ac.uns.sw.xml.states;


import org.springframework.http.ResponseEntity;
import rs.ac.uns.sw.xml.domain.Amendments;
import rs.ac.uns.sw.xml.domain.Law;
import rs.ac.uns.sw.xml.domain.Parliament;
import rs.ac.uns.sw.xml.util.StateConstants;
import rs.ac.uns.sw.xml.util.voting_wrapper.VotingObject;

import static rs.ac.uns.sw.xml.util.HeaderUtil.forbiddenActionFromState;

public class FinishedState implements State {

    @Override
    public ResponseEntity<?> suggestLaw(Law law, Parliament parliament) {
        return forbiddenActionFromState(StateConstants.ParliamentStates.FINISHED_STATE);
    }

    @Override
    public ResponseEntity<?> suggestAmendments(Amendments amendments, Parliament parliament) {
        return forbiddenActionFromState(StateConstants.ParliamentStates.FINISHED_STATE);
    }

    @Override
    public ResponseEntity<?> updateLawStatus(String id, String status, Parliament parliament) {
        return forbiddenActionFromState(StateConstants.ParliamentStates.FINISHED_STATE);
    }

    @Override
    public ResponseEntity<?> updateAmendmentStatus(String id, String status, Parliament parliament) {
        return forbiddenActionFromState(StateConstants.ParliamentStates.FINISHED_STATE);
    }

    @Override
    public ResponseEntity<?> updateLawVoting(String id, VotingObject voting) {
        return forbiddenActionFromState(StateConstants.ParliamentStates.FINISHED_STATE);
    }

    @Override
    public ResponseEntity<?> updateAmendmentVoting(String id, VotingObject voting) {
        return forbiddenActionFromState(StateConstants.ParliamentStates.FINISHED_STATE);
    }
}