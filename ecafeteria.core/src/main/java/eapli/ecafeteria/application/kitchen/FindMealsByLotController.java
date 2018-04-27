package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.domain.kitchen.Lot;
import eapli.ecafeteria.domain.kitchen.MealLot;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.LotRepository;
import eapli.ecafeteria.persistence.MealLotRepository;
import java.util.Optional;

/**
 * @author Gonçalo Silva (1161140)
 */
public class FindMealsByLotController {

    private final LotRepository lotRepository = PersistenceContext.repositories().lots();
    private final MealLotRepository mealLotRepository = PersistenceContext.repositories().mealLots();

    public Iterable<Lot> getUsedLots() {
        return lotRepository.findAll();
    }

    public Long getLotPkWithCode(int lotCode) {
        return lotRepository.findPkWithCode(lotCode);
    }

    public Optional<Lot> getSelectedLot(Long lotId) {
        return lotRepository.findOne(lotId);
    }

    public Iterable<MealLot> getMealLotWithLot(Lot lot) {
        return mealLotRepository.findAllByLot(lot);
    }
}
