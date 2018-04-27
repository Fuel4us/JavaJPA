/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.user.console.presentation.booking;

import eapli.ecafeteria.application.booking.CheckMenuController;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;

/**
 *
 * @author Joao Reis 1160600
 */
public class CheckMenuUI extends AbstractUI {

    CheckMenuController controller = new CheckMenuController();

    @Override
    protected boolean doShow() {
        System.out.println("This week's Menu");
        for (Meal m : controller.currentWeekMenu()) {
            System.out.println(m);
        }

        char res = Console.readLine("Check next week's menu? ('y' para continuar)").toLowerCase().charAt(0);

        if (res == 'y') {
            for (Meal m : controller.nextWeekMenu()) {
                System.out.println(m);
            }
        }
        return true;
    }

    @Override
    public String headline() {
        return "Show Menu";
    }

}
