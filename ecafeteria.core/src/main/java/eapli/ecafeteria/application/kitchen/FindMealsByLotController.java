package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.domain.kitchen.Lot;
import eapli.ecafeteria.domain.kitchen.MealLot;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.LotRepository;
import eapli.ecafeteria.persistence.MealLotRepository;

/**
 * @author Gonçalo Silva (1161140)
 */
public class FindMealsByLotController {

    private final LotRepository lotRepository = PersistenceContext.repositories().lots();
    private final MealLotRepository mealLotRepository = PersistenceContext.repositories().mealLots();

    /**
     * Gets all lots from the repository
     *
     * @return a list of used lots
     */
    public Iterable<Lot> getUsedLots() {
        return lotRepository.findAll();
    }

    /**
     * Given a lot, gets all meals that were cooked with that lot
     *
     * @param lot lot to search meal for
     * @return a list of meals cooked with the selected lot
     */
    public Iterable<MealLot> getCookedMealsWithLot(Lot lot) {
        return mealLotRepository.findAllByLot(lot);
    }
}
