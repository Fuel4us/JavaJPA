/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.meals;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Berccar
 */
public class CheckMealRatingController {
    
    private final MealRepository mealRepository = PersistenceContext.repositories().meals();
    private final BookingRepository bookingRepository = PersistenceContext.repositories().booking();
    
    /**
     * Returns all Meals in Delivered State from an User
     * @param user User
     * @return Bookings
     */
    private Iterable<Meal> getServedMeals() {
        Iterable<Meal> meals = mealRepository.findAll();
        List<Meal> servedMeals = new ArrayList<>();
        for(Meal m : meals){
            if (m.getMealDate().before(Date.from(Instant.now()))){
                servedMeals.add(m);
            }
        }
        return servedMeals;
    }
    
}
