/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.kitchen.Execution;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Carlos Figueiredo (1140317)
 */
public class RegisterMealsActuallyCookedController implements Controller {

    List<Meal> mealList;
    RegisterMealsActuallyCookedService service;

    public RegisterMealsActuallyCookedController() {
        service = new RegisterMealsActuallyCookedService();
    }

    public void setMealList(List<Meal> list) {
        this.mealList = list;
    }

    public List<Meal> getAllMeals() {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);
        return service.getAllMeals();

    }

    public Execution registerMealsActuallyMade(long mealCode, int cookedMeals) throws DataConcurrencyException, DataIntegrityViolationException {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);
        return service.registerMealsActuallyMade(mealCode, cookedMeals);
    }

    public boolean checkedMeal(long mealCode) {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);
        return service.checkedMeal(mealCode);
    }

    public List<Date> displayMealsByDate() {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);
        return service.displayMealsByDate(mealList);

    }

    public List<Dish> displayMealsByDish() {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);
        return service.displayMealsByDish(mealList);
    }

    public List<Meal> displayMealsByMeal() {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);
        return service.displayMealsByMeal(mealList);
    }

    public List<MealType> displayMealsByMealType() {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);
        return service.displayMealsByMealType(mealList);
    }
}
