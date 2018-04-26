package eapli.ecafeteria.app.backoffice.console.presentation.meals;

import eapli.ecafeteria.domain.meals.MealType;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pedro Alves <1150372@isep.ipp.pt>
 */
public class ActivateDeactivateMealTypeUI extends AbstractUI {

//    private final ActivateDeactivateMealTypeController theController = new ActivateDeactivateMealTypeController();
//    protected Controller controller() {
//        return this.theController;
//    }
    @Override
    protected boolean doShow() {

//        final Iterable<MealType> allMealTypes = this.theController.allMealTypes();
//        if (!allMealTypes.iterator().hasNext()) {
//            System.out.println("There are no registered Dish Types");
//        } else {
//            final SelectWidget<MealType> selector = new SelectWidget<>("Meal types:", allMealTypes, new MealTypePrinter());
//            selector.show();
//            final MealType updtDishType = selector.selectedElement();
//            try {
//                this.theController.changeMealTypeState(updtDishType);
//            } catch (DataConcurrencyException ex) {
//                System.out.println(
//                        "It is not possible to change the dish type state because it was changed by another user");
//            } catch (DataIntegrityViolationException ex) {
//                // should not happen!
//                Logger.getLogger(ActivateDeactivateMealTypeUI.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
        return true;
    }

    @Override
    public String headline() {
        return "Activate / Deactivate Dish Types";
    }

}
