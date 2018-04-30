package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.domain.finance.WorkSession;
import eapli.ecafeteria.domain.kitchen.CanteenShift;
import eapli.ecafeteria.domain.kitchen.CanteenShiftState;
import eapli.ecafeteria.persistence.CanteenShiftRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

public class RegisterCanteenShiftController implements Controller{

    private final CanteenShiftRepository csRepository = PersistenceContext.repositories().canteenShift();
    
    public CanteenShift registerCanteenShift(String date, CanteenShiftState cfs, WorkSession ws1) throws DataIntegrityViolationException, DataConcurrencyException {
        //AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);
        
        final CanteenShift cs = new CanteenShift(date, cfs, ws1);
        return this.csRepository.save(cs);
    }
}
