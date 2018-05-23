/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.meals;

import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.PersistenceContext;

/**
 *
 * @author Pedro Rodrigues (1140572)
 */
public class MealServices {
    
    private final MealRepository mealRepo = PersistenceContext.repositories().meals();
    
    public MealServices(){
        
    }
}
