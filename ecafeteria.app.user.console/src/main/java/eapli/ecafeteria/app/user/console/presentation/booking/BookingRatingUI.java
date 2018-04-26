/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.user.console.presentation.booking;

import eapli.ecafeteria.application.booking.BookingRatingController;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.framework.util.Console;

/**
 *
 * @author Rúben - 1160998
 */
public class BookingRatingUI{
    
    private final BookingRatingController controller = new BookingRatingController();

    boolean show() {
        Iterable<Booking> booking = controller.getBookingsForRating();
        for(Booking b: booking)
            System.out.println(b.toString());
        
        String bookingName = Console.readLine("Choose Booking:");
        
        Booking choosen = controller.chooseBooking(bookingName, booking);
        
        int score = Console.readInteger("Insert Rating (1-5):");
        String comment = Console.readLine("Insert comment (optional):");
        
        controller.createRating(choosen, score, comment);
        
        return true;
    }

}
