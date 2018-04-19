package eapli.ecafeteria.domain.meals;

/**
 *
 * @author Tiago João Santos Rios, 1161292@isep.ipp.pt
 */
public class HeuristicConfiguration {
    
    private Heuristic selectedHeuristic;
    
    public HeuristicConfiguration(Heuristic selectedHeuristic) {
        this.selectedHeuristic = selectedHeuristic;
    }
    
    public boolean selectNewHeuristic(Heuristic newHeuristic) {
        if (newHeuristic != null){
            this.selectedHeuristic = newHeuristic;
            return true;
        } else
            return false;
    }
}
