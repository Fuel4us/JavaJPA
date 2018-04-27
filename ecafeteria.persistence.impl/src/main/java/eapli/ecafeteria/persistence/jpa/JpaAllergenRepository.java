package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.dishes.Allergens;
import eapli.ecafeteria.domain.dishes.AllergensList;

import eapli.ecafeteria.persistence.AllergenRepository;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author João Pereira <1150478@isep.ipp.pt>
 */
public class JpaAllergenRepository extends CafeteriaJpaRepositoryBase<Allergens, Long> implements AllergenRepository {

    @Override
    public Optional<Allergens> findById(Long id) {
        final Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return matchOne("e.id=:id", params);
    }

}
