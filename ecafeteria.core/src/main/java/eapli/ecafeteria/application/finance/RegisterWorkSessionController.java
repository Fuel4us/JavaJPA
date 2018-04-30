package eapli.ecafeteria.application.finance;

import eapli.ecafeteria.domain.finance.WorkSession;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.WorkSessionRepository;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

public class RegisterWorkSessionController implements Controller{
    
    private final WorkSessionRepository wsRepository = PersistenceContext.repositories().workSession();

    public WorkSession registerWorkSession(Integer workSessionCode) throws DataIntegrityViolationException, DataConcurrencyException {

        final WorkSession ws = new WorkSession(workSessionCode);
        return this.wsRepository.save(ws);
    }
}
