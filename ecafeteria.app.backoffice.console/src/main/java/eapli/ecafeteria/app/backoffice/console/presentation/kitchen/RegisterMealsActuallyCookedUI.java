/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.ecafeteria.application.kitchen.RegisterMealsActuallyCookedController;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos Figueiredo (1140317)
 */
public class RegisterMealsActuallyCookedUI extends AbstractUI {

    private final RegisterMealsActuallyCookedController controller = new RegisterMealsActuallyCookedController();

    protected Controller controller() {
        return this.controller;
    }

    @Override
    protected boolean doShow() {
        
        this.controller.listMeals();
        final long mealCode = Console.readLong("Meal ID: ");
        final int cookedMeals = Console.readInteger("Meals Actually Made: ");

        try {
            if (this.controller.checkedMeal(mealCode)) {
                this.controller.registerMealsActuallyMade(mealCode, cookedMeals);
            }
        } catch (DataIntegrityViolationException | DataConcurrencyException ex) {
            Logger.getLogger(RegisterMealsActuallyCookedUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;

    }

    @Override
    public String headline() {
        return "Register Meals Actually Cooked";
    }

}
