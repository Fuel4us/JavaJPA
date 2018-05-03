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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Josué Lapa
 */
@Entity
public class Shift implements AggregateRoot<Long>, Serializable{
    
    private static final long serialVersionUID = 1L;
    //public  static final int LUNCH_INIT_HOUR = 12;
    //public  static final int LUNCH_END_HOUR = 15;
    public  static final int DINNER_INIT_HOUR = 15;
    //public static final int DINNER_END_HOUR = 21;
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date shiftDate; //DATE OU SHIFTDATE?
    private MealType mealType;
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
