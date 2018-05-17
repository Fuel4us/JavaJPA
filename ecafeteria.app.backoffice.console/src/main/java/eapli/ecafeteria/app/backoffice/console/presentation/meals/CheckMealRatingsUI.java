package eapli.ecafeteria.app.backoffice.console.presentation.meals;

import eapli.ecafeteria.application.meals.CheckMealRatingsController;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

/**
 *
 * @author João Pereira <1150478@isep.ipp.pt>
 */
public class CheckMealRatingsUI extends AbstractUI {

    private final CheckMealRatingsController controller = new CheckMealRatingsController();
    int average = 0, count = 0, flag = 0, commentFlag = 0;

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

        for (Booking booking : bookingList) {
            if (booking.getRating() != null) {
                flag++;
            } else {
                System.out.println("There are no ratings.");
                return true;
            }
        }

        System.out.println("\n## RATING INFO of the Meal with the ID  " + meal.getId() + ":\n");
        System.out.println("\n-> Number of Ratings:  " + flag);

        for (Booking book : bookingList) {
            average += book.getRating().getScore();
            count++;
        }
        average /= count;

        System.out.println("\n-> Average Score:  " + average);

        System.out.println("\n-> Comments: ");
        for (Booking book : bookingList) {
            if (book.getRating().getComment() != null) {
                commentFlag++;
                if (book.getRating().getComment().getResposta() == null) {
                    System.out.println("* Comment number " + commentFlag + ":  " + book.getRating().getComment().getRealComment() + ";");
                } else {
                    System.out.println("* Comment number " + commentFlag + ":  \nComment: " + book.getRating().getComment().getRealComment() + ";\nAnswer: " + book.getRating().getComment().getResposta() + ";");
                }
            }
        }

        return true;
    }

    @Override
    public String headline() {
        return "Check meal rating";
    }
}
