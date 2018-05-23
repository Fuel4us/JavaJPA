package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.Comment;
import eapli.ecafeteria.domain.booking.Rating;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * changed by João Pereira <1150478@isep.ipp.pt>
 */
public class RatingBootstrapper implements Action{

    @Override
    public boolean execute() {
        try {
            register();
        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            Logger.getLogger(AllergenBootstrapper.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    public void register() throws DataIntegrityViolationException, DataConcurrencyException {
        
        final BookingRepository bRepository = PersistenceContext.repositories().booking();
        
        Iterator<Booking> bR = bRepository.findBookingsDelivered().iterator();
        Booking b1 = bR.next();
        Booking b2 = bR.next();
        
        //comment made by a user
        final Comment c1 = new Comment("Very spicy");
        final Comment c2 = new Comment("Nice food");
        //final Comment c3 = new Comment("The best food");
        
        //answer or reply made by the chef (menu manager)
        c1.changeAnswer("Sorry, it was our mistake");
        
        final Rating r1 = new Rating(2, c1);
        final Rating r2 = new Rating(4, c2);
        //final Rating r3 = new Rating(5, c3);

        b1.changeRating(r1);
        b2.changeRating(r2);
        
        bRepository.save(b1);
        bRepository.save(b2);
    }
    
}
