package eapli.ecafeteria.domain.booking;

import eapli.framework.domain.ddd.ValueObject;
import eapli.framework.util.Strings;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Hilario Coelho
 * changed by João Pereira <1150478@isep.ipp.pt>
 */
@Entity
public class Comment implements Serializable, ValueObject {

    private static final long serialVersionUID = 1L;
    
    /**
     * Variable that defines the id of the comment.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
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
        return "Comment{" + "comment=" + comment + ", resposta=" + answer + '}';
    }
}
