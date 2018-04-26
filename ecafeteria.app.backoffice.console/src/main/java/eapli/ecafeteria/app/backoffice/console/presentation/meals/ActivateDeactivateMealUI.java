package eapli.ecafeteria.app.backoffice.console.presentation.meals;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pedro Alves <1150372@isep.ipp.pt>
 */
public class ActivateDeactivateMealUI extends AbstractUI {

    //private final ActivateDeactivateMealController theController = new ActivateDeactivateMealController();

//    protected Controller controller() {
//        return this.theController;
//    }

    @Override
    protected boolean doShow() {

        //final Iterable<Meal> allDishes = this.theController.allDishes();
//        if (!allMeals.iterator().hasNext()) {
//            System.out.println("There are no registered Meals");
//        } else {
//            final SelectWidget<Meal> selector = new SelectWidget<>("Meal:", allMeals, new MealPrinter());
//            selector.show();
//            final Meal updtMeal = selector.selectedElement();
//            try {
//                this.theController.changeMealState(updtMeal);
//            } catch (DataConcurrencyException ex) {
//                System.out
//                        .println("It is not possible to change the dish state because it was changed by another user");
//            } catch (DataIntegrityViolationException ex) {
//                // should not happen!
//                Logger.getLogger(ActivateDeactivateMealTypeUI.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
        return true;
    }

    @Override
    public String headline() {
        return "Activate / Deactivate Dishes";
    }

}
