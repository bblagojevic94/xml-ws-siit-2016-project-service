package rs.ac.uns.sw.xml.util;


import rs.ac.uns.sw.xml.states.CanceledState;
import rs.ac.uns.sw.xml.states.ConvenedState;
import rs.ac.uns.sw.xml.states.FinishedState;
import rs.ac.uns.sw.xml.states.State;

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
}
