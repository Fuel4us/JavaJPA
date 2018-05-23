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
public class CheckMealRatingsController implements Controller {

    private final BookingRepository repBooking = PersistenceContext.repositories().booking();
    private int count = 0, flag1 = 0, flag2 = 0, commentFlag = 0;
    private double average = 0;
    private String comment = " ";

    /**
     * Returns the bookings that are on delivered state.
     *
     * @return
     */
    public Iterable<Booking> getBookingsDelivered() {
        return repBooking.findBookingsDelivered();
    }

    /**
     * Returns the meals of the bookings on delivered state.
     *
     * @return
     */
    public Set<Meal> getMealsOfBookings() {
        Set<Meal> mealList = new HashSet<>();
        Iterable<Booking> bookingList = getBookingsDelivered();

        for (Booking book : bookingList) {
            mealList.add(book.getMeal());
        }

        return mealList;
    }

    /**
     * Returns the bookings of a meal passed by parameter.
     *
     * @param meal
     * @return
     */
    public Iterable<Booking> getBookingsOfMeal(Meal meal) {
        return repBooking.findBookingsForMeal(meal);
    }

    /**
     * Returns the number of ratings of a meal.
     *
     * @param selectedMeal
     * @return
     */
    public int getNumberOfRatings(Meal selectedMeal) {
        for (Booking booking : getBookingsOfMeal(selectedMeal)) {
            if (booking.getRating() != null) {
                System.out.println("One rating found on the booking with the id: " + booking.bookingId());
                flag1++;
            } else {
                System.out.println("There are no ratings on booking with the id: " + booking.bookingId());
                flag2 = 0;
            }
        }
        if (flag1 == 0) {
            return flag2;
        }
        return flag1;
    }

    /**
     * Returns the average score of ratings.
     *
     * @param selectedMeal
     * @return
     */
    public double getAverage(Meal selectedMeal) {
        for (Booking book : getBookingsOfMeal(selectedMeal)) {
            if (book.getRating() != null) {
                average += book.getRating().getScore();
                count++;
            }
        }
        average /= count;

        return average;
    }

    /**
     * Returns the comments and/or answers, if it exists, of ratings.
     *
     * @param selectedMeal
     * @return
     */
    public String getComments(Meal selectedMeal) {
        for (Booking book : getBookingsOfMeal(selectedMeal)) {
            if (book.getRating() != null) {
                if (book.getRating().getComment() != null) {
                    if (book.getRating().getComment().getAnswer() == null) {
                        comment += "\n      * Comment: " + book.getRating().getComment().getRealComment() + ";";
                    } else {
                        comment += "\n      * Comment: " + book.getRating().getComment().getRealComment() + ";\n      * Answer: " + book.getRating().getComment().getAnswer() + ";";
                    }
                } else {
                    System.out.println("This rating has no comments.");
                }
            }
        }
        return comment;
    }

}
