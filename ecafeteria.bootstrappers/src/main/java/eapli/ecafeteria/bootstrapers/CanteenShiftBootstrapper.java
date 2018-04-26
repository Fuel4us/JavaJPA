package eapli.ecafeteria.bootstrapers;

import eapli.ecafetaria.domain.finance.WorkSession;
import eapli.ecafeteria.application.kitchen.RegisterCanteenShiftController;
import eapli.ecafeteria.domain.kitchen.CanteenShift;
import eapli.ecafeteria.domain.kitchen.CanteenShiftState;
import eapli.ecafeteria.persistence.CanteenShiftRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Calendar;

/**
 *
 * FALTA CHAMAR O CONSTRUTOR DA WORKSECTION (AINDA NAO EXISTE)
 */
public class CanteenShiftBootstrapper implements Action{

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
        final CanteenShiftRepository csRepository = PersistenceContext.repositories().canteenShift();

        for(CanteenShift cs : csRepository.findAll()) {
            new RegisterCanteenShiftController().registerLot(Calendar.getInstance(), CanteenShiftState.OPEN, new WorkSession());
        }
    }
}
