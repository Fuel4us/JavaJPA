/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.meals;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

/**
 *
 * @author Bernardo Carreira
 */
public class ChangeMealController implements Controller{
    
    private final ListMealService svc = new ListMealService();
    private final MealRepository mealRepository = PersistenceContext.repositories().meals();

    public Iterable<Meal> allMeals() {
        return this.svc.allMeals();
    }

    public Meal changeMealDish(Meal meal, Dish newDish)
            throws DataConcurrencyException, DataIntegrityViolationException {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);
        if (meal == null) {
            throw new IllegalArgumentException();
        }
        meal.changeDishTo(newDish);

        return this.mealRepository.save(meal);
    }
}
