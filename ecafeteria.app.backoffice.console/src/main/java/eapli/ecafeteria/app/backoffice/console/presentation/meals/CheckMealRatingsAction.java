package eapli.ecafeteria.app.backoffice.console.presentation.meals;

import eapli.framework.actions.Action;

/**
 *
 * @author @João Pereira_1150478@isep.ipp.pt
 */
public class CheckMealRatingsAction implements Action {

    /**
     * Starts the UI.
     *
     * @return
     */
    @Override
    public boolean execute() {
        return new CheckMealRatingsUI().show();
    }
}
