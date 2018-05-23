package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.domain.kitchen.MealPlan;

/**
 * @author Gonçalo Fonseca - 1150503@isep.ipp.pt
 */
public class CloseMealPlanController {

    CloseMealPlanService service;
    MealPlan meal;

    /**
     * calls CloseMealPlanService so I can use it
     */
    public CloseMealPlanController() {
        service = new CloseMealPlanService();
    }

    /**
     * this method gives meal a meal and closes it
     */
    public void choseMealPlanToClose() {
        meal = service.choseMealPlanToClose();
        //service.changeStateDB(meal);
        meal.changeState(meal);
    }

}
