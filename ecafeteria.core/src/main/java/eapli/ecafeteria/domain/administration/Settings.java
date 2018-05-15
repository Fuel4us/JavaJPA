package eapli.ecafeteria.domain.administration;

import eapli.ecafeteria.domain.kitchen.HeuristicConfiguration;
import eapli.ecafeteria.domain.kitchen.LimitConfiguration;
import eapli.ecafeteria.persistence.PersistenceContext;

/**
 *
 * @author Tiago João Santos Rios, 1161292@isep.ipp.pt
 */
public class Settings {
    
    private static Settings instance = null;
    
    protected Settings(){
        heuristic = PersistenceContext.repositories().heuristics().last();
        limits = PersistenceContext.repositories().kitchenLimit().first();
    }
    
    public static Settings getInstance(){
        if(instance == null){
            instance = new Settings();
        }
        
        return instance;
    }
    
    private HeuristicConfiguration heuristic;
    private LimitConfiguration limits;
    
    public boolean defineNewHeuristic(HeuristicConfiguration newHeuristic){
        if(newHeuristic == null)
            return false;
        
        this.heuristic = newHeuristic;
        return true;
    }
}
