package eapli.ecafeteria.domain.booking;

import eapli.framework.domain.ddd.ValueObject;
import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author Pedro Vieira 1160634
 */
@Embeddable
public class Rate implements Serializable, ValueObject{
    
    private static final int MIN_VAL = 1;
    private static final int MAX_VAL = 5;
    private static final long serialVersionUID = 1L;
    
    
    /**
     * Instance variable that defines the comment made by the user.
     */
    private int score;
    
    /**
     * Empty constructor of the class for the ORM.
     */
    public Rate() {
    }

    public Rate(int score) {
        if(score<MIN_VAL || score>MAX_VAL)
            throw new IllegalArgumentException("Rate has to be in the interval 1 to 5");
        this.score = score;
    }

    /**
     * Returns the score of the rate.
     * @return 
     */
    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "Rate{" + "score=" + score + '}';
    }
    
}
