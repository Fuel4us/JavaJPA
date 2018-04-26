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
import java.util.Optional;

/**
 *
 * @author Pedro Rodrigues (1140572)
 */
public class RegisterLotsUsedInMealUI extends AbstractUI {

    private final RegisterLotsUsedInMealController controller = new RegisterLotsUsedInMealController();
    private final MaterialRepository matRepository = PersistenceContext.repositories().materials();
    private final MealRepository mealRepository = PersistenceContext.repositories().meals();

    protected Controller controller() {
        return this.controller;
    }

    @Override
    protected boolean doShow() {

        int i = 1, j = 1;
        final int lotCode = Console.readInteger("Lot Code:");
        for (Material mat : matRepository.findAll()) {
            System.out.println(j + " - " + mat.toString());
            j++;
        }
        final long ingredientCode = Console.readLong("Ingredient Code:");
        final int quantity = Console.readInteger("Quantity:");
        for (Meal meal : mealRepository.findAll()) {
            System.out.println(i + " - " + meal.toString());
            i++;
        }
        final long idMeal = Console.readLong("Meal ID:");

        try {
            Optional<Material> ingredient = matRepository.findOne(ingredientCode);
            Optional<Meal> meal = mealRepository.findById(idMeal);
            if (ingredient.isPresent() && meal.isPresent()) {
                Lot lot = this.controller.registerLot(lotCode, ingredient.get(), quantity);
                this.controller.registerMealLot(meal.get(), lot);
            } else {
                System.out.println("Ingredient or meal doesn't exist.");
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
