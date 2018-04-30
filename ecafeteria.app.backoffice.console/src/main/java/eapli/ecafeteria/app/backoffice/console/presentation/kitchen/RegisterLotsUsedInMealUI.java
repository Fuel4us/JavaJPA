/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.ecafeteria.application.kitchen.RegisterLotsUsedInMealController;
import eapli.ecafeteria.domain.kitchen.Lot;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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

        try {
            Meal meal = selectMeal();
            Lot lot = selectLot(meal);
            if (lot != null) {
                int quantityUsed = Console.readInteger("Quantity Used:");
                this.controller.registerMealLot(meal, lot, quantityUsed);
            } 
        } catch (DataIntegrityViolationException | DataConcurrencyException ex) {
            Logger.getLogger(RegisterLotsUsedInMealUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;

    }

    public Meal selectMeal() {
        List<Meal> listMeals = this.controller.getAllMeals();
        for (int i = 0; i < listMeals.size(); i++) {
            System.out.println(i + 1 + " - " + this.controller.getAllMeals().get(i));
        }
        int selectMeal = Console.readInteger("Meal ID:");
        return listMeals.get(selectMeal - 1);
    }

    public Lot selectLot(Meal meal) {
        List<Lot> listLotsByMeal = this.controller.getLotsByMeal(meal);
        if (listLotsByMeal.isEmpty()) {
            System.out.println("Não existem lotes com ingredientes registados para esta refeição!");
        } else {

            for (int i = 0; i < listLotsByMeal.size(); i++) {
                System.out.println(i + 1 + " - " + this.controller.getLotsByMeal(meal).get(i).toString3());
            }
            int selectLot = Console.readInteger("Lot ID:");
            while (selectLot < 0 || selectLot > listLotsByMeal.size() + 1) {
                System.out.println("ID Inválido!!!");
                selectLot = Console.readInteger("Lot ID:");
            }
            return listLotsByMeal.get(selectLot - 1);
        }
        return null;
    }

    @Override
    public String headline() {
        return "Register Lots Used in Meal";
    }
}
