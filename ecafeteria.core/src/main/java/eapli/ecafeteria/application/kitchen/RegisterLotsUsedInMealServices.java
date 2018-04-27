/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.domain.kitchen.Lot;
import eapli.ecafeteria.domain.kitchen.Material;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.LotRepository;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pedro Rodrigues (1140572)
 */
public class RegisterLotsUsedInMealServices {

    private final MealRepository mealRepository = PersistenceContext.repositories().meals();
    private final LotRepository lotRepository = PersistenceContext.repositories().lots();

    public RegisterLotsUsedInMealServices() {

    }

    public List<Meal> getAllMeals() {
        List<Meal> listMeals = new ArrayList<>();
        for (Meal meal : mealRepository.findAll()) {
            listMeals.add(meal);
        }
        return listMeals;
    }
    
    /*
    public List<Lot> getLotsFromMeal(Meal meal) {
        List<Lot> listLots = new ArrayList<>();
        for (Lot lot : lotRepository.findAll()) {
            for (Material material : meal.getDish().getIngredients()) {
                if (material.equals(lot.getIngredient())) {
                    listLots.add(lot);
                }
            }
        }
        return listLots;
    }
*/
}
