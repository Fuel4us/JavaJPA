package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.kitchen.Lot;
import eapli.framework.persistence.repositories.DataRepository;

/**
 * @author Gonçalo Silva (1161140)
 */
public interface LotRepository extends DataRepository<Lot, Long> {
    
    Long findPkWithCode(int lotCode);
}
