/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.kitchen.Lot;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.application.Controller;
import java.util.List;

/**
 *
 * @author Carlos Figueiredo (1140317)
 */
public class ListLotsUsedInMealController implements Controller {

    private final ListLotsUsedInMealServices service = new ListLotsUsedInMealServices();

    /**
     * controller for getAllMeals method
     * @return 
     */
    public List<Meal> getAllMeals() {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);
        return service.getAllMeals();
    }

    /**
     * controller for getLotsFromMeal method
     * @param meal
     * @return 
     */
    public List<Lot> getLotsByMeal(Meal meal) {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);
        return service.getLotsFromMeal(meal);
    }

}
