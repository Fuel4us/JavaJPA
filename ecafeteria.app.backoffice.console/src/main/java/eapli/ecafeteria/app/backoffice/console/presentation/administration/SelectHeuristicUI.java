package eapli.ecafeteria.app.backoffice.console.presentation.administration;

import eapli.ecafeteria.application.administration.SelectHeuristicController;
import eapli.ecafeteria.domain.meals.Heuristic;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;
import java.util.Iterator;

/**
 *
 * @author Tiago João Santos Rios, 1161292@isep.ipp.pt
 */
public class SelectHeuristicUI extends AbstractUI {
    
    private final SelectHeuristicController theController = new SelectHeuristicController();
    private Integer heuristicsTypesSize = 0;
    
    @Override
    protected boolean doShow() {
        final Iterable<Heuristic> heuristicsTypes = theController.listHeuristics();
        
        showHeuristics(heuristicsTypes);
        Heuristic newHeuristic = querySelection(heuristicsTypes);
        
        return true;
    }
    
    @Override
    public String headline() {
        return "Select Heuristic";
    }
    
    private void showHeuristics(Iterable<Heuristic> heuristicsTypes) {
        int i = 0;
        
        for(Heuristic h : heuristicsTypes){
            System.out.printf("%d. %s\n", i, h.toString());
            i++;
        }
        
        heuristicsTypesSize = i;
        System.out.printf("\n");
    }
    
    private Heuristic querySelection(Iterable<Heuristic> heuristicsTypes){
        boolean valid = false;
        Integer option;
        
        do {
            option = Console.readInteger("Please select heuristic number");
            
            if (!(option > 0 && option < heuristicsTypesSize))
                option = Console.readInteger("Please insert a valid number.");
            else 
                valid = true;
            
        } while (valid);
        
        return findHeuristic(option, heuristicsTypes);
    }
    
    private Heuristic findHeuristic(Integer option, Iterable<Heuristic> heuristicsTypes){
        int i = 0;
        Iterator<Heuristic> heuristicIterator = heuristicsTypes.iterator();
        
        while(i != option){
            heuristicIterator.next();
            i++;
        }
        
        return heuristicIterator.next();
    }
}

