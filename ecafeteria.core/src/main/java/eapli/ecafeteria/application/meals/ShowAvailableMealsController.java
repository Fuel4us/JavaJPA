/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.meals;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.dishes.DishType;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.persistence.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author João Pires <your.name at your.org>
 */
public class ShowAvailableMealsController {

    private final DishTypeRepository dishTypeRepo = PersistenceContext.repositories().dishTypes();

    private final MealRepository mealRepo = PersistenceContext.repositories().meals();

    private final CafeteriaUserRepository userRepo = PersistenceContext.repositories().cafeteriaUsers();

    public Iterable<MealType> listMealTypes() {
        return (Iterable< MealType>) MealType.MealTypeValues();
    }

    public Iterable<DishType> listDishTypes() {
        return dishTypeRepo.findAll();
    }

    public Map<DishType, Integer> listAvailableMeals(MealType mealType, Iterable<DishType> listDishType) {

        MealRepository meal = PersistenceContext.repositories().meals();

        BookingRepository bookingRepo = PersistenceContext.repositories().booking();

        Iterator<DishType> listdishtype = listDishType.iterator();

        Map<DishType, Integer> map = new HashMap<>();

        while (listdishtype.hasNext()) {
            
            DishType dishType = listdishtype.next();
            BookingState bookingState = BookingState.RESERVED;
            List<Meal> listMeals = (List<Meal>) meal.findAllByMealType(mealType, dishType);
          
            int numMeals = listMeals.size();
            
            Iterable<Booking> listBookingsIterable = bookingRepo.findBookingByDate(mealType, dishType, bookingState);
            Iterator<Booking> listBookingsIterator = listBookingsIterable.iterator();
            
            int numBookings = 0;
            
            while (listBookingsIterator.hasNext()) {
                listBookingsIterator.next();
                numBookings++;
            }

            map.put(dishType, (numMeals - numBookings));
        }
        return map;
    }
}
