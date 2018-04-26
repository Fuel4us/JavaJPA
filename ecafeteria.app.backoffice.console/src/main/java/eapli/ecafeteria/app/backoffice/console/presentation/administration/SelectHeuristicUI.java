package eapli.ecafeteria.app.backoffice.console.presentation.administration;

import eapli.ecafeteria.application.administration.SelectHeuristicController;
import eapli.ecafeteria.domain.kitchen.MealPlan;
import eapli.ecafeteria.domain.kitchen.Heuristic;
import eapli.ecafeteria.domain.kitchen.HeuristicConfiguration;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.util.Console;
import java.util.Iterator;

/**
 *
 * @author Tiago João Santos Rios, 1161292@isep.ipp.pt
 */
public class SelectHeuristicUI extends AbstractUI {
    
    private final SelectHeuristicController theController = new SelectHeuristicController();
    
    @Override
    protected boolean doShow() {
        final Iterable<HeuristicConfiguration> heuristicsTypes = theController.listHeuristics();
        
        SelectWidget option = new SelectWidget("Please select heuristic number", heuristicsTypes);
        option.show();
        
        HeuristicConfiguration newHeuristic = (HeuristicConfiguration) option.selectedElement();
        
        return MealPlan.changeHeuristicInUse(newHeuristic);
    }
    
    @Override
    public String headline() {
        return "Select Heuristic";
    }
}