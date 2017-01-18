package rs.ac.uns.sw.xml.states;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import rs.ac.uns.sw.xml.domain.Amendments;
import rs.ac.uns.sw.xml.domain.Law;
import rs.ac.uns.sw.xml.domain.Parliament;
import rs.ac.uns.sw.xml.service.AmendmentsServiceXML;
import rs.ac.uns.sw.xml.service.LawServiceXML;
import rs.ac.uns.sw.xml.util.Constants;
import rs.ac.uns.sw.xml.util.StateConstants;
import rs.ac.uns.sw.xml.util.voting_wrapper.VotingObject;

import static rs.ac.uns.sw.xml.util.HeaderUtil.forbiddenActionFromState;
import static rs.ac.uns.sw.xml.util.StatesUtil.addAgenda;
import static rs.ac.uns.sw.xml.util.StatesUtil.removeAgenda;

public class InProgressState implements State {

    private LawServiceXML lawServiceXML;
    private AmendmentsServiceXML amendmentsServiceXML;

    public InProgressState(LawServiceXML lawServiceXML, AmendmentsServiceXML amendmentsServiceXML) {
        this.lawServiceXML = lawServiceXML;
        this.amendmentsServiceXML = amendmentsServiceXML;
    }

    @Override
    public ResponseEntity<?> suggestLaw(Law law, Parliament parliament) {
        return forbiddenActionFromState(StateConstants.ParliamentStates.IN_PROGRESS_STATE);
    }

    @Override
    public ResponseEntity<?> suggestAmendments(Amendments amendments, Parliament parliament) {
        return forbiddenActionFromState(StateConstants.ParliamentStates.IN_PROGRESS_STATE);
    }

    @Override
    public ResponseEntity<?> updateLawStatus(String id, String status, Parliament parliament) {
        if (Constants.LawsStates.ACCEPTED.equals(status) || Constants.LawsStates.REJECTED.equals(status)) {
            final Law result = lawServiceXML.updateLawStatus(id, status);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return forbiddenActionFromState(StateConstants.ParliamentStates.IN_PROGRESS_STATE);
    }

    @Override
    public ResponseEntity<?> updateAmendmentStatus(String id, String status, Parliament parliament) {
        if (Constants.AmendmentsStates.ACCEPTED.equals(status) || Constants.AmendmentsStates.REJECTED.equals(status)) {
            final Amendments result = amendmentsServiceXML.updateAmendmentsStatus(id, status);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return forbiddenActionFromState(StateConstants.ParliamentStates.IN_PROGRESS_STATE);
    }

    @Override
    public ResponseEntity<?> updateLawVoting(String id, VotingObject voting) {
        final Law result = lawServiceXML.updateVotes(id, voting);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateAmendmentVoting(String id, VotingObject voting) {
        final Amendments result= amendmentsServiceXML.updateVoting(id, voting);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
