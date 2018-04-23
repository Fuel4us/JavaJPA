/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafetaria.domain.movement;

import eapli.ecafetaria.domain.finance.Transaction;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author Hernani Gil
 */
@Entity
public class Movement {
    @Id
    @GeneratedValue
    private int id;
    
    @ManyToOne()
    private MovementType movementType;
    
    @OneToOne
    Transaction transation;
    
    protected Movement() {
        //ORM
    }
    
    public Movement(MovementType movementType, Transaction transaction){
        this.movementType = movementType;
        this.transation = transaction;
    }
}
