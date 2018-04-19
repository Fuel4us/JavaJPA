package eapli.ecafeteria.app.user.console.presentation.booking;

import eapli.framework.actions.Action;

/**
 *
 * @author ruben
 */
public class MealRatingAction implements Action {
    
    @Override
    public boolean execute() {
        return new MealRatingUI().show();
    }
}
