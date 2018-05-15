/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.booking;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Rúben - 1160998
 */
@Entity
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int score;

    @OneToOne
    private Comment comment;

    public Rating() {
    }

    public Rating(int score, String comment) {
        this.score = score;
        this.comment = new Comment(comment);
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
