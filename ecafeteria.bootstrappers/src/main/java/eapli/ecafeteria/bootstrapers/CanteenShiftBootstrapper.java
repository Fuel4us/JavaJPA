package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.kitchen.RegisterCanteenShiftController;
import eapli.ecafeteria.domain.finance.WorkSession;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.WorkSessionRepository;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static eapli.ecafeteria.application.kitchen.Utilities.createCurrentDate;

public class CanteenShiftBootstrapper implements Action {
    
    @Override
    public boolean execute() {
        try {
            register();
        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            Logger.getLogger(AllergenBootstrapper.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public void register() throws DataIntegrityViolationException, DataConcurrencyException {
        final RegisterCanteenShiftController csController = new RegisterCanteenShiftController();
        final WorkSessionRepository wsRepository = PersistenceContext.repositories().workSession();
        
        Iterable<WorkSession> Iws = wsRepository.findAll();
        WorkSession ws1 = Iws.iterator().next();
        
        //para um dia que já passou
        csController.registerCanteenShift("2018-01-01", ws1);
        
        //para o dia atual
        csController.registerCanteenShift(createCurrentDate(), ws1);
    }
}
