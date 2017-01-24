package rs.ac.uns.sw.xml.states;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import rs.ac.uns.sw.xml.domain.Amendments;
import rs.ac.uns.sw.xml.domain.Law;
import rs.ac.uns.sw.xml.domain.Parliament;
import rs.ac.uns.sw.xml.domain.Ref;
import rs.ac.uns.sw.xml.service.AmendmentsServiceXML;
import rs.ac.uns.sw.xml.service.LawServiceXML;
import rs.ac.uns.sw.xml.service.ParliamentServiceXML;
import rs.ac.uns.sw.xml.util.Constants;
import rs.ac.uns.sw.xml.util.HeaderUtil;
import rs.ac.uns.sw.xml.util.StateConstants;
import rs.ac.uns.sw.xml.util.voting_wrapper.VotingObject;

import static rs.ac.uns.sw.xml.util.HeaderUtil.forbiddenActionFromState;
import static rs.ac.uns.sw.xml.util.StatesUtil.addAgenda;
import static rs.ac.uns.sw.xml.util.StatesUtil.removeAgenda;


public class ConvenedState implements State {

    private LawServiceXML lawServiceXML;
    private AmendmentsServiceXML amendmentsServiceXML;
    private ParliamentServiceXML parliamentServiceXML;


    public ConvenedState(LawServiceXML lawServiceXML, AmendmentsServiceXML amendmentsServiceXML, ParliamentServiceXML parliamentServiceXML, Parliament parliament) {
        this.lawServiceXML = lawServiceXML;
        this.amendmentsServiceXML = amendmentsServiceXML;
        this.parliamentServiceXML = parliamentServiceXML;
    }

    @Override
    public ResponseEntity<?> suggestLaw(Law law, Parliament parliament) {
        if (lawServiceXML.lawExists(law.getId())) {
            return ResponseEntity
                    .badRequest()
                    .headers(HeaderUtil.failure(
                            Constants.EntityNames.LAWS,
                            HeaderUtil.ERROR_CODE_ID_ALREADY_EXIST,
                            HeaderUtil.ERROR_MSG_ID_ALREADY_EXIST))
                    .body(null);
        }

        final Law result = lawServiceXML.create(law);

        addAgenda(law.getId(), parliament, "law");

        parliamentServiceXML.create(parliament);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> suggestAmendments(Amendments amendments, Parliament parliament) {
        if (amendmentsServiceXML.amendmentsExists(amendments.getId())) {
            return ResponseEntity
                    .badRequest()
                    .headers(HeaderUtil.failure(
                            Constants.EntityNames.AMENDMENTS,
                            HeaderUtil.ERROR_CODE_ID_ALREADY_EXIST,
                            HeaderUtil.ERROR_MSG_ID_ALREADY_EXIST))
                    .body(null);
        }

        final Amendments result = amendmentsServiceXML.create(amendments);

        Ref ref = new Ref();
        ref.setId(amendments.getId());
        addAgenda(amendments.getId(), parliament, "amendment");

        parliamentServiceXML.create(parliament);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> updateLawStatus(String id, String status, Parliament parliament) {
        if (Constants.LawsStates.WITHDRAWN.equals(status)) {
            final Law result = lawServiceXML.updateLawStatus(id, status);
            removeAgenda(id, parliament);

            parliamentServiceXML.create(parliament);

            return new ResponseEntity<>(result, HttpStatus.OK);
        } else if (Constants.LawsStates.SUGGESTED.equals(status)) {
            final Law result = lawServiceXML.updateLawStatus(id, status);
            addAgenda(id, parliament, "law");

            parliamentServiceXML.create(parliament);

            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return forbiddenActionFromState(StateConstants.ParliamentStates.CONVENED_STATE);
    }

    @Override
    public ResponseEntity<?> updateAmendmentStatus(String id, String status, Parliament parliament) {
        if (Constants.AmendmentsStates.WITHDRAWN.equals(status)) {
            final Amendments result = amendmentsServiceXML.updateAmendmentsStatus(id, status);
            removeAgenda(id, parliament);

            parliamentServiceXML.create(parliament);

            return new ResponseEntity<>(result, HttpStatus.OK);
        } else if (Constants.AmendmentsStates.SUGGESTED.equals(status)) {
            final Amendments result = amendmentsServiceXML.updateAmendmentsStatus(id, status);
            addAgenda(id, parliament, "amendment");

            parliamentServiceXML.create(parliament);

            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return forbiddenActionFromState(StateConstants.ParliamentStates.CONVENED_STATE);
    }

    @Override
    public ResponseEntity<?> updateLawVoting(String id, VotingObject voting) {
        return forbiddenActionFromState(StateConstants.ParliamentStates.CONVENED_STATE);
    }

    @Override
    public ResponseEntity<?> updateAmendmentVoting(String id, VotingObject voting) {
        return forbiddenActionFromState(StateConstants.ParliamentStates.CONVENED_STATE);
    }
}
