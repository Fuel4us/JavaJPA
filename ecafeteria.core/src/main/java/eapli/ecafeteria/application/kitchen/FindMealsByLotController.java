package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.kitchen.Lot;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.LotRepository;

/**
 * @author Gonçalo Silva (1161140)
 */
public class FindMealsByLotController {
    
    private final LotRepository lotRepository = PersistenceContext.repositories().lots();
    private final MealRepository mealRepository = PersistenceContext.repositories().meals();
    
    public Iterable<Lot> getUsedLots() {
        return lotRepository.findAll();
    }
    
    public Iterable<Meal> getCookedMealsWithLot(Long lotId) {
        return mealRepository.findAllByLot(lotId);
    }
}
