package eapli.ecafeteria.app.backoffice.console.presentation.meals;

import eapli.framework.actions.Action;

/**
 *
 * @author pedro
 */
public class ChangeMealTypeAction implements Action {

    @Override
    public boolean execute() {
        return new ChangeMealTypeUI().show();
    }
}
