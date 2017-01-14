package rs.ac.uns.sw.xml.states;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import rs.ac.uns.sw.xml.domain.Parliament;
import rs.ac.uns.sw.xml.service.ParliamentServiceXML;

import static rs.ac.uns.sw.xml.util.Constants.ACTIVE_PARLIAMENT;

@Component
public class StartupStateInitialization {

    @Autowired
    ParliamentServiceXML parliamentServiceXML;

    @Autowired
    StateContext stateContext;

    @EventListener(ContextRefreshedEvent.class)
    void contextRefreshedEvent() {
        Parliament activeParliament = parliamentServiceXML.findOneById(ACTIVE_PARLIAMENT);
        if (activeParliament != null) {
            stateContext.setParliament(activeParliament);
            stateContext.setState(stateContext.parliamentStatusToState(activeParliament.getHead().getStatus()));
        }
    }
}
