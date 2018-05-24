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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

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

            Random random = new Random();
            int low = 70;
            int high = 110;
            while(it.hasNext()){
                Execution execution = new Execution(it.next(),random.nextInt(high-low)+low);
                execution.getPicked().setPickedQuantity(random.nextInt(70));
                register(execution);

            }

            /*execution4.getPicked().setPickedQuantity(40);
            execution5.getPicked().setPickedQuantity(60);
            execution6.getPicked().setPickedQuantity(85);*/

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
