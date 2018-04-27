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
        return (Iterable< MealType>) MealType.MealTypeValues().iterator();
    }

    public Iterable<DishType> listDishTypes() {
        return dishTypeRepo.findAll();
    }

    public Map<DishType, Integer> listAvailableMeals(MealType mealType, Iterable<DishType> listDishType) {

        MealRepository meal = PersistenceContext.repositories().meals();

        BookingRepository bookingRepo = PersistenceContext.repositories().booking();

        SystemUser su = AuthorizationService.session().authenticatedUser();

        Optional<CafeteriaUser> optUser = userRepo.findByUsername(su.username());

        Iterator<DishType> listdishtype = listDishType.iterator();

        Map<DishType, Integer> map = new HashMap<>();

        while (listdishtype.hasNext()) {
            DishType dishType = listdishtype.next();
            BookingState bookingState = BookingState.RESERVED;
            Iterable<Meal> listMeals = meal.findAllByLot(Long.MIN_VALUE);

            Iterator<Meal> listMealIterator = listMeals.iterator();
            int count = 0;
            while (listMealIterator.hasNext()) {
                count += listMealIterator.next().hashCode();
            }

            Iterable<Booking> listBookingsIterable = bookingRepo.findBookingByUserAndDate(optUser, mealType, bookingState);
            Iterator<Booking> listBookingsIterator = listBookingsIterable.iterator();
            int count2 = 0;
            while (listBookingsIterator.hasNext()) {
                listBookingsIterator.next();
                count2++;
            }

            map.put(dishType, (count - count2));
        }
        return map;
    }
}
