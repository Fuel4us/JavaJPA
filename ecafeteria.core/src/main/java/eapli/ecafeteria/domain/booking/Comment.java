package eapli.ecafeteria.domain.booking;

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
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;
    
    // ORM primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String comment;
    private String answer;

    // for ORM
    public Comment() {
    }

    public Comment(String comment) {
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
     * 
     * @param answer
     */
    public void changeAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Comment{" + "comment=" + comment + ", resposta=" + answer + '}';
    }
}
