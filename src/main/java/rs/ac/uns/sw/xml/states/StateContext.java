package rs.ac.uns.sw.xml.states;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rs.ac.uns.sw.xml.domain.Parliament;
import rs.ac.uns.sw.xml.service.AmendmentsServiceXML;
import rs.ac.uns.sw.xml.service.LawServiceXML;
import rs.ac.uns.sw.xml.service.ParliamentServiceXML;

import static rs.ac.uns.sw.xml.util.StateConstants.ParliamentStates.*;

@Component
public class StateContext {

    @Autowired
    private LawServiceXML lawServiceXML;

    @Autowired
    private AmendmentsServiceXML amendmentsServiceXML;

    @Autowired
    private ParliamentServiceXML parliamentServiceXML;

    private State state;
    private Parliament parliament;

    public State getState() {
        return state;
    }

    public State setState(State state) {
        if (this.state instanceof ConvenedState) {
            if (state instanceof InProgressState || state instanceof CanceledState) {
                this.state = state;
                return this.state;
            }
            return null;
        } else if (this.state instanceof InProgressState) {
            if (state instanceof FinishedState) {
                this.state = state;
                return this.state;
            }
            return null;
        } else if (this.state instanceof CanceledState || this.state instanceof FinishedState) {
            if (state instanceof ConvenedState) {
                this.state = state;
                return this.state;
            }
            return null;
        } else if (this.state == null) {
            this.state = state;
            return this.state;
        } else {
            return null;
        }
    }

    public State parliamentStatusToState(String status) {
        switch (status) {
            case CONVENED_STATE:
                return new ConvenedState(lawServiceXML, amendmentsServiceXML, parliamentServiceXML, parliament);
            case IN_PROGRESS_STATE:
                return new InProgressState(lawServiceXML, amendmentsServiceXML);
            case FINISHED_STATE:
                return new FinishedState();
            case CANCELED_STATE:
                return new CanceledState();
            default:
                return null;
        }
    }

    public Parliament getParliament() {
        return parliament;
    }

    public void setParliament(Parliament parliament) {
        this.parliament = parliament;
    }
}
