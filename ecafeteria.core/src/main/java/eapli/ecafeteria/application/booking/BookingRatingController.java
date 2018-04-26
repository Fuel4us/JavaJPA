/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.Rating;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import java.util.Optional;

/**
 *
 * @author Rúben - 1160998
 */
public class BookingRatingController implements Controller {
    
    private final BookingRepository repBooking = PersistenceContext.repositories().booking();
    private final CafeteriaUserRepository repCafeteriaUser = PersistenceContext.repositories().cafeteriaUsers();

    public Iterable<Booking> getBookingsForRating(Optional<CafeteriaUser> user) {
        Iterable<Booking> b = repBooking.findBookingsDeliveredByUser(user);
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

    public Optional<CafeteriaUser> getUser() {
        SystemUser su = AuthorizationService.session().authenticatedUser();
        Optional<CafeteriaUser> user = repCafeteriaUser.findByUsername(su.username());
        return user;
    }
    
}
