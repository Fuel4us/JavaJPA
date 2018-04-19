package eapli.ecafeteria.app.backoffice.console.presentation.meals;

import eapli.ecafeteria.domain.meals.MealType;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.util.Console;

/**
 *
 * @author Pedro Alves <1150372@isep.ipp.pt>
 */
public class RegisterMealUI extends AbstractUI {

//    private final RegisterMealController theController = new RegisterMealController();
//
//    protected Controller controller() {
//        return this.theController;
//    }

    @Override
    protected boolean doShow() {
//        final Iterable<MealType> mealTypes = this.theController.MealTypes();
//
//        final SelectWidget<MealType> selector = new SelectWidget<>("Dish types:", mealTypes, new DishTypePrinter());
//        selector.show();
//        final MealType theMealType = selector.selectedElement();
//
//        final String name = Console.readLine("Name");
//
//        try {
//            this.theController.registerDish(theMealType, name);
//        } catch (final DataIntegrityViolationException | DataConcurrencyException e) {
//            System.out.println("You tried to enter a dish which already exists in the database.");
//        }

        return false;
    }

    @Override
    public String headline() {
        return "Register Dish";
    }
    
}
