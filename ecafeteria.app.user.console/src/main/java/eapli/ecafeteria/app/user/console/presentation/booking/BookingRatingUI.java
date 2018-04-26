/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.user.console.presentation.booking;

import eapli.ecafeteria.application.booking.BookingRatingController;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;
import java.util.Optional;

/**
 *
 * @author Rúben - 1160998
 */
public class BookingRatingUI extends AbstractUI {
    
    private final BookingRatingController controller = new BookingRatingController();

    @Override
    protected boolean doShow() {
        Optional<CafeteriaUser> user = controller.getUser();
        
        Iterable<Booking> booking = controller.getBookingsForRating(user);
        int i = 0;
        for(Booking b: booking){
            i++;
            System.out.println(i + ": " + b.toString());
        }
        
        String bookingName = Console.readLine("Choose Booking:");
        
        Booking choosen = controller.chooseBooking(bookingName, booking);
        
        int score = Console.readInteger("Insert Rating (1-5):");
        String comment = Console.readLine("Insert comment (optional):");
        
        controller.createRating(choosen, score, comment);
        
        return true;
    }

    @Override
    public String headline() {
        return "Rate Booking";
    }

}