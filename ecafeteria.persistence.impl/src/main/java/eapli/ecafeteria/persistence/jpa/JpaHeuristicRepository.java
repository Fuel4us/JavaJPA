package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.meals.Heuristic;
import eapli.ecafeteria.domain.meals.HeuristicConfiguration;
import eapli.ecafeteria.persistence.HeuristicRepository;
import eapli.framework.persistence.repositories.impl.jpa.JpaAutoTxRepository;

/**
 *
 * @author Tiago João Santos Rios, 1161292@isep.ipp.pt
 */
public class JpaHeuristicRepository extends JpaAutoTxRepository<HeuristicConfiguration, Heuristic> implements HeuristicRepository {
    
    public JpaHeuristicRepository(String persistenceUnitName) {
        super(persistenceUnitName);
    }
    
}
