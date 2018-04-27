package eapli.ecafeteria.app.pos.console.presentation.finance;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import eapli.ecafeteria.application.finance.OpenPOSController;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;
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
        Iterable<Long> posList = theController.showPOSList();

        for (Long id : posList) {
            posString += "POS - " + id + "\n";
        }

        final long posID = Console.readLong(posString);
        try {
            if (!theController.openPOS(posID)) {
                System.out.println("The is POS already opened!\n");
                //return false; //Dúvida - o que acontece quando este método retorna false?
            }
        } catch (Exception e) {
            Logger.getLogger(OpenPOSUI.class.getName()).log(Level.SEVERE, null, e);
        }

        return true;

    }
}
