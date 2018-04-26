package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.kitchen.Lot;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepositoryWithLongPK;
import eapli.ecafeteria.persistence.LotRepository;

public class InMemoryLotRepository extends InMemoryRepositoryWithLongPK<Lot> implements LotRepository {

}
