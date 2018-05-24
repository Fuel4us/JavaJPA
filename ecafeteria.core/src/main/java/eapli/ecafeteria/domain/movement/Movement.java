/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.movement;

import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import eapli.framework.domain.ddd.AggregateRoot;
import eapli.framework.domain.money.Money;
import eapli.framework.util.DateTime;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Currency;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 *
 * @author Hernani Gil
 */
@Entity
public class Movement implements AggregateRoot<Long>, Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    private MecanographicNumber nif;

    @Embedded
    private Money quantity;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar date;
    private MovementType movementType;

    protected Movement() {
        //ORM
    }

    public Movement(MecanographicNumber nif, MovementType movementType, double quantityDouble, Currency currency) {
        this.nif = nif;
        this.movementType = movementType;
        this.quantity = new Money(quantityDouble, currency);
        this.date = DateTime.now();
    }

    @Override
    public boolean sameAs(Object other) {
        return false; //can't compare a movement. a movement is unique
    }

    @Override
    public boolean is(Long otherId) {
        return AggregateRoot.super.is(otherId); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long id() {
        return this.id;
    }

    public String toString() {
        return "Id: " + id + "          Date: " + date.getTime() + "          Movement Type: " + movementType.toString() + "          Quantity: " + quantity.toString() + "          Nif: " + nif;
    }

    public MovementType type() {
        return this.movementType;
    }

    public MecanographicNumber nif() {
        return this.nif;
    }

    public Money money() {
        return this.quantity;
    }

    public Calendar date() {
        return this.date;
    }
}
