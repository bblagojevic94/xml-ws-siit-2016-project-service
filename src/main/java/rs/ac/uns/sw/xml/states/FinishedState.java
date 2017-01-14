package rs.ac.uns.sw.xml.states;


import org.springframework.http.ResponseEntity;
import rs.ac.uns.sw.xml.domain.Amendments;
import rs.ac.uns.sw.xml.domain.Law;
import rs.ac.uns.sw.xml.domain.Parliament;
import rs.ac.uns.sw.xml.service.AmendmentsServiceXML;
import rs.ac.uns.sw.xml.service.LawServiceXML;
import rs.ac.uns.sw.xml.util.StateConstants;

import static rs.ac.uns.sw.xml.util.HeaderUtil.wrongState;

public class FinishedState implements State {

    private LawServiceXML lawServiceXML;
    private AmendmentsServiceXML amendmentsServiceXML;

    public FinishedState(LawServiceXML lawServiceXML, AmendmentsServiceXML amendmentsServiceXML) {
        this.lawServiceXML = lawServiceXML;
        this.amendmentsServiceXML = amendmentsServiceXML;
    }

    @Override
    public ResponseEntity<?> suggestLaw(Law law, Parliament parliament) {
        return wrongState(StateConstants.ParliamentStates.FINISHED_STATE);
    }

    @Override
    public ResponseEntity<?> suggestAmendments(Amendments amendments, Parliament parliament) {
        return wrongState(StateConstants.ParliamentStates.FINISHED_STATE);
    }

    @Override
    public ResponseEntity<?> withdrawalLaw(Law law) {
        return wrongState(StateConstants.ParliamentStates.FINISHED_STATE);
    }

    @Override
    public ResponseEntity<?> withdrawalAmendments(Amendments amendments) {
        return wrongState(StateConstants.ParliamentStates.FINISHED_STATE);
    }

    @Override
    public ResponseEntity<?> voting(int votesFor, int votesAgainst, int votesNeutral) {
        return wrongState(StateConstants.ParliamentStates.FINISHED_STATE);
    }
}