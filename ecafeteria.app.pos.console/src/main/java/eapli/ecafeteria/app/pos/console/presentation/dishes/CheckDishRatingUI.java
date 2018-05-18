/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.pos.console.presentation.dishes;

import eapli.ecafeteria.application.dishes.CheckDishRatingController;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;

/**
 *
 * @author João Pires <your.name at your.org>
 */
public class CheckDishRatingUI extends AbstractUI {

    private final CheckDishRatingController controller = new CheckDishRatingController();

    protected Controller controller() {
        return (Controller) this.controller;
    }

    @Override
    protected boolean doShow() {
        return false;
    }

    @Override
    public String headline() {
        return "Check Dish Rating";
    }

}
