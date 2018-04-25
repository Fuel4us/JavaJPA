package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.meals.Heuristic;
import eapli.ecafeteria.domain.meals.HeuristicConfiguration;
import eapli.ecafeteria.persistence.HeuristicRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;

/**
 *
 * @author Tiago João Santos Rios, 1161292@isep.ipp.pt
 */
public class InMemoryHeuristicRepository extends InMemoryRepository<HeuristicConfiguration, Heuristic> implements HeuristicRepository {

    @Override
    protected Heuristic newKeyFor(HeuristicConfiguration entity) {
        return entity.heuristics().get(0);
    }

}
