/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.kitchen.Execution;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.ExecutionRepository;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

/**
 *
 * @author Carlos Figueiredo (1140317)
 */
public class RegisterMealsActuallyCookedController implements Controller {
    
    private final ExecutionRepository repository = PersistenceContext.repositories().execution();
    private final MealRepository mRepository = PersistenceContext.repositories().meals();
    
    public Execution registerMealsActuallyMade(long mealCode, int cookedMeals) throws DataIntegrityViolationException, DataConcurrencyException {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);
        
        Meal meal = mRepository.findById(mealCode).get();
        final Execution mealsActuallyMade = new Execution(meal,cookedMeals);
         return this.repository.save(mealsActuallyMade);
    }
    
    public void listMeals(){
        
        for (Meal meal : mRepository.findAll()) {
            System.out.println(meal.getId() + " - " + meal.toString());
        }
    }
    
    public boolean checkedMeal(long mealCode){
        return mRepository.findById(mealCode).isPresent();
    }
}
     
