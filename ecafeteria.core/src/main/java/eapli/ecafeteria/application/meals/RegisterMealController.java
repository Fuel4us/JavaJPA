/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.meals;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.application.dishes.ListDishService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.domain.menus.Menu;
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

    private final ListDishService theDishes = new ListDishService();

    private final MealRepository mealRepository = PersistenceContext.repositories().meals();

    private Meal meal = null;

    /**
     * Método que regista a Nova Meal.
     *
     * @param mealType
     * @param mealDate
     * @param dish
     * @return
     * @throws DataIntegrityViolationException
     * @throws DataConcurrencyException
     */
    public Meal registerMeal(final MealType mealType, final Date mealDate, final Dish dish) throws DataIntegrityViolationException, DataConcurrencyException {
        final Meal newMeal = new Meal(mealType, mealDate, dish);
        meal = newMeal;
        return this.mealRepository.save(newMeal);
    }

    /**
     * Método por desenvolver que atualizará a Meal, adicionando o menu.
     *
     * @param meal
     * @return
     * @throws DataConcurrencyException
     * @throws DataIntegrityViolationException
     */
    public Meal updateMeal(Meal meal, Menu menu) throws DataConcurrencyException, DataIntegrityViolationException { //alterações 
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);
        meal.insertMenu(menu);
        return this.mealRepository.save(meal);
    }

    /**
     * @autor Pedro Alves - 1150372
     * @return
     */
    public Iterable<MealType> getMealTypes() {
        return this.svc.allMealTypes();
    }

    public Iterable<Dish> getAllDishesActives() {
        return this.theDishes.allDishesActives();
    }

    @Override
    public String toString() {
        return meal.toString();
    }

}
