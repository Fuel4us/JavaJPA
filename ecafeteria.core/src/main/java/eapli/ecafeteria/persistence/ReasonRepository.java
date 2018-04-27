package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.authz.Reason;
import eapli.ecafeteria.domain.authz.ReasonType;
import eapli.framework.persistence.repositories.DataRepository;

/**
 * The repository for Reason
 * @author pedromonteiro
 */
public interface ReasonRepository extends DataRepository<Reason, Long>{
    
    
}
