/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.ecafeteria.application.kitchen.ListLotsUsedInMealController;
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
 * @author Carlos Figueiredo (1140317)
 */
public class ListLotsUsedInMealUI extends AbstractUI {

    private final ListLotsUsedInMealController controller = new ListLotsUsedInMealController();

    protected Controller controller() {
        return this.controller;
    }

    @Override
    protected boolean doShow() {

        Meal meal = selectMeal();
        List<Lot> listLotsByMeal = this.controller.getLotsByMeal(meal);
        System.out.println(listLotsByMeal);

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

    public Lot selectLot(List<Lot> listLotsByMeal) {
        if (listLotsByMeal.isEmpty()) {
            System.out.println("Não existem lotes com ingredientes registados para esta refeição!");
        } else {

            for (int i = 0; i < listLotsByMeal.size(); i++) {
                System.out.println(i + 1 + " - " + listLotsByMeal.get(i).toString3());
            }
            int selectLot = Console.readInteger("Lot ID:");
            while (selectLot <= 0 || selectLot > listLotsByMeal.size()) {
                System.out.println("ID Inválido!!!");
                selectLot = Console.readInteger("Lot ID:");
            }
            if (selectLot != 0 && !listLotsByMeal.isEmpty()) {
                return listLotsByMeal.get(selectLot - 1);
            }
        }
        return new Lot(0, null, 0);
    }

    @Override
    public String headline() {
        return "List Lots Used in Meal";
    }

}
