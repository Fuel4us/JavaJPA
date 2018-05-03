/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.domain.kitchen.Execution;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.ExecutionRepository;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

import java.util.Iterator;

/**
 *
 * @author Carlos Figueiredo (1140317)
 */
public class ExecutionBootstrapper implements Action {

    @Override
    public boolean execute() {
        try {
            final MealRepository mealRepository = PersistenceContext.repositories().meals();


            Iterator<Meal> it = mealRepository.findAll().iterator();
            final Meal meal1 = it.next();
            final Meal meal2 = it.next();
            final Meal meal3 = it.next();
            Execution execution1= new Execution(meal1,40);
            Execution execution2 = new Execution(meal2,60);
            Execution execution3 = new Execution(meal3,100);
            register(execution1);
            register(execution2);
            register(execution3);
        } catch (DataIntegrityViolationException | DataConcurrencyException ex) {
            return false;
        }

        return true;
    }
    
    public void register(Execution execution) throws DataIntegrityViolationException, DataConcurrencyException {
        final ExecutionRepository executionRepository = PersistenceContext.repositories().execution();
        executionRepository.save(execution);
    }
}
