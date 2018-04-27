package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.kitchen.Lot;
import eapli.ecafeteria.persistence.LotRepository;
import java.util.Optional;

public class JpaLotRepository extends CafeteriaJpaRepositoryBase<Lot, Long> implements LotRepository {

    @Override
    public Optional<Lot> findByLotCode(int lotCode) {
        return matchOne("e.lotCode=:lotCode", "lotCode", lotCode);
    }

}
