package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.kitchen.Execution;
import eapli.ecafeteria.persistence.ExecutionRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepositoryWithLongPK;

/**
 *
 * @author Carlos Figueiredo (1140317)
 */
public class InMemoryExecutionRepository extends InMemoryRepositoryWithLongPK<Execution> implements ExecutionRepository {

    
}
