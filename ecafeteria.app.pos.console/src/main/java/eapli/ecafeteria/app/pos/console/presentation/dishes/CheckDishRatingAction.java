/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.pos.console.presentation.dishes;

import eapli.framework.actions.Action;

/**
 *
 * @author João Pires <your.name at your.org>
 */
public class CheckDishRatingAction implements Action {

    @Override
    public boolean execute() {
        return new CheckDishRatingUI().show();
    }

}
