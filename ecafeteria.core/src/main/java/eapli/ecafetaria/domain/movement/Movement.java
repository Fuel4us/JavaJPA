/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafetaria.domain.movement;

import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import eapli.framework.domain.money.Money;
import eapli.framework.util.DateTime;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Currency;
import java.util.Locale;
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
    
    @ManyToOne()    
    private MecanographicNumber nif;
    
    private Money quantity;
    private Calendar date;
    
    @ManyToOne()
    private MovementType movementType;
    
    protected Movement() {
        //ORM
    }
    
    public Movement(MecanographicNumber nif, MovementType movementType, double quantityDouble, Currency currency){
        this.nif = nif;
        this.movementType = movementType;
        quantity = new Money(quantityDouble, currency);
        date= DateTime.now();
    }
}
