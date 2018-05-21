package eapli.ecafeteria.domain.kitchen;

import eapli.ecafeteria.application.kitchen.GenerateMealPlanController;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Embeddable;

/**
 * Average - Averages the number of meals to cook
 *
 * @author Tiago João Santos Rios, 1161292@isep.ipp.pt
 * @author Gonçalo Silva (1161140)
 */
@Embeddable
public class HeuristicB implements Heuristic {

    private String name;
    private GenerateMealPlanController controller;

    public HeuristicB(String name) {
        this.name = name;
    }

    @Override
    public void doHeuristicLogic() {
        List<MealPlanItemQuantity> mealPlanItemQuantityList = new ArrayList<>();
        List<Integer> averageMealsList = new ArrayList<>();
        int totalMeals = 0, averageMeals = 0;

        for (MealPlan mealPlan : controller.getMealPlanHistory()) {
            mealPlanItemQuantityList = mealPlan.getItemQuantityList();

            for (MealPlanItemQuantity mealPlanItemQuantity : mealPlanItemQuantityList) {
                int itemQuantity = mealPlanItemQuantity.getItemQuantity();

                totalMeals += itemQuantity;
            }
        }

        averageMeals = totalMeals / mealPlanItemQuantityList.size();

        //Passar isto corretamente para um array de int
        averageMealsList.add(averageMeals);

        returnToController(averageMealsList);
    }

    public List<Integer> returnToController(List<Integer> averageMealsList) {
        return averageMealsList;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
