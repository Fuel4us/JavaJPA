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
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Date;

/**
 *
 * @author Bernardo Carreira
 */
public class RegisterMealController implements Controller {

    private final ListMealService svc = new ListMealService();

    private final MealRepository mealRepository = PersistenceContext.repositories().meals();

    private final Meal meal = new Meal();

    public Meal registerMeal(final MealType mealType, final Date mealDate, final Dish dish) throws DataIntegrityViolationException, DataConcurrencyException {

        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);

        final Meal newMeal = new Meal(mealType, mealDate, dish);

        return this.mealRepository.save(newMeal);
    }

    /**
     * @autor Pedro Alves - 1150372
     * @return
     */
    public Iterable<MealType> getMealTypes() {
        return this.svc.allMealTypes();
    }

    /**
     * @autor Pedro Alves - 1150372
     * @param mealType
     * @param mealDate
     */
    public void validateDate(MealType mealType, Date mealDate) {
        if (mealType != null && mealDate != null) {
            //completar o método
        }
    }

}
