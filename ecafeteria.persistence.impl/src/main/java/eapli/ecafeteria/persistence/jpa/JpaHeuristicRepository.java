package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.kitchen.HeuristicConfiguration;
import eapli.ecafeteria.persistence.HeuristicRepository;
import eapli.framework.persistence.repositories.impl.jpa.JpaAutoTxRepository;
import java.util.Iterator;

/**
 *
 * @author Tiago João Santos Rios, 1161292@isep.ipp.pt
 */
public class JpaHeuristicRepository extends JpaAutoTxRepository<HeuristicConfiguration, Long> implements HeuristicRepository {
    
    public JpaHeuristicRepository(String persistenceUnitName) {
        super(persistenceUnitName);
    }
    
    public HeuristicConfiguration last(){
        final Iterator<HeuristicConfiguration> it = findAll().iterator();
        HeuristicConfiguration value = null;
        
        while(it.hasNext()){
            value = it.next();
        }
        
        return value;
    }
}
