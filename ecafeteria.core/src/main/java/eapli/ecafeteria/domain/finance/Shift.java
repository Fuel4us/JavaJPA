/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.finance;

import eapli.ecafeteria.domain.meals.MealType;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Id;

/**
 *
 * @author Josué Lapa
 */
public class Shift {
 private static final long serialVersionUID = 1L;

    @Id
    private long id;

    private Date shiftDate; //DATE OU SHIFTDATE?
    private MealType mealType;
    private ShiftState shiftState;

    public Shift(Date shiftDate, MealType mealType) {
        this.shiftDate = shiftDate;
        this.mealType = mealType;
        this.shiftState = ShiftState.CLOSED; 
    }
    
}
