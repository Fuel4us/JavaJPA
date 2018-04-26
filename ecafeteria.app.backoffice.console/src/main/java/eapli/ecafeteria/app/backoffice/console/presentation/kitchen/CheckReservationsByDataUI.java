package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;

public class CheckReservationsByDataUI extends AbstractUI {
    @Override
    protected boolean doShow() {

        int choice = Console.readInteger("What information do you want to use for the search?\n1 - Day\n2 - Type\n3- Dish\n4 - Meal");
        return true;
    }

    @Override
    public String headline() {
        return "Check Reservations By Data";
    }
}
