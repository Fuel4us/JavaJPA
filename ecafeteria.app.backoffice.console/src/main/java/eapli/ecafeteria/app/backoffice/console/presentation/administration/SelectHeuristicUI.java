package eapli.ecafeteria.app.backoffice.console.presentation.administration;

import eapli.ecafeteria.application.administration.SelectHeuristicController;
import eapli.ecafeteria.domain.kitchen.Heuristic;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.presentation.console.ShowMessageAction;
import java.util.List;

/**
 *
 * @author Tiago João Santos Rios, 1161292@isep.ipp.pt
 */
public class SelectHeuristicUI extends AbstractUI {
    
    private final SelectHeuristicController theController = new SelectHeuristicController();
    
    @Override
    protected boolean doShow() {
        final List<Heuristic> heuristicsTypes = theController.listHeuristics();
        
        SelectWidget option = new SelectWidget("Please select heuristic number", heuristicsTypes);
        option.show();
        
        Heuristic newHeuristic = (Heuristic) option.selectedElement();
        
        boolean success = theController.changeHeuristicInUse(newHeuristic);
        
        if(success)
            new ShowMessageAction(String.format("Operation was made with success! Now you're using %s", newHeuristic)).execute();
        else
            new ShowMessageAction(String.format("An error occured while executing the operation. Please try again!")).execute();
        
        return success;
    }
    
    @Override
    public String headline() {
        return "Select Heuristic";
    }
}