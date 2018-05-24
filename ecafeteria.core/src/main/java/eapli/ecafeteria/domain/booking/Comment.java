package eapli.ecafeteria.domain.booking;

import eapli.framework.domain.ddd.ValueObject;
import eapli.framework.util.Strings;
import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author Hilario Coelho
 * changed by João Pereira <1150478@isep.ipp.pt>
 * changed by Pedro Vieira 1160634
 */
@Embeddable
public class Comment implements Serializable, ValueObject {

    /*
    * The default answer, before the menu manager reply
    */
    private static final String INITIAL_ANSWER = "there is no answer yet!";
    private static final long serialVersionUID = 1L;
    
    /**
     * Instance variable that defines the comment made by the user.
     */
    private String comment;
    
    /**
     * Instance variable that defines the answer made by the menu manager.
     */
    private String answer;

    /**
     * Empty constructor of the class for the ORM.
     */
    public Comment() {
    }

    /**
     * Complete constructor of the class with the String comment.
     * @param comment
     */
    public Comment(String comment) {
        if(Strings.isNullOrEmpty(comment))
            throw new IllegalArgumentException("Comment should neither be null nor empty");
        this.comment = comment;
        this.answer = INITIAL_ANSWER;
    }
    
    /**
     * Returns the comment.
     * Created by João Pereira <1150478@isep.ipp.pt>
     * @return 
     */
    public String getRealComment() {
        return comment;
    }
    
    /**
     * Returns the answer of the comment.
     * @return 
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Changes or Set answer.
     * @param answer
     */
    public void changeAnswer(String answer) {
        if(Strings.isNullOrEmpty(answer))
            throw new IllegalArgumentException("Answer should neither be null nor empty");
        this.answer = answer;
    }

     /**
     * Returns a brief description of the comment.
     * @return
     */
    @Override
    public String toString() {
        return "Comment{" + ", comment=" + comment + ", answer=" + answer + '}';
    }

}
