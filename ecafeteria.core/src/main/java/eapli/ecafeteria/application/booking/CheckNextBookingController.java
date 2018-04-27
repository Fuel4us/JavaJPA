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
 * @author Tiago Correia
 */
public class CheckNextBookingController {
    
    private final BookingRepository bookingRepository = PersistenceContext.repositories().booking();
    private final CafeteriaUserRepository userRepository = PersistenceContext.repositories().cafeteriaUsers();
    
    public Optional<CafeteriaUser> findUserByUsername(Username username) throws DataConcurrencyException {
        return userRepository.findByUsername(username);
    }
    
    public Booking getNextBooking(Optional <CafeteriaUser> user, Date date){
        return bookingRepository.getNextBooking(user, date);
    }
}
