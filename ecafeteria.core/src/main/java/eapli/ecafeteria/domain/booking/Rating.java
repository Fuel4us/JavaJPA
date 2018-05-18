package eapli.ecafeteria.domain.booking;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Rúben - 1160998
 * changed by João Pereira <1150478@isep.ipp.pt>
 */
@Entity
public class Rating implements Serializable {

    private static final long serialVersionUID = 1L;
    
    // ORM primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int score;

    @OneToOne(cascade=CascadeType.ALL)
    private Comment comment;

    // for ORM
    public Rating() {
    }

    /**
     * String comment for the general use (without specifying the answer of the menu manager)
     * @param score
     * @param comment
     */
    public Rating(int score, String comment) {
        this.score = score;
        this.comment = new Comment(comment);
    }
    
    /**
     * Comment comment for comments with answer already
     * @param score
     * @param comment
     */
    public Rating(int score, Comment comment) {
        this.score = score;
        this.comment = comment;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public Comment getComment() {
        return comment;
    }
    
    public int id() {
        return id;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Rating{" + "id=" + id + ", score=" + score + ", comment=" + comment + '}';
    }
}
