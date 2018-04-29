package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.dishes.Allergens;
import eapli.ecafeteria.domain.dishes.AllergensList;
import eapli.ecafeteria.persistence.AllergenRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;
import java.util.Optional;

/**
 *
 * @author João Pereira <1150478@isep.ipp.pt>
 */
public class InMemoryAllergensRepository extends InMemoryRepository<Allergens, Long> implements AllergenRepository {

    @Override
    public Optional<Allergens> findById(Long id) {
        return matchOne(e -> e.id().equals(id));
    }

    @Override
    protected Long newKeyFor(Allergens entity) {
        return entity.id();
    }

}
