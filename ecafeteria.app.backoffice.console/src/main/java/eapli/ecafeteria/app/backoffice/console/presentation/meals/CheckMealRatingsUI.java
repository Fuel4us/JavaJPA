package eapli.ecafeteria.app.backoffice.console.presentation.meals;

import eapli.ecafeteria.application.meals.CheckMealRatingController;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

/**
 *
 * @author João Pereira <1150478@isep.ipp.pt>
 */
public class CheckMealRatingsUI extends AbstractUI {

    private final CheckMealRatingController controller = new CheckMealRatingController();
    int average = 0, count = 0;

    @Override
    protected boolean doShow() {

        if (controller.getMealsOfBookings().isEmpty()) {
            System.out.println("There are no bookings or meals.");

            return true;
        }

        final SelectWidget<Meal> selector = new SelectWidget<>("Select one meal to see the rating:", controller.getMealsOfBookings());
        selector.show();
        final Meal meal = selector.selectedElement();

        Iterable<Booking> bookingList = controller.getBookingsOfMeal(meal);

        System.out.println("\nNumber of ratings: " + bookingList.toString() + ";");

        for (Booking book : bookingList) {
            average += book.getRating().getScore();
            count++;
        }
        average /= count;

        System.out.println("\nAverage score: " + average);

        System.out.println("\nComments: ");
        for (Booking book : bookingList) {
            if (book.getRating().getComment() != null) {
                System.out.println("-> " + book.getRating().getComment());
            }
        }

        return true;
    }

    @Override
    public String headline() {
        return "Check meal rating";
    }
}
