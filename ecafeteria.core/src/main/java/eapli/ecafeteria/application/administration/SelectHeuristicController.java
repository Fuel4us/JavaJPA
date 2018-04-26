package eapli.ecafeteria.application.administration;

import eapli.ecafeteria.domain.kitchen.HeuristicConfiguration;
import eapli.ecafeteria.persistence.HeuristicRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;

/**
 *
 * @author joaotiagow
 */
public class SelectHeuristicController implements Controller {
    
    private final HeuristicRepository heuristicRepository = PersistenceContext.repositories().heuristics();
    
    public Iterable<HeuristicConfiguration> listHeuristics(){
        return heuristicRepository.findAll();
    }

}
