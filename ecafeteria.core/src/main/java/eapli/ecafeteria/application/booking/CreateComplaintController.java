/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.ComplaintFactory;
import eapli.ecafeteria.domain.booking.Description;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.ComplaintRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hernani Gil
 */
public class CreateComplaintController implements Controller {
    private CafeteriaUser selectedUser;
    private ComplaintFactory complaintFactory;
    
    
    private final BookingRepository bookingRepository = PersistenceContext.repositories().booking();
    private final CafeteriaUserRepository cafeteriaUserRepository = PersistenceContext.repositories().cafeteriaUsers();
    


    public boolean selectUser(Username username){ //boolean
        Optional<CafeteriaUser> OpCU = cafeteriaUserRepository.findByUsername(username);
        
        
        //se o existe e se o seu estado de SystemUser está ativo
        if(OpCU.isPresent() && OpCU.get().user().isActive()){
            selectedUser = OpCU.get();
            return true;
        }else{
            System.out.println("No user selected");
            return false;
        }
    }
    
    private Iterable<Booking> getBookingsDeliveredFromUser(CafeteriaUser user) {
        
        return bookingRepository.findBookingsDeliveredByUser(user);
    }
    
    private void createComplaint(Booking booking, String text){
        Description description = new Description(text);
        
        complaintFactory = ComplaintFactory.getInstance();
        
        try {
            complaintFactory.createComplaint(booking, selectedUser, description);
        } catch (DataConcurrencyException ex) {
            Logger.getLogger(CreateComplaintController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DataIntegrityViolationException ex) {
            Logger.getLogger(CreateComplaintController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
