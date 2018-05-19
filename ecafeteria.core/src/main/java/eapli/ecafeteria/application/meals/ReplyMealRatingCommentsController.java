/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.meals;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Bernardo Carreira 1150990
 */
public class ReplyMealRatingCommentsController {
    
    private final BookingRepository bookingRepository = PersistenceContext.repositories().booking();

    public Iterable<Booking> getBookingsDelivered() {
        return bookingRepository.findBookingsDelivered();
    }

    public Set<Meal> getMealsOfBookings() {
        Set<Meal> mealList = new HashSet<>();
        Iterable<Booking> bookingList = getBookingsDelivered();

        for (Booking book : bookingList) {
            mealList.add(book.getMeal());
        }

        return mealList;
    }

    public Iterable<Booking> getBookingsOfMeal(Meal meal) {
        return bookingRepository.findBookingsForMeal(meal);
    }

    public void saveBooking(Booking b) throws DataConcurrencyException, DataIntegrityViolationException{
        bookingRepository.save(b);
    }

}
