/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.meals;

import eapli.ecafeteria.domain.kitchen.MealPlan;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.MealPlanItemQuantityRepository;
import eapli.ecafeteria.persistence.MealPlanRepository;
import eapli.ecafeteria.persistence.PersistenceContext;

/**
 *
 * @author Pedro Rodrigues (1140572)
 */
public class MealPlanServices {

    private final MealPlanRepository mealPlanRepo = PersistenceContext.repositories().mealplans();
    private final MealPlanItemQuantityRepository mealPlanItemRepo = PersistenceContext.repositories().mealplanitemquantities();
    
    public MealPlanServices() {

    }

    public int findQuantityByMeal(Meal meal){
        return mealPlanItemRepo.findByMeal(meal).getItemQuantity();
    }
}
