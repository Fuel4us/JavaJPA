package eapli.ecafeteria.app.backoffice.console.presentation.meals;

import eapli.framework.actions.Action;

/**
 *
 * @author João Pereira <1150478@isep.ipp.pt>
 */
public class CheckMealRatingsAction implements Action {
    
     @Override
    public boolean execute() {
        return new CheckMealRatingsUI().show();
    }
}
