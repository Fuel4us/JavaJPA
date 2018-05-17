package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.domain.booking.Comment;
import eapli.ecafeteria.persistence.CommentRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommentBootstrapper implements Action{

    
    
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
        
        
        //comment made by a user
        final Comment c1 = new Comment("comment1");
        final Comment c2 = new Comment("comment2");
        
        //answer or reply made by the chef (menu manager)
        c1.changeAnswer("answer1");
        c2.changeAnswer("answer2");
        
        cRepository.save(c1);
        cRepository.save(c2);
    }
}
