/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.domain.movement.BalanceService;
import eapli.ecafeteria.domain.movement.Movement;
import eapli.ecafeteria.domain.movement.MovementType;
import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.MovementRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Date;
import java.util.Optional;
import eapli.framework.util.DateTime;
import java.util.Calendar;
import java.util.Currency;
import java.util.Locale;

/**
 *
 * @author Leandro
 */
public class RegisterBookingController {
    
    private final BookingRepository repBooking = PersistenceContext.repositories().booking();
    private final MealRepository repMeal = PersistenceContext.repositories().meals();
    private final CafeteriaUserRepository repCafeteriaUser = PersistenceContext.repositories().cafeteriaUsers();
    private final MovementRepository repMovements = PersistenceContext.repositories().movement();
    
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
        
        final MecanographicNumber mn = new MecanographicNumber(cu.mecanographicNumber().number());
        final double amount = meal.getDish().currentPrice().amount();
        
        boolean moreThanDay = Math.abs(mealDate.getTime() - CurrentDate.getTime()) > MILLIS_PER_DAY;
        
        if(BalanceService.balance(mn) >= amount){
        
            Currency currency = Currency.getInstance(Locale.FRANCE);
            Movement movement = new Movement(mn, MovementType.BOOKING, amount, currency);

            repMovements.save(movement);

            Calendar mealDateCalendar = DateTime.dateToCalendar(mealDate);
            Calendar currentDateCalendar = DateTime.dateToCalendar(CurrentDate);

            if(moreThanDay == true && DateTime.isBefore(currentDateCalendar, mealDateCalendar)){
                //More then 24 hours
                Booking booking = new Booking(cu, meal);

                repBooking.save(booking);

                return true;
            }else{
                return false;
            }
        }
        
        return false;
    }
}
