/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.application.administration.KitchenLimitsServices;
import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.application.meals.MealPlanServices;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.meals.Meal;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Pedro Rodrigues (1140572)
 */
public class BookingWatchDog extends Observable implements Observer {

    private BookingServices bookingServices = new BookingServices();
    private KitchenLimitsServices kitchenLimitsServices = new KitchenLimitsServices();
    private MealPlanServices mealPlanServices = new MealPlanServices();

    private Booking booking;
    private int numberOfMeal = 0;
    private int numberOfBookingByMeal = 0;
    private double yellowLimit = 0;
    private double redLimit = 0;

    public void getNumberOfBookingByMeal(Meal meal) {
        for (Booking booking1 : bookingServices.findByMeal(meal)) {
            if (booking1.isReserved()) {
                numberOfBookingByMeal++;
            }
        }
    }

    public void getNumberOfMeal(Meal meal) {
        numberOfMeal = mealPlanServices.findQuantityByMeal(meal);
    }

    public void getYellowLimit() {
        yellowLimit = kitchenLimitsServices.findYellowLimit();
    }

    public void getRedLimit() {
        redLimit = kitchenLimitsServices.findRedLimit();
    }

    public void display() {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN, ActionRight.MANAGE_MENUS);
        
        if ((numberOfBookingByMeal / numberOfMeal) * 100 > yellowLimit && (numberOfBookingByMeal / numberOfMeal) * 100 < redLimit) {
            notifyObservers();
            System.out.println("YELLOW ALERT" + yellowLimit);
        } else if ((numberOfBookingByMeal / numberOfMeal) * 100 > redLimit) {
            notifyObservers();
            System.out.println("RED ALERT" + redLimit);
        }
    }

    @Override
    public void update(Observable bookingWatchDog, Object booking) {
        this.booking = (Booking) booking;
        display();
    }
}
