/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.administration;

import eapli.framework.actions.Action;

/**
 *
 * @author 1160629@isep.ipp.pt
 */
public class ConfigureKitchenAlertLimitsAction implements Action{

    @Override
    public boolean execute() {
        return new ConfigureKitchenAlertLimitsUI().show();
    }
    
}
