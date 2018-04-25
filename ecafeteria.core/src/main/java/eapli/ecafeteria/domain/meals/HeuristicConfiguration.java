package eapli.ecafeteria.domain.meals;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Tiago João Santos Rios, 1161292@isep.ipp.pt
 */
public class HeuristicConfiguration {
    
    private List<Heuristic> heuristicsAvailable;
    
    public HeuristicConfiguration() {
        heuristicsAvailable = new LinkedList<>();
    }

    public List<Heuristic> heuristics() {
        return heuristicsAvailable;
    }
}
