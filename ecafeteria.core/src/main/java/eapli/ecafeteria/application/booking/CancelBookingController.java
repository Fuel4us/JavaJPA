/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.domain.movement.Movement;
import eapli.ecafeteria.domain.movement.MovementType;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.MovementRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Calendar;
import java.util.Currency;
import java.util.Locale;
import java.util.Optional;

/**
 *
 * @author Hilario Coelho
 */
public class CancelBookingController implements Controller {
    private final CafeteriaUserRepository repCafeteriaUser = PersistenceContext.repositories().cafeteriaUsers();
    private final MovementRepository repMovements = PersistenceContext.repositories().movement();
    private final BookingRepository repBookings = PersistenceContext.repositories().booking();
    
    /**
     * Cancels a booking in Reserved State refunding the user
     * @param booking Booking
     * @return Success
     * @throws DataConcurrencyException
     * @throws DataIntegrityViolationException 
     */
    public boolean cancelBooking(Booking booking) throws DataConcurrencyException, DataIntegrityViolationException {        
        booking.changeState(BookingState.CANCELED);
        Optional<CafeteriaUser> user = getUser();
        Currency currency = Currency.getInstance(Locale.FRANCE);
        
        Movement movement = new Movement(user.get().mecanographicNumber(), MovementType.CANCEL, amountToRefund(booking), currency);
        
        repMovements.save(movement);
        repBookings.save(booking);
        
        return true;
    }
    
    /**
     * Gets a booking by it's ID from a list
     * @param bookingId Booking ID
     * @param bookings List of Bookings
     * @return Booking
     */
    public Booking getBooking(String bookingId, Iterable<Booking> bookings) {
        Booking booking = null;
        for(Booking b : bookings) {
            if(b.bookingId().equals(bookingId)) {
                booking = b;
                break;
            }
        }
        
        return booking;
    }
    
    /**
     * Returns all bookings from an User in Reserved State
     * @param user User
     * @return Bookings
     */
    public Iterable<Booking> findBookings(Optional<CafeteriaUser> user) {
        return repBookings.getBookings(user);
    }
    
    /**
     * Gets the authenticated user
     * @return User
     */
    public Optional<CafeteriaUser> getUser() {
        SystemUser su = AuthorizationService.session().authenticatedUser();
        return repCafeteriaUser.findByUsername(su.username());
    }
    
    /**
     * Returns the amount to refund considering the time of the day
     * @param b Booking
     * @return Value
     */
    public double amountToRefund(Booking b) {
        Calendar date = Calendar.getInstance();
        
        double amount = b.getMeal().getDish().currentPrice().amount();
        
        if(b.getMeal().getMealDate().before(date.getTime())) {
            MealType mt = b.getMeal().getMealType();
            if(mt == MealType.LUNCH) {
                if(date.get(Calendar.HOUR_OF_DAY) >= 10)
                    return amount / 2;
            } else if(mt == MealType.DINNER) {
                if(date.get(Calendar.HOUR_OF_DAY) >= 16)
                    return amount / 2;
            }
        }
        
        return amount;
    }
}
