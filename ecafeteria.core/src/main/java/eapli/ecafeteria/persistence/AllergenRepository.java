package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.dishes.Allergens;
import eapli.framework.persistence.repositories.DataRepository;
import java.util.Optional;

/**
 *
 * @author João Pereira <1150478@isep.ipp.pt>
 */
public interface AllergenRepository extends DataRepository<Allergens, Long> {
    
    Optional<Allergens> findById(Long id);
}
