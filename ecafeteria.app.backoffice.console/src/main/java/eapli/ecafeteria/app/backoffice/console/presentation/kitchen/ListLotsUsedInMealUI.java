/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.ecafeteria.application.kitchen.ListLotsUsedInMealController;
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
    
        public Meal selectMeal() {
        List<Meal> listMeals = this.controller.getAllMeals();
        for (int i = 0; i < listMeals.size(); i++) {
            System.out.println(i + 1 + " - " + this.controller.getAllMeals().get(i));
        }
        int selectMeal = Console.readInteger("Meal ID:");
        return listMeals.get(selectMeal - 1);
    }

    @Override
    protected boolean doShow() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String headline() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
