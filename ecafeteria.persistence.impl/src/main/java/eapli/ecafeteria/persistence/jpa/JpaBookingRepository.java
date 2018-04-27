/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.booking.Rating;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.persistence.BookingRepository;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.persistence.Query;
    
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

    
    public Booking getNextBooking(Optional <CafeteriaUser> user, Date date) {
        Iterable<Booking> nextBookings = checkBookingsForNextDays(user, date);
        BirthDateComparator comparator = new BirthDateComparator();
        List<Booking> bookingList = new ArrayList();
        bookingList= (List<Booking>) nextBookings;
        bookingList.sort(comparator);
        return bookingList.get(0);
    }
    
    public class BirthDateComparator implements Comparator<Booking> {
        @Override
        public int compare(Booking booking1, Booking booking2) {
            if (booking1.getMeal().getDate().before(booking2.getMeal().getDate())) {
                return -1;
            } else if (booking1.getMeal().getDate().after(booking2.getMeal().getDate())) {
                return 1;
            } else {
                return 0;
            }        
        }
    }

    @Override
    public Iterable<Booking> findBookingByUserAndDate(Optional<CafeteriaUser> user, MealType mealType, BookingState bookingState) {
        Map<String, Object> params = new HashMap<>();
        params.put("user", user);
        params.put("mealType", mealType);
        params.put("bookingState", bookingState);
        return (Iterable<Booking>) match("e.user =:user AND e.meal.mealType =:mealType AND e.bookingState =:bookingState AND e.meal.day = '" + new java.sql.Date(Calendar.getInstance().getTimeInMillis()) + "'", params);
    }

    /**
     * Rúben - 1160998
     */
    @Override
    public Iterable<Booking> findBookingsDeliveredByUser(CafeteriaUser user) {
        Map<String, Object> params = new HashMap<>();
        params.put("user", user);
        params.put("bookingState", BookingState.RESERVED);
        return (Iterable<Booking>) match("e.user = :user AND e.bookingState = :bookingState", params);
    }

    /**
     * Rúben - 1160998
     */
    @Override
    public void updateBookingRating(Booking choosen, Rating rating) {
        Query query = entityManager().createQuery("UPDATE Booking SET RATING_ID = " + rating.id() + " WHERE BOOKINGID = " + choosen.bookingId());
        query.executeUpdate();
    }

    
    
    
}