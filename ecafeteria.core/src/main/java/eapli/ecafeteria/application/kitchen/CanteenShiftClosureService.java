package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.domain.kitchen.Execution;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.ExecutionRepository;
import eapli.ecafeteria.persistence.PersistenceContext;

import java.util.Iterator;

public class CanteenShiftClosureService {

    final ExecutionRepository executionRepository= PersistenceContext.repositories().execution();
    final BookingRepository bookingRepository = PersistenceContext.repositories().booking();

    public CanteenShiftClosureService(){

    }

    public Execution getExecutionByMeal(Meal meal){
        Iterable<Execution> it = executionRepository.findAll();
        if(it.iterator().hasNext()) {
            for (Execution execution :
                    it) {
                if (execution.getMeal().equals(meal)) {
                    return execution;
                }
            }
        }else{
            System.out.println("There are no executions in the DataBase");
        }
        return null;
    }


}
