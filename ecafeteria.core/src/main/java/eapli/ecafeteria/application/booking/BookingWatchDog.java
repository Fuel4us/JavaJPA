/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.application.administration.KitchenLimitsServices;
import eapli.ecafeteria.application.meals.MealServices;
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
    private MealServices mealServices = new MealServices();
    
    private int numberOfMeal = 0;
    private int numberOfBookingByMeal = 0;
    private double yellowLimit;
    private double redLimit;

    public void getYellowLimit() {
        yellowLimit = kitchenLimitsServices.findYellowLimit();
    }

    public void getRedLimit() {
        redLimit = kitchenLimitsServices.findRedLimit();
    }

    public void getNumberOfMeal(Meal meal) {
        numberOfMeal = 0;
    }

    public void getNumberOfBookingByMeal(Meal meal) {
        for (Booking booking : bookingServices.findByMeal(meal)) {
            numberOfBookingByMeal++;
        }
    }

    @Override
    public void update(Observable o, Object o1) {
    }
}
