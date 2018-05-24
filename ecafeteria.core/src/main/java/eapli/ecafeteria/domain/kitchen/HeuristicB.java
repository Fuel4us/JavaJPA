package eapli.ecafeteria.domain.kitchen;

import eapli.ecafeteria.persistence.MealPlanRepository;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Embeddable;

/**
 * Average - Averages the number of all previous meal plans
 *
 * @author Tiago João Santos Rios, 1161292@isep.ipp.pt
 * @author Gonçalo Silva (1161140)
 */
@Embeddable
public class HeuristicB implements Heuristic {

    private String name;
    private MealPlanRepository repository;
    private List<MealPlanItemQuantity> mealPlanItemQuantityList;

    public HeuristicB(String name) {
        this.name = name;
    }

    @Override
    public void doHeuristicLogic() {
        List<Integer> averageMealsList = new ArrayList<>();
        int totalMeals = 0, averageMeals = 0;

        for (MealPlan mealPlan : repository.findAll()) {
            mealPlanItemQuantityList = mealPlan.getItemQuantityList();

            for (MealPlanItemQuantity mealPlanItemQuantity : mealPlanItemQuantityList) {
                int itemQuantity = mealPlanItemQuantity.getItemQuantity();

                totalMeals += itemQuantity;
            }
        }

        averageMeals = totalMeals / mealPlanItemQuantityList.size();

        averageMealsList.add(averageMeals);

        //Passar isto corretamente para um array de MealPlanItemQuantity
        returnMealPlanItemQuantityList();
    }

    public List<MealPlanItemQuantity> returnMealPlanItemQuantityList() {
        return mealPlanItemQuantityList;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
