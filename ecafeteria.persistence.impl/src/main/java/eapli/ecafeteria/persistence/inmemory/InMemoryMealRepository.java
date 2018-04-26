/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.framework.domain.Designation;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;
import java.util.Optional;

/**
 *
 * @author Bernardo Carreira
 */
public class InMemoryMealRepository extends InMemoryRepository<Meal, Long> implements MealRepository {

    @Override
    public Optional<Meal> findById(Long id) {
//        return matchOne(e -> e.name().equals(name));
        return null;
    }

    @Override
    protected Long newKeyFor(Meal entity) {
//        return entity.id();
        return null;
    }

    @Override
    public Iterable<Meal> findAllByLot(Long lotId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
