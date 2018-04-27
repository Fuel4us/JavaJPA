/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.ecafeteria.application.kitchen.RegisterLotsUsedInMealController;
import eapli.ecafeteria.domain.kitchen.Lot;
import eapli.ecafeteria.domain.kitchen.Material;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.MaterialRepository;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;

/**
 *
 * @author Pedro Rodrigues (1140572)
 */
public class RegisterLotsUsedInMealUI extends AbstractUI {

    private final RegisterLotsUsedInMealController controller = new RegisterLotsUsedInMealController();
    

    protected Controller controller() {
        return this.controller;
    }

    @Override
    protected boolean doShow() {

        this.controller.listMeals();
        final long idMeal = Console.readLong("Meal ID:");
        final int lotCode = Console.readInteger("Lot Code:");
        this.controller.listMaterials();
        final String ingredientCode = Console.readLine("Ingredient Acronym:");
        final int quantity = Console.readInteger("Quantity:");

        try {
            
            if (this.controller.checkMaterial(ingredientCode) && this.controller.checkMeal(idMeal)) {
               this.controller.registerLot(lotCode, ingredientCode, quantity);
                this.controller.registerMealLot(idMeal, lotCode);
            } else {
                System.out.println("Ingredient or meal invalid.");
            }
        } catch (final DataConcurrencyException | DataIntegrityViolationException e) {
            System.out.println("");
        }
        return false;

    }

    @Override
    public String headline() {
        return "Register Lots Used in Meal";
    }
}
