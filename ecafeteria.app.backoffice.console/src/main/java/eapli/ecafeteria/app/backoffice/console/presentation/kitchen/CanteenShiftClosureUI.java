package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.ecafeteria.application.kitchen.CanteenShiftClosureController;
import eapli.framework.presentation.console.AbstractUI;

public class CanteenShiftClosureUI extends AbstractUI {

    private final CanteenShiftClosureController controller = new CanteenShiftClosureController();

    @Override
    protected boolean doShow() {
        if (controller.canteenShiftClosure() == true) {
            System.out.println("The operation is over");
            return true;
        }
        System.out.println("The operation wasn't concluded");
        return false;
    }

    @Override
    public String headline() {
        return "Begins the closure of canteen shift";
    }

}
