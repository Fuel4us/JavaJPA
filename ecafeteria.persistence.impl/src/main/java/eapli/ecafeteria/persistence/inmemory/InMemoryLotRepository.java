package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.kitchen.Lot;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepositoryWithLongPK;
import eapli.ecafeteria.persistence.LotRepository;
import java.util.Optional;

public class InMemoryLotRepository extends InMemoryRepositoryWithLongPK<Lot> implements LotRepository {

    @Override
    public Optional<Lot> findByLotCode(int lotCode) {
        return matchOne(e -> e.id().equals(lotCode));
    }

    @Override
    public Long findPkWithCode(int lotCode) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
