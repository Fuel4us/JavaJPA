package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.framework.actions.Action;

/**
 * @author Gonçalo Silva (1161140)
 */
public class FindMealsByLotAction implements Action {

    @Override
    public boolean execute() {
        return new FindMealsByLotUI().show();
    }
}
