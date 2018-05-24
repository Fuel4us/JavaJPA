package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.domain.kitchen.HeuristicConfiguration;
import eapli.ecafeteria.domain.kitchen.MealPlan;
import eapli.ecafeteria.domain.menus.Menu;
import eapli.ecafeteria.persistence.HeuristicRepository;
import eapli.ecafeteria.persistence.MealPlanRepository;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.ecafeteria.persistence.PersistenceContext;

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

        //NullPointerException
        heuristic.getHeuristicInUse().doHeuristicLogic();

        mealPlan.setDishNumber(heuristic.getHeuristicInUse().returnMealPlanItemQuantityList());

        return mealPlan;
    }
}
