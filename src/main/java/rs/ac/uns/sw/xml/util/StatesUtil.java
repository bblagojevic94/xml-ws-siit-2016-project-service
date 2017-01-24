package rs.ac.uns.sw.xml.util;


import rs.ac.uns.sw.xml.domain.Parliament;
import rs.ac.uns.sw.xml.domain.Ref;
import rs.ac.uns.sw.xml.states.CanceledState;
import rs.ac.uns.sw.xml.states.ConvenedState;
import rs.ac.uns.sw.xml.states.FinishedState;
import rs.ac.uns.sw.xml.states.State;

import java.util.Iterator;
import java.util.List;

public final class StatesUtil {

    public static String stateToString(State state){
        if(state instanceof ConvenedState)
            return StateConstants.ParliamentStates.CONVENED_STATE;
        else if(state instanceof CanceledState)
            return StateConstants.ParliamentStates.CANCELED_STATE;
        else if(state instanceof FinishedState)
            return StateConstants.ParliamentStates.FINISHED_STATE;
        else
            return StateConstants.ParliamentStates.IN_PROGRESS_STATE;
    }

    public static boolean addAgenda(String id, Parliament parliament, String type) {
        Ref ref = new Ref();
        ref.setId(id);
        ref.setType(type);

        for (Ref r : parliament.getBody().getAkti().getRef()) {
            if (ref.getId().equals(r.getId())) {
                return false;
            }
        }

        parliament.getBody().getAkti().getRef().add(ref);
        return true;
    }

    public static boolean removeAgenda(String id, Parliament parliament) {
        Ref ref = new Ref();
        ref.setId(id);

        List<Ref> list = parliament.getBody().getAkti().getRef();

        for (Iterator<Ref> iterator = list.listIterator(); iterator.hasNext(); ) {
            Ref r = iterator.next();
            if (r.getId().equals(id)) {
                iterator.remove();
                return true;
            }
        }

        return false;
    }
}
