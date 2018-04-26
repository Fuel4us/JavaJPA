/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Arrays;
import java.util.Optional;

/**
 *
 * @author Ana Mafalda Silva
 */
public class DeliverBookingController {
    
    private final BookingRepository repositoryBooking = PersistenceContext.repositories().booking();
    private final CafeteriaUserRepository repositoryUser = PersistenceContext.repositories().cafeteriaUsers();
    
    public Optional<CafeteriaUser> findUserByNumber(MecanographicNumber numberUser) throws DataConcurrencyException {
        return repositoryUser.findByMecanographicNumber(numberUser);
    }
    
    public Iterable<MealType> listMealTypes() {
        return Arrays.asList(MealType.MealTypeValues());
    }
    
    public Iterable<Booking> findBookingByUserAndDate(Optional <CafeteriaUser> user, MealType mealType, BookingState rs) {
        return repositoryBooking.findBookingByUserAndDate(user, mealType, rs);
    }
    
    public boolean deliverBookingState(Booking booking) throws DataConcurrencyException, DataIntegrityViolationException {
        if(!booking.isReserved()){
            throw new IllegalArgumentException();
        }
        booking.changeState(BookingState.DELIVERED);
        repositoryBooking.save(booking);
        return true;
    }
       
}
