package eapli.ecafeteria.domain.kitchen;

import eapli.ecafeteria.persistence.MealPlanRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import java.util.List;
import javax.persistence.Embeddable;

/**
 * Most recent - Copies the number of meals from most recent meal plan
 *
 * @author Tiago João Santos Rios, 1161292@isep.ipp.pt
 * @author Gonçalo Silva (1161140)
 */
@Embeddable
public class HeuristicA implements Heuristic {

    private String name;

    public HeuristicA(String name) {
        this.name = name;
    }

    @Override
    public void doHeuristicLogic() {
        generateNumberOfDishes();
    }

    @Override
    public List<MealPlanItemQuantity> generateNumberOfDishes() {
        MealPlanRepository repository = PersistenceContext.repositories().mealplans();

        return repository.getLastMealPlan().getItemQuantityList();
    }

    @Override
    public String toString() {
        return this.name;
    }
}
