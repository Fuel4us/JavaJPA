package eapli.ecafeteria.domain.kitchen;

import eapli.ecafeteria.persistence.MealPlanRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Embeddable;

/**
 * Average - Averages the number of meals from all previous meal plans
 *
 * @author Tiago João Santos Rios, 1161292@isep.ipp.pt
 * @author Gonçalo Silva (1161140)
 */
@Embeddable
public class HeuristicB implements Heuristic {

    private String name;

    public HeuristicB(String name) {
        this.name = name;
    }

    @Override
    public void doHeuristicLogic() {
        generateNumberOfDishes();
    }

    public List<MealPlanItemQuantity> generateNumberOfDishes() {
        List<Integer> averageMealsList = new ArrayList<>();
        int totalMeals = 0, averageMeals = 0;

        MealPlanRepository repository = PersistenceContext.repositories().mealplans();

        List<MealPlanItemQuantity> mealPlanItemQuantityList = new ArrayList<>();

        for (MealPlan mealPlan : repository.findAll()) {
            for (MealPlanItemQuantity mealPlanItemQuantity : mealPlan.getItemQuantityList()) {
                int itemQuantity = mealPlanItemQuantity.getItemQuantity();

                totalMeals += itemQuantity;
            }

            if (mealPlan.getItemQuantityList().size() != 0) {
                averageMeals = totalMeals / mealPlan.getItemQuantityList().size();
            }
            averageMealsList.add(averageMeals);
        }

        for (Integer number : averageMealsList) {
            mealPlanItemQuantityList.add(new MealPlanItemQuantity(number, null));
        }

        return mealPlanItemQuantityList;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
