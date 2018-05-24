package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.domain.kitchen.MealPlan;
import eapli.ecafeteria.domain.kitchen.MealPlanItemQuantity;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.menus.Menu;
import eapli.ecafeteria.persistence.MealPlanRepository;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Random;

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
        MenuRepository menuRepository = PersistenceContext.repositories().menus();
        MealPlanRepository mealPlanRepository = PersistenceContext.repositories().mealplans();
        Random rand = new Random();

        for (Menu menu : menuRepository.findAll()) {
            MealPlan mealPlan = new MealPlan(menu);

            for (Meal meal : menu.getMealList()) {
                mealPlan.getItemQuantityList().add(new MealPlanItemQuantity(rand.nextInt(10) + 1, meal));
            }

            mealPlanRepository.save(mealPlan);
        }
    }
}
