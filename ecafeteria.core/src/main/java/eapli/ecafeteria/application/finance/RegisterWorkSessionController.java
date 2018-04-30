package eapli.ecafeteria.application.finance;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.finance.WorkSession;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.WorkSessionRepository;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

public class RegisterWorkSessionController {
    
    private final WorkSessionRepository wsRepository = PersistenceContext.repositories().workSession();

    public WorkSession registerCanteenShift(Integer workSessionCode) throws DataIntegrityViolationException, DataConcurrencyException {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.SALE);

        final WorkSession ws = new WorkSession(workSessionCode);
        return this.wsRepository.save(ws);
    }
}
