/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.finance.Shift;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.persistence.ShiftRepository;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.util.DateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.NoResultException;

/**
 *
 * @author 11607
 */
public class JpaShiftRepository extends CafeteriaJpaRepositoryBase<Shift, Long> implements ShiftRepository {

    @Override
    public boolean checkShift(Date shiftDate, MealType mealType) {
        final Map<String, Object> params = new HashMap<>();
        params.put("shiftDate", shiftDate);
        params.put("mealType", mealType);
        try {
            matchOne("e.shiftDate = :shiftDate AND e.mealType = :mealType", params).isPresent();
            return false;
        } catch (NoResultException e) {
            return true;
        }
    }

    @Override
    public boolean closeCurrentShift() {

        //now
        Date shiftDate = DateTime.now().getTime();
        Calendar now = DateTime.now();
        MealType mealType = MealType.LUNCH;

        //current mealtype
        if (now.get(Calendar.HOUR_OF_DAY) > Shift.DINNER_INIT_HOUR) {
            mealType = MealType.DINNER;
        } else {
            if (now.get(Calendar.HOUR_OF_DAY) == Shift.DINNER_INIT_HOUR) {
                if (now.get(Calendar.MINUTE) > 0) {
                    mealType = MealType.DINNER;
                }
            }
        }

        final Map<String, Object> params = new HashMap<>();
        params.put("shiftDate", shiftDate);
        params.put("mealType", mealType);

        try {
            Shift shift = matchOne("e.shiftDate = :shiftDate AND e.mealType = :mealType", params).get();
            if (shift.close()) {
                try {
                    this.save(shift);
                } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
                    Logger.getLogger(JpaShiftRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
                return true;
            }
            return false;
        } catch (NoResultException e) {
            return false;
        }
    }
}
