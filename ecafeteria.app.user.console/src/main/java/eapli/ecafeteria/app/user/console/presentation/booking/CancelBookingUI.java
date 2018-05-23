/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.user.console.presentation.booking;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.application.booking.CancelBookingController;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hilario Coelho
 */
public class CancelBookingUI extends AbstractUI {
    private final CancelBookingController controller = new CancelBookingController();
    
    @Override
    protected boolean doShow() {
        SystemUser systemUser = AuthorizationService.session().authenticatedUser();
        Optional<CafeteriaUser> user = controller.getUser();
        
        Iterable<Booking> bookings = controller.findBookings(user);
        for(Booking b : bookings)
            System.out.println(
                "\nBooking nº " + b.bookingId() + ":\nData: " + b.getMeal().getMealDate() + 
                " | Prato: " + b.getMeal().getDish().name() +
                " (" + b.getMeal().getMealType() +
                ") | Preco: " + b.getMeal().getDish().currentPrice().amount() + " " +
                b.getMeal().getDish().currentPrice().currency()
            );
        
        String bookingId = Console.readLine("Type the booking number to cancel:");
        
        Booking booking = controller.getBooking(bookingId, bookings);
        if(booking == null) {
            System.out.println("Booking not found!");
            return true;
        }
        
        double amount = controller.amountToRefund(booking);
        
        String answer;
        do {
            answer = Console.readLine(String.format("\nAre you sure you want to cancel booking number %s?\nYou will get %.2f€ back. (y/n)", bookingId, amount));
        } while(!answer.equals("n") && !answer.equals("y"));
        
        if(answer.equals("y")) 
            confirmCancel(booking);
        
        return true;
    }

    @Override
    public String headline() {
        return "Cancel bookings";
    }
    
    private boolean confirmCancel(Booking booking) {
        boolean flag = true;

        try {
            flag = controller.cancelBooking(booking);
        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            Logger.getLogger(CancelBookingUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (!flag) {
            System.out.println("\nBooking ID not found!");
        } else {
            System.out.println("\nBooking successfully canceled!");
        }
        
        return flag;
    }
}
