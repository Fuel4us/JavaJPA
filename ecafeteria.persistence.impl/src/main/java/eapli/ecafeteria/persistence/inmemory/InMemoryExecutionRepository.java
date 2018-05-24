package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.kitchen.Execution;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.ExecutionRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepositoryWithLongPK;

/**
 *
 * @author Carlos Figueiredo (1140317)
 */
public class InMemoryExecutionRepository extends InMemoryRepositoryWithLongPK<Execution> implements ExecutionRepository {


    @Override
    public int getMealActuallyServed(Meal meal) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterable<Execution> getExecutionByMeal(Meal meal) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getPickedMeals(Meal meal) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
