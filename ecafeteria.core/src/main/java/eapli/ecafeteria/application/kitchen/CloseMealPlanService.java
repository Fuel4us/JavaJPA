package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.domain.kitchen.MealPlan;
import eapli.ecafeteria.persistence.MealPlanRepository;
import eapli.ecafeteria.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Gonçalo Fonseca <1150503@isep.ipp.pt>
 */
public class CloseMealPlanService {

    private final MealPlanRepository repo = PersistenceContext.repositories().mealplans();

    public CloseMealPlanService(){
    }

    public List<MealPlan> getAllMealPlans() {
        Iterable<MealPlan> mealPlans = repo.findAll();
        List<MealPlan> list = new ArrayList<>();

        if(!mealPlans.iterator().hasNext()) {
            System.out.println("There are no meal plans available");
        } else {
            for (MealPlan meals: mealPlans) {
                list.add(meals);
            }
        }

        return list;
    }

}
