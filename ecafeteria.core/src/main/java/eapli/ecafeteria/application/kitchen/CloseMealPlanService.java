package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.domain.kitchen.MealPlan;
import eapli.ecafeteria.persistence.MealPlanRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.util.Console;

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

    public MealPlan choseMealPlanToClose() {
        List<MealPlan> list = getAllMealPlans();
        MealPlan mealToClose = new MealPlan();

        if(list.size() > 0) {
            for (int i= 0; i < list.size(); i++) {
                System.out.println((i+1) + " - " + list.get(i) + "\n");
            }
            int choice = Console.readInteger("Which meal plan do you wish to close ? ");
            if (choice > 0 && choice < list.size() + 1) {
                mealToClose = list.get(choice-1);
            } else {
                System.out.println("This option does not exist");
                return null;
            }
        }

        return mealToClose;
    }

    public void changeStateDB(MealPlan mealPlan) {
    }

}
