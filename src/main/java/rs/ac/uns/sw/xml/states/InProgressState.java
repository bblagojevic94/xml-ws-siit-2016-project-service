package rs.ac.uns.sw.xml.states;


import org.springframework.http.ResponseEntity;
import rs.ac.uns.sw.xml.domain.Amendments;
import rs.ac.uns.sw.xml.domain.Law;
import rs.ac.uns.sw.xml.domain.Parliament;
import rs.ac.uns.sw.xml.service.AmendmentsServiceXML;
import rs.ac.uns.sw.xml.service.LawServiceXML;
import rs.ac.uns.sw.xml.util.StateConstants;

import static rs.ac.uns.sw.xml.util.HeaderUtil.forbiddenActionFromState;

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
    public ResponseEntity<?> withdrawalLaw(Law law) {
        return forbiddenActionFromState(StateConstants.ParliamentStates.IN_PROGRESS_STATE);
    }

    @Override
    public ResponseEntity<?> withdrawalAmendments(Amendments amendments) {
        return forbiddenActionFromState(StateConstants.ParliamentStates.IN_PROGRESS_STATE);
    }

    @Override
    public ResponseEntity<?> voting(int votesFor, int votesAgainst, int votesNeutral) {
        return null;
    }
}
