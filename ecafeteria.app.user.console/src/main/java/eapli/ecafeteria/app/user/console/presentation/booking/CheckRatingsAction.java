package eapli.ecafeteria.app.user.console.presentation.booking;

import eapli.framework.actions.Action;

public class CheckRatingsAction implements Action{

    @Override
    public boolean execute() {
        return new BookingRatingUI().doShow();
    }
    
}
