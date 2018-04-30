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

    /**
     * Returns the list of bookings in reserved state for the next X days
     * @param user User
     * @param date Starting date to compare
     * @return List of bookings
     */
    @Override
    public Iterable<Booking> checkBookingsForNextDays(Optional<CafeteriaUser> user, Date date) {
        
        Map<String, Object> params = new HashMap();
        params.put("user", user.get());
        params.put("bookingState", BookingState.RESERVED);
        
        List<Booking> bookingList = new ArrayList();
        
        for(int i = 0; i < NEXT_DAYS; i++){
            params.put("date", new Date(date.getTime()));
            bookingList.addAll(match("e.meal.mealDate = :date and e.user = :user and e.bookingState = :bookingState", params));
            
            //+1 to that day
            date.setTime(date.getTime() + ONE_MORE_DAY);
        }
        
        return bookingList;
    }

    /**
     * Returns the next booking from an User
     * @param user User
     * @param date Date to compare
     * @return 
     */
    @Override
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
            if (booking1.getMeal().getMealDate().before(booking2.getMeal().getMealDate())) {
                return -1;
            } else if (booking1.getMeal().getMealDate().after(booking2.getMeal().getMealDate())) {
                return 1;
            } else {
                return 0;
            }        
        }
    }

    /**
     * Finds and returns a list of bookings by their user, meal type and state
     * @param user User
     * @param mealType Meal Type
     * @param bookingState State
     * @return Bookins
     */
    @Override
    public Iterable<Booking> findBookingByUserAndDate(Optional <CafeteriaUser> user, MealType mealType, BookingState bookingState) {
        Map<String, Object> params = new HashMap<>();
        params.put("user", user.get());
        params.put("mealType", mealType);
        params.put("bookingState", bookingState);
        Date mealDate = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
        params.put("mealDate", mealDate);
        return (Iterable<Booking>) match("e.user =:user AND e.meal.mealType =:mealType AND e.bookingState =:bookingState AND e.meal.mealDate = :mealDate", params);
    }

    /**
     * Returns all bookings in delivered state from an User
     * @param user User
     * @return List of bookings
     */
    @Override
    public Iterable<Booking> findBookingsDeliveredByUser(CafeteriaUser user) {
        Map<String, Object> params = new HashMap<>();
        params.put("user", user);
        params.put("bookingState", BookingState.DELIVERED);
        return (Iterable<Booking>) match("e.user = :user AND e.bookingState = :bookingState", params);
    }

    /**
     * Updates a booking to set a Rating
     * @param choosen Booking
     * @param rating Rating
     */
    @Override
    public void updateBookingRating(Booking choosen, Rating rating) {
        entityManager().getTransaction().begin();
        
        Query query = entityManager().createQuery("UPDATE Booking SET RATING_ID=:ratingid WHERE BOOKINGID=:bookingid");
        query.setParameter("ratingid", rating.id());
        query.setParameter("bookingid", choosen.bookingId());
        query.executeUpdate();
        
        entityManager().getTransaction().commit();
    }
}