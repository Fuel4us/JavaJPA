package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.framework.actions.Action;

/**
 * @author Gonçalo Fonseca - 1150503@isep.ipp.pt
 */
public class CloseMealPlanAction implements Action {

    /**
     * method from Action that executes the Menu created in CloseMealPlanUI
     * @return
     */
    @Override
    public boolean execute() {
        return new CloseMealPlanUI().show();
    }
}
