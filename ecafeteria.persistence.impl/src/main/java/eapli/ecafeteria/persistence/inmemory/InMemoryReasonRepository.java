package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.authz.Reason;
import eapli.ecafeteria.domain.authz.ReasonType;
import eapli.ecafeteria.persistence.ReasonRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepositoryWithLongPK;

/**
 * In Memory Reason repository
 * @author pedromonteiro
 */
public class InMemoryReasonRepository extends InMemoryRepositoryWithLongPK<Reason> implements ReasonRepository{

    @Override
    public Iterable<Reason> findByReasonType(ReasonType rt) {
        return match(e -> e.reasonType().equals(rt));
    }

    
}
