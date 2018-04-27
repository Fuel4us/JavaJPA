/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.finance.Shift;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.persistence.ShiftRepository;
import java.util.Date;

/**
 *
 * @author 11607
 */
public class JpaShiftRepository extends CafeteriaJpaRepositoryBase<Shift, Long> implements ShiftRepository {

    @Override
    public Iterable<Shift> findByDate(Date shiftDate) {
        return null;

    }

    @Override
    public boolean checkShift(Date shiftDate, MealType shiftMealType) {
        return false;

    }
    @Override
    public void addShift(Date shiftDate, MealType shiftMealType){
        
    }
}
