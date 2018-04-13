package eapli.ecafeteria.domain.booking;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Rúben - 1160998
 */
@Entity
public class Rating {
    
    @Id
    @GeneratedValue
    private int id;
    
    private int score;
    private String comment;

    public Rating() {
    }

    public Rating(int score, String comment) {
        this.score = score;
        this.comment = comment;
    }
}
