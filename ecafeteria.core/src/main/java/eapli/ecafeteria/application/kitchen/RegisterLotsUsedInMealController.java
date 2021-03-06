/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.kitchen.Lot;
import eapli.ecafeteria.domain.kitchen.MealLot;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.MealLotRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.List;

/**
 *
 * @author Pedro Rodrigues (1140572)
 */
public class RegisterLotsUsedInMealController implements Controller {

    private final RegisterLotsUsedInMealServices service = new RegisterLotsUsedInMealServices();
    private final MealLotRepository mlRepository = PersistenceContext.repositories().mealLots();

    public List<Meal> getAllMeals() {
        return service.getAllMeals();
    }
    
    public List<Lot> getLotsByMeal(Meal meal) {
        return service.getLotsFromMeal(meal);
    }
     
    public MealLot registerMealLot(Meal meal, Lot lot, int quantityUsed)
            throws DataIntegrityViolationException, DataConcurrencyException {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);
        MealLot mealLot = new MealLot(meal, lot, quantityUsed);
        return this.mlRepository.save(mealLot);
    }
}
