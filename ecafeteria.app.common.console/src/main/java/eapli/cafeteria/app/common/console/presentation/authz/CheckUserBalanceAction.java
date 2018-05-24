    package eapli.cafeteria.app.common.console.presentation.authz;

    import eapli.framework.actions.Action;

    /**
     * @author Gonçalo Fonseca - 1150503@isep.ipp.pt
     */
    public class CheckUserBalanceAction implements Action {
        @Override
        public boolean execute() {
            return new CheckUserBalanceUI().show();
        }
    }
