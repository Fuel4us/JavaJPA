/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafetaria.domain.movement;

import eapli.framework.domain.money.Money;
import eapli.framework.util.DateTime;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Currency;
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
public class Movement implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    
    private Money quantity;
    private Calendar date;
    
    @ManyToOne()
    private MovementType movementType;
    
    protected Movement() {
        //ORM
    }
    
    public Movement(MovementType movementType, double quantityDouble, Currency currency){
        this.movementType = movementType;
        quantity = new Money(quantityDouble, currency);
        date= DateTime.now();
    }
}
