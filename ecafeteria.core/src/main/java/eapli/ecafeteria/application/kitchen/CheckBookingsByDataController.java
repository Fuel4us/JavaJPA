package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.framework.application.Controller;
import eapli.framework.util.Console;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Diogo Monteiro
 */
public class CheckBookingsByDataController implements Controller {
    CheckBookingsService service;

    public CheckBookingsByDataController(){
        service= new CheckBookingsService();
    }

    public void run(int choice){
        if(choice==1){
            service.displayBookingsDate();
        }else if(choice ==2){
            service.displayBookingsByMealType();
        }else if(choice ==3){
            service.displayBookingsByDish();
        }else if(choice ==4){
            service.displayBookingsByMeal();
        }
    }

    public List<Booking> getAllBookings(){
    return service.getAllReservations();
    }


    public List<Date> displayBookingsDate() {
        return service.displayBookingsDate();

    }

    public List<Dish> displayBookingsByDish(){
        return service.displayBookingsByDish();
    }

    public List<Meal> displayBookingsByMeal(){
        return service.displayBookingsByMeal();
    }

    public List<MealType> displayBookingsByMealType(){
        return service.displayBookingsByMealType();
    }
}
