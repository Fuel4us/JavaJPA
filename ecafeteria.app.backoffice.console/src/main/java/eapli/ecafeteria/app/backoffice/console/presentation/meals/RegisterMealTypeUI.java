package eapli.ecafeteria.app.backoffice.console.presentation.meals;

import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;

/**
 *
 * @author  Pedro Alves <1150372@isep.ipp.pt>
 */
public class RegisterMealTypeUI extends AbstractUI {

//    private final RegisterMealTypeController theController = new RegisteMealTypeController();

//    protected Controller controller() {
//        return this.theController;
//    }

    @Override
    protected boolean doShow() {
//        final String acronym = Console.readLine("Meal Type Acronym:");
//        final String description = Console.readLine("Meal Type Description:");
//
//        try {
//            this.theController.registerDishType(acronym, description);
//        } catch (final DataConcurrencyException | DataIntegrityViolationException e) {
//            System.out.println("That acronym is already in use.");
//        }
        return false;
    }

    @Override
    public String headline() {
        return "Register Meal Type";
    }
    
}
