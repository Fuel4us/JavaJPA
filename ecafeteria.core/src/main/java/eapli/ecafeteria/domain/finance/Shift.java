/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.finance;

import eapli.ecafeteria.domain.meals.MealType;
import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Josué Lapa
 */
public class Shift implements AggregateRoot<Long>, Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date shiftDate; //DATE OU SHIFTDATE?
    @OneToOne
    private MealType mealType;
    @OneToOne
    private ShiftState shiftState;

    public Shift() {
    }

    public Shift(Date shiftDate, MealType mealType) {
        this.shiftDate = shiftDate;
        this.mealType = mealType;
        this.shiftState = ShiftState.CLOSED;
    }

    @Override
    public Long id() {
        return this.id;
    }

    @Override
    public boolean sameAs(Object other) {
        return this.equals(other);
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Shift other = (Shift) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.shiftDate, other.shiftDate)) {
            return false;
        }
        if (this.mealType != other.mealType) {
            return false;
        }
        if (this.shiftState != other.shiftState) {
            return false;
        }
        return true;
    }
    
}
