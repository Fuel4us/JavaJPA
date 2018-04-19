/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.meals;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.application.Controller;

/**
 *
 * @author Bernardo Carreira
 */
public class ListMealController implements Controller {
    
    private final ListMealService svc = new ListMealService();

    public Iterable<Meal> allMeals() {
        return this.svc.allMeals();
    }
}
