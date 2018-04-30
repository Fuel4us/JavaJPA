package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.domain.finance.WorkSession;
import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.kitchen.CanteenShift;
import eapli.ecafeteria.domain.kitchen.CanteenShiftState;
import eapli.ecafeteria.persistence.CanteenShiftRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

public class RegisterCanteenShiftController {

    private final CanteenShiftRepository csRepository = PersistenceContext.repositories().canteenShift();

    public CanteenShift registerCanteenShift(String date, CanteenShiftState cfs, WorkSession ws) throws DataIntegrityViolationException, DataConcurrencyException {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);

        final CanteenShift cs = new CanteenShift(date, cfs, ws);
        return this.csRepository.save(cs);
    }
}
