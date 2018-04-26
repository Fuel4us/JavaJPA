/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.PersistenceContext;

/**
 *
 * @author Tiago Correia
 */
public class CheckNextBookingController {
    
    private final BookingRepository bookingRepository = PersistenceContext.repositories().booking();
    private final CafeteriaUserRepository userRepository = PersistenceContext.repositories().cafeteriaUsers();

}
