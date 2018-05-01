/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.menus.Menu;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;
import java.util.Optional;

/**
 *
 * @author Bernardo Carreira
 * @EDIT Pedro Alves <1150372@isep.ipp.pt>
 */
public class InMemoryMealRepository extends InMemoryRepository<Meal, Long> implements MealRepository {

    @Override
    public Optional<Meal> findById(Long id) {
        return matchOne(e -> e.getId().equals(id));
    }

    @Override
    protected Long newKeyFor(Meal entity) {
        return entity.getId();
    }

    @Override
    public Iterable<Meal> findAllByLot(Long lotId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterable<Meal> findByIdMenu(Long idMenu) {
        return match(e -> e.getId().equals(idMenu));
    }

    @Override
    public Iterable<Meal> findAllMealsAvailables(Menu menu) {
        return match(e -> e.getMenu() == null && e.insertMenu(menu));
    }
}
