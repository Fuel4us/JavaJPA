package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.framework.persistence.repositories.DataRepository;
import java.util.Date;

/**
 * Booking Repository
 *
 */
public interface BookingRepository extends DataRepository<Booking, Long> {

	Iterable<Booking> checkBookingsForNextDays(CafeteriaUser currentUser, Date currentDate);
        
        
        public Booking getNextBooking();
        
        
        
}
