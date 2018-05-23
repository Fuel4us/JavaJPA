package eapli.ecafeteria.app.backoffice.console.presentation.meals;

import eapli.framework.actions.Action;

/**
 *
 * @author Pedro Alves 
 */
public class RegisterMealAction implements Action {

    @Override
    public boolean execute() {
        return new RegisterMealUI().show();
    }
}
