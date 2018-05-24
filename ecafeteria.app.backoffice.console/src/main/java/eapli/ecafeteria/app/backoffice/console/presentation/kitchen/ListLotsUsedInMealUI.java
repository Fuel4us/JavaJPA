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
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;
import java.util.List;

/**
 *
 * @author Carlos Figueiredo (1140317)
 */
public class ListLotsUsedInMealUI extends AbstractUI {

    private final ListLotsUsedInMealController controller = new ListLotsUsedInMealController();

    protected Controller controller() {
        return this.controller;
    }

    /**
     * core method from UI class
     * @return 
     */
    @Override
    protected boolean doShow() {

        System.out.println("Select your meal : \n");
        Meal meal = selectMeal();
        List<Lot> listLotsByMeal = this.controller.getLotsByMeal(meal);
        System.out.println("Lots Used in selected meal : \n" + listLotsByMeal);

        return false;

    }

    /**
     * select a meal from all meals method
     * @return selected meal
     */
    public Meal selectMeal() {
        List<Meal> listMeals = this.controller.getAllMeals();
        for (int i = 0; i < listMeals.size(); i++) {
            System.out.println(i + 1 + " - " + this.controller.getAllMeals().get(i));
        }
        int selectMeal = Console.readInteger("Choose Meal ID:");
        return listMeals.get(selectMeal - 1);
    }

    /**
     * headline
     * @return String
     */
    @Override
    public String headline() {
        return "List Lots Used in Meal";
    }

}
