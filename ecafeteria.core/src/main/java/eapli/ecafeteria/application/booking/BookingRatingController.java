/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.Comment;
import eapli.ecafeteria.domain.booking.Rating;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.RatingRepository;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.ArrayList;
import java.util.Optional;

/**
 *
 * @author Rúben - 1160998
 */
public class BookingRatingController implements Controller {
    
    private final RatingRepository repRating = PersistenceContext.repositories().rating();
    private final BookingRepository repBooking = PersistenceContext.repositories().booking();
    private final CafeteriaUserRepository repCafeteriaUser = PersistenceContext.repositories().cafeteriaUsers();
    //private final CommentRepository repComment = PersistenceContext.repositories().comments();

    public Iterable<Booking> getBookingsForRating(Optional<CafeteriaUser> optUser) {
        if(optUser.isPresent()){
            CafeteriaUser user = optUser.get();
            Iterable<Booking> list = repBooking.findBookingsDeliveredByUser(user);
            ArrayList<Booking> res = new ArrayList<>();
            for(Booking b : list){
                res.add(b);
            }
            return (Iterable<Booking>) res;
        }
        
        System.out.println("User not found");
        return null;
    }

    public Booking chooseBooking(String bookingName, Iterable<Booking> booking) {
        for(Booking b: booking){
            if(b.bookingId().equalsIgnoreCase(bookingName))
              return b;  
        }
        return null;
    }

    public void createRating(Booking choosen, int score, String comment) throws DataConcurrencyException, DataIntegrityViolationException {
        Rating rating = new Rating(score, comment);
        
        rating = repRating.save(rating);
        
        repBooking.updateBookingRating(choosen, rating);
    }

    public Optional<CafeteriaUser> getUser() {
        SystemUser su = AuthorizationService.session().authenticatedUser();
        Optional<CafeteriaUser> user = repCafeteriaUser.findByUsername(su.username());
        return user;
    }
    
}
