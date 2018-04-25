/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafetaria.app.pos.console.presentation.finance;

import eapli.ecafetaria.application.finance.OpenPOSController;
import eapli.ecafetaria.domain.finance.POS;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 11607
 */
public class OpenPOSUI extends AbstractUI {

    private final OpenPOSController theController = new OpenPOSController();

    protected Controller controller() {
        return this.theController;
    }

    @Override
    public String headline() {
        return "Open POS";
    }

    @Override
    protected boolean doShow() {
        String posString = "POS List \n\n";
        List<POS> posList = theController.showPOSList();
        int posOption = 0;

        for (POS p : posList) {
            posString += posOption + " - " + p.toString() + "\n";
            posOption++;
        }

        final int posIndex = Console.readInteger(posString);
        try {
            theController.openPOS(posList.get(posIndex));
        } catch (Exception e) {
            Logger.getLogger(OpenPOSUI.class.getName()).log(Level.SEVERE, null, e);
        }

        return true;

    }
}
