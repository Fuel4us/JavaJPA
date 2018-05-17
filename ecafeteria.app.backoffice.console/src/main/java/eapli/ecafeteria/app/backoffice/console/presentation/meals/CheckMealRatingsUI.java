package eapli.ecafeteria.app.backoffice.console.presentation.meals;

import eapli.ecafeteria.application.meals.CheckMealRatingsController;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

/**
 *
 * @author João Pereira <1150478@isep.ipp.pt>
 */
public class CheckMealRatingsUI extends AbstractUI {

    private final CheckMealRatingsController controller = new CheckMealRatingsController();

    @Override
    protected boolean doShow() {

        if (controller.getMealsOfBookings().isEmpty()) {
            System.out.println("There are no bookings or meals.");
            return true;
        }

        final SelectWidget<Meal> selector = new SelectWidget<>("Select one meal to see the rating:", controller.getMealsOfBookings());
        selector.show();
        final Meal selectedMeal = selector.selectedElement();

        int numOfRatings = controller.getNumberOfRatings(selectedMeal);
        if (numOfRatings == 0) {
            return true;
        }

        System.out.println("\n## RATING INFO of the Meal with the ID  " + selectedMeal.getId() + ":\n");

        System.out.println("\n-> Number of Ratings:  " + numOfRatings);

        System.out.println("\n-> Average Score:  " + controller.getAverage(selectedMeal));

        System.out.println("\n-> Comments: " + controller.getComments(selectedMeal));

        return true;
    }

    @Override
    public String headline() {
        return "Check meal rating";
    }
}
