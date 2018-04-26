/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafetaria.app.pos.console.presentation.finance;

import eapli.ecafetaria.application.finance.ChargeCardController;
import eapli.framework.presentation.console.AbstractUI;

/**
 *
 * @author Hernani Gil
 */
public class ChargeCardUI extends AbstractUI {

    private final ChargeCardController chargeCardController = new ChargeCardController();
    
    @Override
    protected boolean doShow() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String headline() {
        return "Charge Card";
    }
    
}
