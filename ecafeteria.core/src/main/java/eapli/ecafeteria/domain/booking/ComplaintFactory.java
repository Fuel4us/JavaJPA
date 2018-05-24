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

    /**
     * Create a Complaint and stores in repository. If the booking has already a complaint
     * the old one is deleted from the ComplaintRepository. Booking complaint is updated in
     * BookingRepository
     *
     * @param booking Booking
     * @param cafeteriaUser CafeteriaUser
     * @param description Description
     * @param state ComplaintState
     * @return
     */
    
    public void createComplaint(Booking booking, CafeteriaUser cafeteriaUser, Description description, ComplaintState state) throws DataConcurrencyException, DataIntegrityViolationException {
        Complaint complaint = new Complaint(booking.getMeal(), cafeteriaUser, description, state);
        Complaint aux = booking.Complaint();
        
        complaint = complaintRepository.save(complaint);
        
        bookingRepository.updateBookingComplaint(booking, complaint);
        
        if(aux != null){
            complaintRepository.delete(aux);
        }
    }
}
