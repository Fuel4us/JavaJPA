/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

    public void setScore(int score) {
        this.score = score;
    }

    public int id() {
        return id;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
