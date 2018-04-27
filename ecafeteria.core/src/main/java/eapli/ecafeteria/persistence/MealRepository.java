/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.persistence.repositories.DataRepository;
import java.util.Optional;

/**
 *
 * @author Berccar
 */
public interface MealRepository extends DataRepository<Meal, Long> {

    Optional<Meal> findById(Long id);

    Iterable<Meal> findAllByLot(Long lotId);
    
    Iterable<Meal> findByIdMenu(Long idMenu);
}
