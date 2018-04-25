/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafetaria.domain.finance;

import eapli.ecafeteria.domain.meals.MealType;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Josué Lapa
 */
public class Shift {

    private Date shiftDate; //DATE OU SHIFTDATE?
    private MealType mealType;
    private ShiftState shiftState;

    public Shift(Date shiftDate, MealType mealType) {
        this.shiftDate = shiftDate;
        this.mealType = mealType;
        this.shiftState = ShiftState.CLOSED; 
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
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
        if (!Objects.equals(this.shiftDate, other.shiftDate)) {
            return false;
        }
        if (this.shiftState != other.shiftState) {
            return false;
        }
        return true;
    }
    
    
}
