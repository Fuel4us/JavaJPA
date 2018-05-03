/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.finance.Shift;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.framework.persistence.repositories.DataRepository;
import java.util.Date;

/**
 *
 * @author 11607
 */
public interface ShiftRepository extends DataRepository<Shift, Long>{
    
    public boolean checkShift(Date shiftDate, MealType shiftMealType);
    
}
