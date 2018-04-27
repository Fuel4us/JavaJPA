package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.authz.Reason;
import eapli.ecafeteria.persistence.ReasonRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;

/**
 * In Memory Reason repository
 * @author pedromonteiro
 */
public class InMemoryReasonRepository extends InMemoryRepository<Reason, Long> implements ReasonRepository{

    @Override
    protected Long newKeyFor(Reason entity) {
        return entity.id();
    }

    
}
