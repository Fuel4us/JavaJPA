package eapli.ecafeteria.app.pos.console.presentation.finance;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import eapli.cafeteria.app.common.console.presentation.authz.LoginAction;
import eapli.ecafeteria.app.pos.console.presentation.MainMenu;
import eapli.ecafeteria.application.finance.ClosePOSServiceController;
import eapli.ecafeteria.application.finance.OpenPOSController;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.finance.POS;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Josué Lapa
 */
public class ClosePOSServiceUI extends AbstractUI{
    private final ClosePOSServiceController theController = new ClosePOSServiceController();

    protected Controller controller() {
        return this.theController;
    }

    @Override
    public String headline() {
        return "Close POS";
    }

    @Override
    protected boolean doShow() {

        try {
            if (theController.closeCurrentPOS()) {
                System.out.println("The current POS is now closed\n");
                
                System.out.println("\nDelivery Summary: \n\n" + theController.deliverySummary() + "\n");
                
                System.out.println("\nMeals Not Delivered: \n\n");
                
//                for(Booking b : theController.unusedBookedMeals()){
//                    System.out.println(b.toString() + "\n");
//                }

                // logout
                if (new LoginAction(ActionRight.SALE).execute()) {
                    final MainMenu menu = new MainMenu();
                    menu.mainLoop();
                    System.exit(0);
                }
            }else{
                System.out.println("POS already closed!\n");
            }
            
            
        } catch (Exception e) {
            Logger.getLogger(OpenPOSUI.class.getName()).log(Level.SEVERE, null, e);
        }

        return true;

    }
}
