package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.kitchen.RegisterCanteenShiftController;
import eapli.ecafeteria.domain.finance.WorkSession;
import eapli.ecafeteria.domain.kitchen.CanteenShiftState;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.WorkSessionRepository;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

public class CanteenShiftBootstrapper implements Action {
    
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
        final RegisterCanteenShiftController csController = new RegisterCanteenShiftController();
        final WorkSessionRepository wsRepository = PersistenceContext.repositories().workSession();
        
        WorkSession ws1 = wsRepository.findOne(1).get();
        
        csController.registerCanteenShift("20180430", CanteenShiftState.OPEN, ws1);
        
    }
}
