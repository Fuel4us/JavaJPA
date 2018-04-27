package eapli.ecafeteria.app.pos.console.presentation.finance;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import eapli.framework.actions.Action;

/**
 *
 * @author 11607
 */
public class OpenShiftAction implements Action {

    @Override
    public boolean execute() {
        return new OpenShiftUI().show();
    }
    
}
