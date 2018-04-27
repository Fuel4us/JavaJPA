/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.administration;

import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;
import eapli.ecafeteria.application.administration.ConfigureKitchenAlarmLimitsController;

/**
 *
 * @author 1160629@isep.ipp.pt
 */
public class ConfigureKitchenAlertLimitsUI extends AbstractUI{
    
    private final ConfigureKitchenAlarmLimitsController controller = new ConfigureKitchenAlarmLimitsController();

    @Override
    protected boolean doShow() {
        if(!controller.configureYellowLimit(Console.readLong("Input yellow limit value:"))){
            System.out.println("Unable to configure yellow limit");
            return true;
        }
        if(!controller.configureRedLimit(Console.readLong("Input red limit value:"))){
            System.out.println("Unable to configure red limit");
            return true;
        }
        controller.save();
        return true;
    }

    @Override
    public String headline() {
        return "Configure Kitchen Alert Limits";
    }
    
}
