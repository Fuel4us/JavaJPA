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
import eapli.ecafeteria.persistence.PersistenceContext;
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
    private final CafeteriaUserRepository repCafeteriaUser = PersistenceContext.repositories().cafeteriaUsers();
    
    private final static long MILLIS_PER_DAY = 24 * 60 * 60 * 1000L;
    
    public CafeteriaUser findCafeteriaUser(Username username){
        Optional<CafeteriaUser> OpCU = repCafeteriaUser.findByUsername(username);
        if(OpCU.isPresent())
            return OpCU.get();
        else{
            return null;
        }
    }
    
    public void registerBooking(CafeteriaUser cu, Meal meal) throws DataConcurrencyException, DataIntegrityViolationException{
        //Check if the meal is reserved 24 hours before the actual meal
        Date CurrentDate = new Date();
        Date mealDate = meal.getDate();
        
        boolean moreThanDay = Math.abs(mealDate.getTime() - CurrentDate.getTime()) > MILLIS_PER_DAY;

        if(moreThanDay == true){
            //More then 24 hours
            Booking booking = new Booking(cu, meal);
            
            repBooking.save(booking);
        }else{
            //Meal is served in less then 24 hours
            //Cant be booked
        }
        
    }
}
