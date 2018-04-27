/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.domain.Designation;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Date;
import java.util.Optional;

/**
 *
 * @author Leandro
 */
public class RegisterBookingController {
    
    private final BookingRepository repBooking = PersistenceContext.repositories().booking();
    private final MealRepository repMeal = PersistenceContext.repositories().meals();
    private final CafeteriaUserRepository repCafeteriaUser = PersistenceContext.repositories().cafeteriaUsers();
    
    private final static long MILLIS_PER_DAY = 24 * 60 * 60 * 1000L;
    
    public Meal findMealByID(long id){
        //Needs revision
        Optional<Meal> OpMeal = repMeal.findOne(id);
        if(OpMeal.isPresent())
            return OpMeal.get();
        else{
            return null;
        }
    }
    
    public CafeteriaUser findCafeteriaUser(Username username){
        Optional<CafeteriaUser> OpCU = repCafeteriaUser.findByUsername(username);
        if(OpCU.isPresent())
            return OpCU.get();
        else{
            return null;
        }
    }
    
    public boolean registerBooking(CafeteriaUser cu, Meal meal) throws DataConcurrencyException, DataIntegrityViolationException{
        //Check if the meal is reserved 24 hours before the actual meal
        Date CurrentDate = new Date();
        Date mealDate = meal.getMealDate();
        
        boolean moreThanDay = Math.abs(mealDate.getTime() - CurrentDate.getTime()) > MILLIS_PER_DAY;

        if(moreThanDay == true){
            //More then 24 hours
            Booking booking = new Booking(cu, meal);
            
            //Transactions
            //It should check if the user has enough money to do this operation
            
            repBooking.save(booking);
            
            return true;
        }else{
            return false;
        }
        
    }
}
