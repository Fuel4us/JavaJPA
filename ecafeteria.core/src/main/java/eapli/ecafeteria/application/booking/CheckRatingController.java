/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.Rating;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.persistence.DataConcurrencyException;
import java.util.ArrayList;
import java.util.Optional;

/**
 *
 * @author Hilario Coelho
 */
public class CheckRatingController {
    private final CafeteriaUserRepository userRepository = PersistenceContext.repositories().cafeteriaUsers();
    private final BookingRepository bookingRepository = PersistenceContext.repositories().booking();
    
    /**
     * Finds and returns an user by his username
     * @param username Username
     * @return User (if found)
     * @throws DataConcurrencyException 
     */
    public Optional<CafeteriaUser> findUserByUsername(Username username) throws DataConcurrencyException {
        return userRepository.findByUsername(username);
    }
    
    /**
     * Returns all bookings in Delivered State from an User
     * @param user User
     * @return Bookings
     */
    private Iterable<Booking> getBookingsDeliveredFromUser(CafeteriaUser user) {
        return bookingRepository.findBookingsDeliveredByUser(user);
    }
    
    /**
     * Returns all ratings from an User
     * @param user User
     * @return Ratings
     */
    public Iterable<Rating> getRatingsFromUser(CafeteriaUser user) {
        Iterable<Booking> bookings = getBookingsDeliveredFromUser(user);
        ArrayList<Rating> ratings = new ArrayList<>();
        
        for(Booking b : bookings) {
            Rating r = b.getRating();
            if(r != null)
                ratings.add(r);
        }
        
        return ratings;
    }
}
