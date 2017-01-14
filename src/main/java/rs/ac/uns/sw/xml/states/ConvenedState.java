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

        Ref ref = new Ref();
        ref.setId(law.getId());
        addAgenda(ref, parliament);

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
        addAgenda(ref, parliament);

        parliamentServiceXML.create(parliament);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> withdrawalLaw(Law law) {
        return null;
    }

    @Override
    public ResponseEntity<?> withdrawalAmendments(Amendments amendments) {
        return null;
    }

    @Override
    public ResponseEntity<?> voting(int votesFor, int votesAgainst, int votesNeutral) {
        return null;
    }

    private boolean addAgenda(Ref ref, Parliament parliament) {
        for (Ref r : parliament.getBody().getAkti().getRef()) {
            if (ref.getId().equals(r.getId())) {
                return false;
            }
        }

        parliament.getBody().getAkti().getRef().add(ref);
        return true;
    }
}
