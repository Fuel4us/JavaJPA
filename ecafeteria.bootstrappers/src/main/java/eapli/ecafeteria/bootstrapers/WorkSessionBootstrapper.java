package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.finance.RegisterWorkSessionController;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

public class WorkSessionBootstrapper implements Action{

    @Override
    public boolean execute() {
        try {
            register();
        } catch (DataIntegrityViolationException | DataConcurrencyException ex) {
            return false;
        }

        return true;
    }
    
    public void register() throws DataIntegrityViolationException, DataConcurrencyException {

        final RegisterWorkSessionController wsController = new RegisterWorkSessionController();
        
        wsController.registerWorkSession(1);
    }
}
