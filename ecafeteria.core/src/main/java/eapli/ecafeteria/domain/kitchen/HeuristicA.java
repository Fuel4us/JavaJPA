package eapli.ecafeteria.domain.kitchen;

import eapli.ecafeteria.persistence.MealPlanRepository;
import java.util.List;
import javax.persistence.Embeddable;

/**
 * Last MealPlan - Copies the number of meals from last week
 *
 * @author Tiago João Santos Rios, 1161292@isep.ipp.pt
 * @author Gonçalo Silva (1161140)
 */
@Embeddable
public class HeuristicA implements Heuristic {

    private String name;
    private MealPlanRepository repository;
    List<MealPlanItemQuantity> mealPlanItemQuantityList;

    public HeuristicA(String name) {
        this.name = name;
    }

    @Override
    public void doHeuristicLogic() {
        repository.getLastMealPlan().getItemQuantityList();
    }

    @Override
    public List<MealPlanItemQuantity> returnMealPlanItemQuantityList() {
        return mealPlanItemQuantityList;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
