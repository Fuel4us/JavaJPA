/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.framework.domain.Designation;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import static jdk.nashorn.internal.objects.NativeString.match;
    
/**
 *
 * @author Ana Mafalda Silva
 */
class JpaBookingRepository extends CafeteriaJpaRepositoryBase<Booking, Long> implements BookingRepository {

    private final int NEXT_DAYS = 7, ONE_MORE_DAY = 1 * 24 * 60 * 60 * 1000;
    
    public JpaBookingRepository() {
    }

    @Override
    public Iterable<Booking> checkBookingsForNextDays(Optional<CafeteriaUser> user, Date date) {
        
        Map<String, Object> params = new HashMap();
        params.put("user", user);
        params.put("bookingState", BookingState.RESERVED);
        
        List<Booking> bookingList = new ArrayList();
        
        long currentDate = date.getTime();
        
        for(int i = 0; i < NEXT_DAYS; i++){
            bookingList.addAll(match("E.MEAL.MEALDATE = '" + new Date(date.getTime()) + "' and E.CAFETERIAUSER = :user and E.BOOKING.BOOKINGSTATE = :bookingState", params));
            
            //+1 to that day
            date.setTime((currentDate) + ONE_MORE_DAY);
            currentDate = date.getTime();
        }
        
        return bookingList;
    }

    @Override
    public Booking getNextBooking() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterable<Booking> findBookingByUserAndDate(Optional<CafeteriaUser> user, MealType mealType, BookingState bookingState) {
        Map<String, Object> params = new HashMap<>();
        params.put("user", user);
        params.put("mealType", mealType);
        params.put("bookingState", bookingState);
        return (Iterable<Booking>) match("e.user =:user AND e.meal.mealType =:mealType AND e.bookingState =:bookingState AND e.meal.day = '" + new java.sql.Date(Calendar.getInstance().getTimeInMillis()) + "'", params);
    }
    
}
