package eapli.ecafeteria.app.backoffice.console.presentation.menus;

import eapli.framework.actions.Action;

/**
 *
 * @author Pedro Alves <1150372@isep.ipp>
 */
public class RegisterMenuAction implements Action {

    @Override
    public boolean execute() {
        return new RegisterMenuUI().show();
    }
}
