package eapli.ecafeteria.domain.booking;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Rúben - 1160998 
 * changed by João Pereira <1150478@isep.ipp.pt>
 */
@Entity
public class Rating implements Serializable {

    /**
     * Instance variable that defines the serial version.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Variable that defines the id of the rating.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Instance variable that defines the score of the rating.
     */
    private int score;

    /**
     * Instance variable that defines the comments and/or answers of the rating.
     */
    @ManyToOne(cascade=CascadeType.ALL)
    private Comment comment;

    /**
     * Empty constructor of the class for the ORM.
     */
    public Rating() {
    }

    /**
     * Complete constructor of the class. String comment for the general use
     * (without specifying the answer of the menu manager)
     *
     * @param score
     * @param comment
     */
    public Rating(int score, String comment) {
        this.score = score;
        this.comment = new Comment(comment);
    }

    /**
     * Partial constructor of the class. Comment comment for comments with
     * answer already
     *
     * @param score
     * @param comment
     */
    public Rating(int score, Comment comment) {
        this.score = score;
        this.comment = comment;
    }

    /**
     * Modifies the score.
     *
     * @param score
     */
    public void setScore(int score) {
        this.score = score;
    }
    
    /**
     * Returns the score.
     * @return
     */
    public int getScore() {
        return score;
    }

    /**
     * Returns the comment.
     *
     * @return
     */
    public Comment getComment() {
        return comment;
    }

    /**
     * Returns the id.
     *
     * @return
     */
    public int id() {
        return id;
    }

    /**
     * Modifies the comment.
     *
     * @param comment
     */
    public void setComment(Comment comment) {
        this.comment = comment;
    }

    /**
     * Returns a brief description of the rating.
     *
     * @return
     */
    @Override
    public String toString() {
        return "Rating{" + "id=" + id + ", score=" + score + ", comment=" + comment + '}';
    }
}
