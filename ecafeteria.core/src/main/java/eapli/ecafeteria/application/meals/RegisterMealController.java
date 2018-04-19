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
import eapli.framework.domain.Designation;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Date;

/**
 *
 * @author Bernardo Carreira
 */
public class RegisterMealController implements Controller {

    private final ListMealTypeService svc = new ListMealTypeService();

    private final MealRepository mealRepository = PersistenceContext.repositories().meals();

    public Meal registerMeal(final MealType mealType, final Date mealDate, final Dish dish,
            final String name) throws DataIntegrityViolationException, DataConcurrencyException {

        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);

        final Meal newMeal = new Meal(mealType, mealDate, dish, Designation.valueOf(name));

        return this.mealRepository.save(newMeal);
    }

    public Iterable<MealType> mealTypes() {
        return this.svc.activeMealTypes();
    }
}
