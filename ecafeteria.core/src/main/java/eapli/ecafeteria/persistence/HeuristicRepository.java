package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.meals.Heuristic;
import eapli.ecafeteria.domain.meals.HeuristicConfiguration;
import eapli.framework.persistence.repositories.DataRepository;

/**
 *
 * @author Tiago João Santos Rios, 1161292@isep.ipp.pt
 */
public interface HeuristicRepository extends DataRepository<HeuristicConfiguration, Heuristic> {
    
}
