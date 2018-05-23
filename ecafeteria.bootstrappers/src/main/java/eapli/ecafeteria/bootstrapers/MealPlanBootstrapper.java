package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.kitchen.CreateMealPlanController;
import eapli.ecafeteria.domain.kitchen.MealPlan;
import eapli.ecafeteria.domain.kitchen.MealPlanItemQuantity;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.menus.Menu;
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
        final MenuRepository menuRepository = PersistenceContext.repositories().menus();
        final CreateMealPlanController controller = new CreateMealPlanController();

        for (Menu menu : menuRepository.findAll()) {
            MealPlan mealPlan = controller.createMealPlan(menu);

            for (Meal meal : controller.getMealList(mealPlan)) {
                Random rand = new Random();

                MealPlanItemQuantity mpiq = controller.createMealPlanItemQuantity(rand.nextInt(10) + 1, meal);
                controller.getItemQuantityList(mealPlan).add(mpiq);
            }

            controller.saveMealPlan(mealPlan);
        }
    }
}
