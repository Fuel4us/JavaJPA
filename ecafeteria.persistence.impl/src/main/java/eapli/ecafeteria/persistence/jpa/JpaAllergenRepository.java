package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.dishes.Allergen;

import eapli.ecafeteria.persistence.AllergenRepository;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author @João Pereira_1150478@isep.ipp.pt
 */
public class JpaAllergenRepository extends CafeteriaJpaRepositoryBase<Allergen, Long> implements AllergenRepository {

    /**
     * Method that returns the allergen with the id passed by parameter.
     *
     * @param id
     * @return
     */
    @Override
    public Optional<Allergen> findById(Long id) {
        final Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return matchOne("e.id=:id", params);
    }

}
