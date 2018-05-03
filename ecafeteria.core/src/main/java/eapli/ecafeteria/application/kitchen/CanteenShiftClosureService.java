package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.kitchen.Execution;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.ExecutionRepository;
import eapli.ecafeteria.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CanteenShiftClosureService {

    private CheckBookingsService service = new CheckBookingsService();
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

    public List<Booking> getBookingListByMeal(Meal meal){
        List<Booking> bookingList = new ArrayList<>();

        for (Booking booking:
             service.getAllReservations(2)) {
            if(booking.getMeal().equals(meal)){
                bookingList.add(booking);
            }
        }

        return bookingList;
    }

    public void registerMealsDoneButNotSold(Meal meal){
        Execution execution = getExecutionByMeal(meal);
        List<Booking> bookingList = getBookingListByMeal(meal);
        int mealDoneButNotSold = execution.getCookedMeals()-bookingList.size();
        //register in database
    }

}
