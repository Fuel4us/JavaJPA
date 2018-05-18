package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.dishes.Allergen;
import eapli.ecafeteria.persistence.AllergenRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;
import java.util.Optional;

/**
 *
 * @author João Pereira <1150478@isep.ipp.pt>
 */
public class InMemoryAllergensRepository extends InMemoryRepository<Allergen, Long> implements AllergenRepository {

    /**
     * Method that finds the allergen with the id passed by parameter.
     *
     * @param id
     * @return
     */
    @Override
    public Optional<Allergen> findById(Long id) {
        return matchOne(e -> e.id().equals(id));
    }

    /**
     * Method that returns the id of the allergen passed by parameter.
     *
     * @param entity
     * @return
     */
    @Override
    protected Long newKeyFor(Allergen entity) {
        return entity.id();
    }

}
