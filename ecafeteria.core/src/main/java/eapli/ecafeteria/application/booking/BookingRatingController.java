/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.Rating;
import eapli.framework.application.Controller;

/**
 *
 * @author Rúben - 1160998
 */
public class BookingRatingController implements Controller {

    public BookingRatingController() {
    }

    public Iterable<Booking> getBookingsForRating() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Booking chooseBooking(String bookingName, Iterable<Booking> booking) {
        for(Booking b: booking){
            if(b.id().equalsIgnoreCase(bookingName))
              return b;  
        }
        return null;
    }

    public void createRating(Booking choosen, int score, String comment) {
        Rating rating = new Rating(score, comment);
        
        choosen.rating(rating);
    }
    
}
