/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.ecafeteria.application.kitchen.RegisterLotsUsedInMealController;
import eapli.ecafeteria.domain.kitchen.Material;
import eapli.ecafeteria.persistence.MaterialRepository;
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

    protected Controller controller() {
        return this.controller;
    }
    
    @Override
    protected boolean doShow() {
        /*final int lotCode = Console.readInteger("Lot Code:");
        final long ingredientCode = Console.readLong("Ingredient Code:");
        final int quantity = Console.readInteger("Quantity:");

        try {
            Material ingredient = matRepository.findOne(ingredientCode);
            this.controller.registerMealLot(lotCode, ingredient, quantity);
        } catch (final DataConcurrencyException | DataIntegrityViolationException e) {
            System.out.println("That acronym is already in use.");
        }*/
        return false;

    }

    @Override
    public String headline() {
        return "Register Lots Used in Meal";
    }
}
