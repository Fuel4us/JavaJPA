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
    CheckBookingsService service;


    public CheckBookingsByDataController(){
        service= new CheckBookingsService();
    }


    public List<Booking> getAllBookings(){
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);
        return service.getAllReservations();
    }


    public List<Date> displayBookingsDate() {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);
        return service.displayBookingsDate();

    }

    public List<Dish> displayBookingsByDish(){
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);
        return service.displayBookingsByDish();
    }

    public List<Meal> displayBookingsByMeal(){
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);
        return service.displayBookingsByMeal();
    }

    public List<MealType> displayBookingsByMealType(){
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);
        return service.displayBookingsByMealType();
    }
}
