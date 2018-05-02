package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.framework.application.Controller;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Diogo Monteiro
 */
public class CheckBookingsByDataController implements Controller {
    List<Booking> bookingList;
    CheckBookingsService service;


    public CheckBookingsByDataController(){
        service= new CheckBookingsService();
    }

    public void setBookingList(List<Booking> list){
        this.bookingList=list;
    }

    public List<Booking> getAllBookings(int state){
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);
        return service.getAllReservations(state);
    }


    public List<Date> displayBookingsDate() {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);
        return service.displayBookingsDate(bookingList);

    }

    public List<Dish> displayBookingsByDish(){
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);
        return service.displayBookingsByDish(bookingList);
    }

    public List<Meal> displayBookingsByMeal(){
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);
        return service.displayBookingsByMeal(bookingList);
    }

    public List<MealType> displayBookingsByMealType(){
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);
        return service.displayBookingsByMealType(bookingList);
    }
}
