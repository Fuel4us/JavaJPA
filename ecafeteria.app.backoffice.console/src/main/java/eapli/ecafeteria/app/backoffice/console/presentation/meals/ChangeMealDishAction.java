package eapli.ecafeteria.app.backoffice.console.presentation.meals;

import eapli.framework.actions.Action;

/**
 *
 * @author Pedro Alves 1150372@isep.ipp.pt
 */
public class ChangeMealDishAction implements Action {

    @Override
    public boolean execute() {
        return new ChangeMealDishUI().show();
    }
}
