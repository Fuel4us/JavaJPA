package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.framework.persistence.repositories.DataRepository;

/**
 * Booking Repository
 *
 */
public interface BookingRepository extends DataRepository<Booking, Long> {

	Iterable<Booking> checkBookingsForNextDays();
}
