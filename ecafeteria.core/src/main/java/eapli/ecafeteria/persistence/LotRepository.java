package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.kitchen.Lot;
import eapli.framework.persistence.repositories.DataRepository;
import java.util.Optional;

public interface LotRepository extends DataRepository<Lot, Long> {
    
    Optional<Lot> findByLotCode(int lotCode);
}
