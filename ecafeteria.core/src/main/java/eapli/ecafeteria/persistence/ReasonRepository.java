package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.authz.Reason;
import eapli.ecafeteria.domain.authz.ReasonType;
import eapli.framework.persistence.repositories.DataRepository;

/**
 * The repository for Reason
 * @author pedromonteiro
 */
public interface ReasonRepository extends DataRepository<Reason, Long>{
    
    /**
     * Find the reasons by Reason Type
     * @param rt Reason Type
     * @return an Iterable of the reasons with rt
     */
    Iterable<Reason> findByReasonType(ReasonType rt);
    
}
