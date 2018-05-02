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
import java.util.HashMap;
import java.util.Map;
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
}
