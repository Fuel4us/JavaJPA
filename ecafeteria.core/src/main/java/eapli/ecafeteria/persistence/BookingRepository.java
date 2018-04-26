package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.framework.persistence.repositories.DataRepository;
import java.util.Date;
import java.util.Optional;

/**
 * Booking Repository
 *
 */
public interface BookingRepository extends DataRepository<Booking, Long> {

	Iterable<Booking> checkBookingsForNextDays(Optional <CafeteriaUser> currentUser, Date currentDate);
        
        Iterable<Booking> findBookingByUserAndDate(Optional <CafeteriaUser> user, MealType mealType, BookingState reservationState);
        
        
        public Booking getNextBooking();
        
        
        
}
