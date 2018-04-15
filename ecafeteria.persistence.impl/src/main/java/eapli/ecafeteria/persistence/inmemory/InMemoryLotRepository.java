package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.kitchen.MealLot;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepositoryWithLongPK;
import eapli.ecafeteria.persistence.LotRepository;

public class InMemoryLotRepository extends InMemoryRepositoryWithLongPK<MealLot> implements LotRepository {

}
