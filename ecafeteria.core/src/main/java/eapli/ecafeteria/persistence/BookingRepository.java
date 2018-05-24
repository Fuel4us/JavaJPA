package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.dishes.DishType;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.booking.Complaint;
import eapli.ecafeteria.domain.booking.Rating;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.framework.persistence.repositories.DataRepository;
import java.util.Date;
import java.util.Optional;

/**
 * Booking Repository changed by @João Pereira_1150478@isep.ipp.pt
 *
 */
public interface BookingRepository extends DataRepository<Booking, Long> {

    /**
     * Returns the list of bookings in reserved state for the next X days
     *
     * @param currentUser User
     * @param currentDate Starting date to compare
     * @return List of bookings
     */
    Iterable<Booking> checkBookingsForNextDays(Optional<CafeteriaUser> currentUser, Date currentDate);

    Iterable<Booking> findBookingByUserAndDate(Optional<CafeteriaUser> user, MealType mealType, BookingState reservationState);

    Iterable<Booking> findBookingByDate(MealType mealType, DishType dishType, BookingState reservationState);

    Iterable<Booking> listBookedMealsByCUser(CafeteriaUser cafUser);
    
    /** 
     * Gets all booking from an User in Reserved State
     * @param user User
     * @return List of bookings
     */
    Iterable<Booking> getBookings(Optional<CafeteriaUser> user);

    public Booking getNextBooking(Optional<CafeteriaUser> user, Date date);

    /**
     * Returns all bookings in delivered state from an User
     * @param user User
     * @return List of bookings
     */
    Iterable<Booking> findBookingsDeliveredByUser(CafeteriaUser user);

    Iterable<Booking> findBookingsDelivered();

    Iterable<Booking> findBookingsForMeal(Meal meal);

    /**
     * Return user's bookings for the current week.
     *
     * @param user current user
     * @return
     */
    Iterable<Booking> checkBookingsForCurrentWeek(CafeteriaUser user);

    public void updateBookingRating(Booking choosen, Rating rating);
    
    public void updateBookingComplaint(Booking booking, Complaint complaint);

    public void updateBookingStateCanceled(Booking booking);

    public void updateBookingStateDelivered(Booking booking);

}
