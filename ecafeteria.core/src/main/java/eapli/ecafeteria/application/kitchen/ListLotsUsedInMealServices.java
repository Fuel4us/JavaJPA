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
 * @author Carlos Figueiredo (1140317)
 */
public class ListLotsUsedInMealServices {

    /**
     *  meal repository variable
     */
    private final MealRepository mealRepository = PersistenceContext.repositories().meals();
    
    /**: 
     *lot repository variable
     */
    private final LotRepository lotRepository = PersistenceContext.repositories().lots();

    /**
     * empty contructor for ORM efects
     */
    public ListLotsUsedInMealServices() { 

    }

    /**
     * Save all meals in arraylist
     * @return listMeals
     */
    public List<Meal> getAllMeals() {
        List<Meal> listMeals = new ArrayList<>();
        for (Meal meal : mealRepository.findAll()) {
            listMeals.add(meal);
        }
        return listMeals;
    }

    /**
     * save all lots in arraylist
     * @param meal
     * @return listLots
     */
    public List<Lot> getLotsFromMeal(Meal meal) {
        List<Lot> listLots = new ArrayList<>();
        for (Lot lot : lotRepository.findAll()) {
            for (Material material : meal.getDish().getIngredientsList()) {
                if (material.equals(lot.getIngredient())) {
                    listLots.add(lot);
                }
            }
        }
        return listLots;
    }
 
}
