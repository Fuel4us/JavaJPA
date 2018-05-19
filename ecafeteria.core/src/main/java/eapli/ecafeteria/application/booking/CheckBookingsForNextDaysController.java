/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.persistence.DataConcurrencyException;
import java.util.Date;
import java.util.Optional;

/**
 *
 * @author Mário Vaz
 */
public class CheckBookingsForNextDaysController {
    
    /**
     * Booking repository
     */
    private final BookingRepository bookingRepository = PersistenceContext.repositories().booking();
    
    /**
     * Cafeteria user repository
     */
    private final CafeteriaUserRepository userRepository = PersistenceContext.repositories().cafeteriaUsers();
    
    /**
     * Returns the user that has the same username has the one indicated
     * 
     * @param username Username to determine user
     * @return CafeteriaUser
     * @throws DataConcurrencyException 
     */
    public Optional<CafeteriaUser> findUserByUsername(Username username) throws DataConcurrencyException {
        return userRepository.findByUsername(username);
    }
    
     /**
     * Returns the list of bookings in reserved state for the next X days
     *
     * @param user User
     * @param date Starting date to compare
     * @return List of bookings
     */
    public Iterable<Booking> findBookingsForNextDays(Optional <CafeteriaUser> user, Date date){
        return bookingRepository.checkBookingsForNextDays(user, date);
    }
    
}
