package eapli.ecafeteria.app.backoffice.console.presentation.ratings;

import eapli.framework.actions.Action;

/**
 *
 * @author Pedro Alves 
 */
public class CheckRatingsAction implements Action {

    @Override
    public boolean execute() {
        return new CheckRatingsUI().doShow();
    }
}
