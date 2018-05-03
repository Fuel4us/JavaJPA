package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.kitchen.HeuristicConfiguration;
import eapli.ecafeteria.persistence.HeuristicRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;

/**
 *
 * @author Tiago João Santos Rios, 1161292@isep.ipp.pt
 */
public class InMemoryHeuristicRepository extends InMemoryRepository<HeuristicConfiguration, Long> implements HeuristicRepository {

    @Override
    protected Long newKeyFor(HeuristicConfiguration entity) {
        return entity.id();
    }

}
