package eapli.ecafeteria.bootstrapers;

import eapli.ecafetaria.domain.finance.WorkSession;
import eapli.ecafeteria.application.kitchen.RegisterCanteenShiftController;
import eapli.ecafeteria.domain.kitchen.CanteenShiftState;
import eapli.ecafeteria.persistence.CanteenShiftRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Calendar;

/**
 *
 * WORKSECTION POR IMPLEMENTAR
 */
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
        //final CanteenShiftRepository csRepository = PersistenceContext.repositories().canteenShift();

        Calendar c1 = Calendar.getInstance();
        c1.set(2018, 3, 27);

        Calendar c2 = Calendar.getInstance();
        c1.set(2018, 0, 1);

        new RegisterCanteenShiftController().registerCanteenShift(c1, CanteenShiftState.OPEN, new WorkSession());

        new RegisterCanteenShiftController().registerCanteenShift(c2, CanteenShiftState.CLOSED, new WorkSession());
    }
}
