package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.domain.kitchen.MealPlan;

/**
 * @author Gonçalo Fonseca <1150503@isep.ipp.pt>
 */
public class CloseMealPlanController {

    CloseMealPlanService service;
    MealPlan meal;

    public CloseMealPlanController() {
        service = new CloseMealPlanService();
    }

    public void choseMealPlanToClose() {
        meal = service.choseMealPlanToClose();
        meal.changeState(meal);
    }

}
