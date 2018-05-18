package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.domain.kitchen.MealPlan;
import eapli.ecafeteria.domain.menus.Menu;
import eapli.ecafeteria.persistence.MealPlanRepository;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

/**
 * @author Tiago Babo 1160760
 * @author Gonçalo Silva (1161140)
 */
public class MealPlanBootstrapper implements Action {

    @Override
    public boolean execute() {
        try {
            register();
        } catch (DataIntegrityViolationException | DataConcurrencyException e) {
            return false;
        }

        return true;
    }

    public void register() throws DataIntegrityViolationException, DataConcurrencyException {
        final MenuRepository menuRepository = PersistenceContext.repositories().menus();
        final MealPlanRepository mealLotRepository = PersistenceContext.repositories().mealplans();

        for (Menu menu : menuRepository.findAll()) {
            MealPlan mealPlan = new MealPlan(menu);

            mealLotRepository.save(mealPlan);
        }
    }
}
