/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.framework.domain.Designation;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author Bernardo Carreira
 * @EDIT Pedro Alves <1150372@isep.ipp.pt>
 */
public class JpaMealRepository extends CafeteriaJpaRepositoryBase<Meal, Long> implements MealRepository {

    @Override
    public Optional<Meal> findById(Long id) {
        final Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return matchOne("e.id=:id", params);
    }

    @Override
    public Iterable<Meal> findAllByLot(Long lotId) {
        final Map<String, Object> params = new HashMap<>();
        params.put("lotId", lotId);

        return match("e.lotId = :lotId", params);
    }

    @Override
    public Iterable<Meal> findByIdMenu(Long idMenu) {
        final Map<String, Object> params = new HashMap<>();
        params.put("idMenu", idMenu);

        return match("e.idMenu = :idMenu", params);
    }
}
