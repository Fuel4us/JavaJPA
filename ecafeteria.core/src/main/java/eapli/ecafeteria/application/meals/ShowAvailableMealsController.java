/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.meals;

import eapli.ecafeteria.domain.*;
import eapli.ecafeteria.persistence.*;

/**
 *
 * @author João Pires <your.name at your.org>
 */
public class ShowAvailableMealsController {
    
    private final DishTypeRepository dishTypeRepo = PersistenceContext.repositories().dishTypes();
    
    private final MealRepository mealRepo = PersistenceContext.repositories().meals();
    
}
