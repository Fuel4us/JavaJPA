package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.framework.actions.Action;

/**
 * @author Gonçalo Fonseca - 1150503@isep.ipp.pt
 */
public class CloseMealPlanAction implements Action {

    @Override
    public boolean execute() {
        return new CloseMealPlanUI().show();
    }
}
