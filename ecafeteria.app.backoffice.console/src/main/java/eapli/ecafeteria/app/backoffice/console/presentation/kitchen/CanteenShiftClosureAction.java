package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.framework.actions.Action;

public class CanteenShiftClosureAction implements Action{

    @Override
    public boolean execute() {
        return new CanteenShiftClosureUI().show();
    }
    
}
