package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.ecafeteria.application.kitchen.CheckBookingsByDataController;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;

/**
 *
 * @author Diogo Monteiro
 */
public class CheckReservationsByDataUI extends AbstractUI {
    CheckBookingsByDataController controller = new CheckBookingsByDataController();
    @Override
    protected boolean doShow() {

        int choice = Console.readInteger("What information do you want to use for the search?\n1 - Day\n2 - Meal Type\n3- Dish\n4 - Meal");
        if(choice>0&&choice<5){
            controller.run(choice);
        }
        return true;
    }

    @Override
    public String headline() {
        return "Check Reservations By Data";
    }
}
