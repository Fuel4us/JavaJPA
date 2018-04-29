/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.finance;

import eapli.ecafeteria.domain.meals.MealType;
import java.util.Date;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Josué Lapa
 */
public class Shift {

    private static final long serialVersionUID = 1L;

    @Id
    private long id;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date shiftDate; //DATE OU SHIFTDATE?
    @ManyToOne
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
    
}
