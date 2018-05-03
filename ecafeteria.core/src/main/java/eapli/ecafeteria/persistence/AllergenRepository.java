package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.dishes.Allergen;
import eapli.framework.persistence.repositories.DataRepository;
import java.util.Optional;

/**
 *
 * @author João Pereira <1150478@isep.ipp.pt>
 */
public interface AllergenRepository extends DataRepository<Allergen, Long> {

    Optional<Allergen> findById(Long id);
}
