package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.domain.kitchen.HeuristicConfiguration;
import eapli.ecafeteria.domain.kitchen.MealPlan;
import eapli.ecafeteria.domain.kitchen.MealPlanItemQuantity;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.menus.Menu;
import eapli.ecafeteria.persistence.HeuristicRepository;
import eapli.ecafeteria.persistence.MealPlanRepository;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Gonçalo Silva (1161140)
 */
public class GenerateMealPlanController {

    private final MealPlanRepository mealPlanRepository = PersistenceContext.repositories().mealplans();
    private final MenuRepository menuRepository = PersistenceContext.repositories().menus();
    private final HeuristicRepository heuristicRepository = PersistenceContext.repositories().heuristics();

    /**
     * Gets all heuristics available from the repository
     *
     * @return a list of available heuristics
     */
    public Iterable<HeuristicConfiguration> getAvailableHeuristics() {
        return heuristicRepository.findAll();
    }

    /**
     * Checks if there are past meal plans to use with heuristics
     *
     * @return a list of past meal plans
     */
    public Iterable<MealPlan> getMealPlanHistory() {
        return mealPlanRepository.findAll();
    }

    /**
     * Gets all existing menus from the repository
     *
     * @return a list of existing menus
     */
    public Iterable<Menu> getExistingMenus() {
        return menuRepository.findAll();
    }

    /**
     * Given an heuristic, generates a meal plan
     *
     * @param heuristic selected heuristic
     * @param menu meal plan's menu
     */
    public MealPlan generateMealPlan(Menu menu, HeuristicConfiguration heuristic) {
        MealPlan mealPlan = new MealPlan(menu);

        Iterator<MealPlanItemQuantity> mealPlanItemQuantityList = heuristic.getHeuristicInUse().generateNumberOfDishes().iterator();
        List<MealPlanItemQuantity> newMealPlanItemQuantityList = new ArrayList<>();

        for (Meal meal : mealPlan.getMenu().getMealList()) {
            if (mealPlanItemQuantityList.hasNext()) {
                MealPlanItemQuantity newMealPlanItemQuantity = new MealPlanItemQuantity(mealPlanItemQuantityList.next().getItemQuantity(), meal);
                newMealPlanItemQuantityList.add(newMealPlanItemQuantity);
            }
        }

        mealPlan.setDishNumber(newMealPlanItemQuantityList);

        return mealPlan;
    }

    /**
     * Saves the meal plan to the repository
     *
     * @param mealPlan meal plan to save
     */
    public void saveMealPlan(MealPlan mealPlan) {
        try {
            mealPlanRepository.save(mealPlan);
        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            Logger.getLogger(GenerateMealPlanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
