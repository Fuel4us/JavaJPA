/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import java.util.Date;

/**
 *
 * @author Mário Vaz
 */
public class CheckBookingsForNextDaysController {
    
    private final BookingRepository bookingRepository = PersistenceContext.repositories().booking();
    //for next 7 days
    
    public Iterable<Booking> findBookingsForNextDays(CafeteriaUser user, Date date){
        return bookingRepository.checkBookingsForNextDays(user, date);
    }
    
}
