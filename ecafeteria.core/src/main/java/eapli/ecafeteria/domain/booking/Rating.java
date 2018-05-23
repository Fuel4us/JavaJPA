package eapli.ecafeteria.domain.booking;

import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Rúben - 1160998 
 * changed by João Pereira <1150478@isep.ipp.pt>
 * changed by Pedro Vieira 1160634
 */
@Entity
public class Rating implements Serializable, AggregateRoot<Long> {

    /**
     * Instance variable that defines the serial version.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Variable that defines the id of the rating.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * Instance variable that defines the score of the rating.
     */
    @Embedded
    private Rate score;

    /**
     * Instance variable that defines the comments and/or answers of the rating.
     */
    @Embedded
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
     * @param strComment
     */
    public Rating(int score, String strComment) {
        this.score = new Rate(score);
        this.comment = new Comment(strComment);
    }

    /**
     * Partial constructor of the class. Comment comment for comments with
     * answer already
     *
     * @param score
     * @param comment
     */
    public Rating(int score, Comment comment) {
        this.score = new Rate(score);
        this.comment = comment;
    }

    /**
     * Returns the score.
     * @return
     */
    public int getScore() {
        return score.getScore();
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
    @Override
    public Long id() {
        return id;
    }

    @Override
    public boolean sameAs(Object other) {
        final Rating r = (Rating) other;
        return this.id().equals(r.id());
    }

    @Override
    public boolean is(Long otherId) {
        return AggregateRoot.super.is(otherId);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 29 * hash + Objects.hashCode(this.score);
        hash = 29 * hash + Objects.hashCode(this.comment);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Rating other = (Rating) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.score.getScore() != other.score.getScore()) {
            return false;
        }
        return Objects.equals(this.comment, other.comment);
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
