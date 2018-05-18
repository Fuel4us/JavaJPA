package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.Comment;
import eapli.ecafeteria.domain.booking.Rating;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.CommentRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.RatingRepository;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        
        final CommentRepository cRepository = PersistenceContext.repositories().comments();
        final RatingRepository rRepository = PersistenceContext.repositories().rating();
        final BookingRepository bRepository = PersistenceContext.repositories().booking();
        
        Iterator<Booking> bR = bRepository.findAll().iterator();
        Booking b1 = bR.next();
        //System.out.println(b1);
        Booking b2 = bR.next();
        //System.out.println(b2);
        
        Iterator<Comment> iR = cRepository.findAll().iterator();
        Comment c1 = iR.next();
        Comment c2 = iR.next();
        
        final Rating r1 = new Rating(2, c1);
        final Rating r2 = new Rating(4, c2);
        
        b1.rating(r1);
        b2.rating(r2);
        
        rRepository.save(r1);
        rRepository.save(r2);
        
        //System.out.println(b1.getRating());
        //System.out.println(b2.getRating());
        
        //bRepository.save(b1);
        //bRepository.save(b2);
    }
    
}
