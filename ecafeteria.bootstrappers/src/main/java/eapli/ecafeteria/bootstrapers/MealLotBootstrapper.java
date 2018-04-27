package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.domain.kitchen.Lot;
import eapli.ecafeteria.domain.kitchen.MealLot;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.LotRepository;
import eapli.ecafeteria.persistence.MealLotRepository;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

/**
 * @author Gonçalo Silva (1161140)
 */
public class MealLotBootstrapper implements Action {

    @Override
    public boolean execute() {
        try {
            register();
        } catch (DataIntegrityViolationException | DataConcurrencyException ex) {
            return false;
        }

        return true;
    }

    public void register() throws DataIntegrityViolationException, DataConcurrencyException {
        final MealRepository mealRepository = PersistenceContext.repositories().meals();
        final LotRepository lotRepository = PersistenceContext.repositories().lots();
        final MealLotRepository mealLotRepository = PersistenceContext.repositories().mealLots();

        for (Meal meal : mealRepository.findAll()) {
            for (Lot lot : lotRepository.findAll()) {
                MealLot mealLot = new MealLot(meal, lot);
                mealLotRepository.save(mealLot);
            }
        }
    }
}
