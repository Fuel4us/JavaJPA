package eapli.ecafeteria.app.backoffice.console.presentation.menus;

import eapli.framework.actions.Action;

/**
 *
 * @author Pedro Alves 
 */
public class EditMenuAction implements Action {

    @Override
    public boolean execute() {
        return new EditMenuUI().show();
    }
}
