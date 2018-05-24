package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.kitchen.Execution;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.ExecutionRepository;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

import java.util.*;

public class CanteenShiftClosureService {


    final ExecutionRepository executionRepository= PersistenceContext.repositories().execution();
    final MealRepository mealRepository=PersistenceContext.repositories().meals();


    public CanteenShiftClosureService(){

    }

    public void saveNotPickedQuantity() {

        Date date = new java.sql.Date(Calendar.getInstance().getTimeInMillis());


        for (Meal meal :
                mealRepository.findMealByDate(date)) {
            Execution execution;
            Iterable<Execution> it = executionRepository.getExecutionByMeal(meal);
            if (!it.iterator().hasNext()) {
                System.out.println("Execution of the meal not found");
            } else {
                int mealsPicked = executionRepository.getPickedMeals(meal);
                int mealsActuallyDone = executionRepository.getMealActuallyServed(meal);

                execution = it.iterator().next();
                execution.getNotPicked().setNotPickedQuantity(mealsActuallyDone-mealsPicked);
                try {
                    executionRepository.save(execution);
                } catch (DataConcurrencyException e) {
                    e.printStackTrace();
                } catch (DataIntegrityViolationException e) {
                    e.printStackTrace();
                }
            }
        }
    }



}
