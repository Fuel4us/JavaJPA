/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.pos.console.presentation.booking;

import eapli.ecafeteria.application.booking.DeliverBookingController;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Ana Mafalda Silva
 */
public class DeliverBookingUI extends AbstractUI {
    
    private final DeliverBookingController controller  =  new DeliverBookingController();
    Booking booking;
    
    protected Controller controller() {
        return this.controller();
    }

    @Override
    protected boolean doShow() {
        String flag;
        boolean flag2;
        do {
            final String mecNum = Console.readLine("Input the mecanographic number:");
            controller.cafetariaUserToSee(mecNum);
            controller.showBookedBookings();
            int choice = Console.readInteger("Please choose a booking: ");
            Long choice1 = new Long(choice);
            flag2 = controller.choice(choice1);
            if(flag2==true){
            try {
                if(controller.registerDelivery()){
                    System.out.println("Success!!");
                }
            }   catch (DataIntegrityViolationException | DataConcurrencyException ex) {
                    Logger.getLogger(DeliverBookingUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            System.out.println("");
            flag = Console.readLine("Do you want to register delivery again? (Y/N)");
        } while (flag.equalsIgnoreCase("Y") || flag.equalsIgnoreCase("y"));
        return false;
        
    }
    @Override
    public String headline() {
        return "Confirme Deliver Booking";
    }
  
    
}
