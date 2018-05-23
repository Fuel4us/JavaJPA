package ratings;

import eapli.framework.actions.Action;

/**
 *
 * @author Pedro Alves <1150372@isep.ipp.pt>
 */
public class CheckRatingsAction implements Action {

    @Override
    public boolean execute() {
        return new CheckRatingsUI().show();
    }
}
