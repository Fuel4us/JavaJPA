package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.dishes.Allergen;
import eapli.ecafeteria.domain.dishes.AllergensList;
import eapli.ecafeteria.persistence.AllergenRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;
import java.util.Optional;

/**
 *
 * @author João Pereira <1150478@isep.ipp.pt>
 */
public class InMemoryAllergensRepository extends InMemoryRepository<Allergen, Long> implements AllergenRepository {

    @Override
    public Optional<Allergen> findById(Long id) {
        return matchOne(e -> e.id().equals(id));
    }

    @Override
    protected Long newKeyFor(Allergen entity) {
        return entity.id();
    }

}
