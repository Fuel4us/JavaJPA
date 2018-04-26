/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
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
    
    private final BookingRepository bookingRepository = PersistenceContext.repositories().booking();
    private final CafeteriaUserRepository userRepository = PersistenceContext.repositories().cafeteriaUsers();
    
    public Optional<CafeteriaUser> findUserByNumber(MecanographicNumber numberUser) throws DataConcurrencyException {
        return userRepository.findByMecanographicNumber(numberUser);
    }
    
    public Iterable<Booking> findBookingsForNextDays(Optional <CafeteriaUser> user, Date date){
        return bookingRepository.checkBookingsForNextDays(user, date);
    }
    
}
