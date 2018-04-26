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
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
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

    public JpaBookingRepository() {
    }

    @Override
    public Iterable<Booking> checkBookingsForNextDays(CafeteriaUser currentUser, Date currentDate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        return (Iterable<Booking>) match("e.user =:user AND e.meal.mealType =:mealType AND e.reservationState =:reservationState AND e.meal.day = '" + new java.sql.Date(Calendar.getInstance().getTimeInMillis()) + "'", params);
    }
    
}
