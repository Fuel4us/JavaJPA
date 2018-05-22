/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.kitchen;

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
    
    public List<Meal> getAllMeals() {
        return service.getAllMeals();
    }
    
        public List<Lot> getLotsByMeal(Meal meal) {
        return service.getLotsFromMeal(meal);
    }
     

}
