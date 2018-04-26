package eapli.ecafeteria.app.user.console.presentation.booking;

import eapli.framework.actions.Action;

/**
 *
 * @author ruben
 */
public class BookingRatingAction implements Action {
    
    @Override
    public boolean execute() {
        return new BookingRatingUI().doShow();
    }
}
