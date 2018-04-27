/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.persistence.ExecutionRepository;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

/**
 *
 * @author Carlos Figueiredo (1140317)
 */
public class ExecutionBootstrapper implements Action {

    @Override
    public boolean execute() {
        try {
            register();
        } catch (DataIntegrityViolationException | DataConcurrencyException ex) {
            return false;
        }

        return true;
    }
    
    public void register() throws DataIntegrityViolationException, DataConcurrencyException {
        final MealRepository mealRepository = PersistenceContext.repositories().meals();
        final ExecutionRepository executionRepository = PersistenceContext.repositories().execution();

        
    }
}
