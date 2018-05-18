package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.dishes.Allergen;
import eapli.framework.persistence.repositories.DataRepository;
import java.util.Optional;

/**
 *
 * @author João Pereira <1150478@isep.ipp.pt>
 */
public interface AllergenRepository extends DataRepository<Allergen, Long> {

    /**
     * Method that finds the allergen with the id passed by parameter.
     *
     * @param id
     * @return
     */
    Optional<Allergen> findById(Long id);
}
