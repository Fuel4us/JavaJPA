package eapli.ecafeteria.application.meals;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author João Pereira <1150478@isep.ipp.pt>
 */
public class CheckMealRatingController implements Controller {

    private final BookingRepository repBooking = PersistenceContext.repositories().booking();

    public Iterable<Booking> getBookingsDelivered() {
        return repBooking.findBookingsDelivered();
    }

    public Set<Meal> getMealsOfBookings() {
        Set<Meal> mealList = new HashSet<>();
        Iterable<Booking> bookingList = getBookingsDelivered();

        for (Booking book : bookingList) {
            mealList.add(book.getMeal());
        }

        return mealList;
    }

    public Iterable<Booking> getBookingsOfMeal(Meal meal) {
        return repBooking.findBookingsForMeal(meal);
    }

}
