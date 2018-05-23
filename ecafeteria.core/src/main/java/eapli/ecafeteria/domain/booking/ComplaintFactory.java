/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.booking;

import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.ComplaintRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.List;

/**
 *
 * @author Hernani Gil
 */
public class ComplaintFactory { //Singleton

    private static ComplaintFactory instance;
    private final BookingRepository bookingRepository = PersistenceContext.repositories().booking();
    private final ComplaintRepository complaintRepository = PersistenceContext.repositories().complaint();

    private ComplaintFactory() {

    }

    static {
        try {
            instance = new ComplaintFactory();
        } catch (Exception e) {
            throw new RuntimeException("Exception occured in creating singleton instance Complaint Factory");
        }
    }

    public static ComplaintFactory getInstance() {
        return instance;
    }

    public Iterable<Booking> findBookingWithDeliveredStateNoComplaints(CafeteriaUser selectedUser) {

        Iterable<Booking> bookings = bookingRepository.findBookingsDeliveredByUser(selectedUser);

        return bookings;
    }

    public void createComplaint(Booking booking, CafeteriaUser cafeteriaUser, Description description) throws DataConcurrencyException, DataIntegrityViolationException {
        Complaint complaint = new Complaint(booking.getMeal(), cafeteriaUser, description);//meal clonado? meal do Complaint?

        complaint = complaintRepository.save(complaint);
        
        bookingRepository.updateBookingComplaint(booking, complaint);
    }
}
