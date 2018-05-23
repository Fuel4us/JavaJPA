/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.PersistenceContext;

/**
 *
 * @author Pedro Rodrigues (1140572)
 */
public class BookingServices {

    private final BookingRepository bookingRepo = PersistenceContext.repositories().booking();
    
    public BookingServices() {

    }

    public Iterable<Booking> findByMeal(Meal meal) {
        return bookingRepo.findBookingsForMeal(meal);
    }

}
