package eapli.ecafeteria.domain.kitchen;

import eapli.ecafeteria.application.kitchen.GenerateMealPlanController;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Embeddable;

/**
 * LastAvailableWeek - Copies the number of meals from last week
 *
 * @author Tiago João Santos Rios, 1161292@isep.ipp.pt
 * @author Gonçalo Silva (1161140)
 */
@Embeddable
public class HeuristicA implements Heuristic {

    private String name;
    private GenerateMealPlanController controller;

    public HeuristicA(String name) {
        this.name = name;
    }

    @Override
    public void doHeuristicLogic() {
        List<MealPlanItemQuantity> mealPlanItemQuantityList = new ArrayList<>();

        //Criar método para ir buscar apenas a última mealPlan / última semana
        for (MealPlan mealPlan : controller.getMealPlanHistory()) {
            mealPlanItemQuantityList = mealPlan.getItemQuantityList();
        }

        returnToController(mealPlanItemQuantityList);
    }

    public List<MealPlanItemQuantity> returnToController(List<MealPlanItemQuantity> mealPlanItemQuantityList) {
        return mealPlanItemQuantityList;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
