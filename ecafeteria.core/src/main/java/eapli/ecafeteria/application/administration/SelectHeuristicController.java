package eapli.ecafeteria.application.administration;

import eapli.ecafeteria.domain.administration.Settings;
import eapli.ecafeteria.domain.kitchen.Heuristic;
import eapli.ecafeteria.domain.kitchen.HeuristicA;
import eapli.ecafeteria.domain.kitchen.HeuristicB;
import eapli.ecafeteria.domain.kitchen.HeuristicConfiguration;
import eapli.ecafeteria.persistence.HeuristicRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joaotiagow
 */
public class SelectHeuristicController implements Controller {
    
    private final HeuristicRepository heuristicRepository = PersistenceContext.repositories().heuristics();
    
    public List<Heuristic> listHeuristics(){
        return new LinkedList<Heuristic>() {{add(new HeuristicA("Heuristic A"));
                                             add(new HeuristicB("Heuristic B"));
                                            }};
    }
    
    public boolean changeHeuristicInUse(Heuristic newHeuristic){
        try {
            final HeuristicConfiguration heuristicToUse = new HeuristicConfiguration(newHeuristic);
            
            if(Settings.getInstance().defineNewHeuristic(heuristicToUse)){
                heuristicRepository.save(heuristicToUse);
                return true;
            } else
                return false;
        } catch (DataConcurrencyException | DataIntegrityViolationException e) {
            Logger.getLogger(SelectHeuristicController.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }
}
