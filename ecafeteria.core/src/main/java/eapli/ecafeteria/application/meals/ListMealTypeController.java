/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.meals;

import eapli.ecafeteria.domain.meals.MealType;
import eapli.framework.application.Controller;

/**
 *
 * @author Bernardo Carreira
 */
public class ListMealTypeController implements Controller {
    
    private final ListMealTypeService svc = new ListMealTypeService();

    public Iterable<MealType> listDishTypes() {
        return this.svc.activeMealTypes();
    }
}
