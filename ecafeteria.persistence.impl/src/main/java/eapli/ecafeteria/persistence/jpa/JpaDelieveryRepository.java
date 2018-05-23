/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.delivery.Delievery;
import eapli.ecafeteria.domain.finance.Shift;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.persistence.DelieveryRepository;
import eapli.framework.domain.Designation;
import eapli.framework.util.DateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.NoResultException;

/**
 *
 * @author Ana Mafalda Silva
 */
public class JpaDelieveryRepository extends CafeteriaJpaRepositoryBase<Delievery, Long> implements DelieveryRepository {

    public Delievery findByName(Designation name) {
        // TODO use parameters instead of string concatenation
        return (matchOne("e.name.designation='" + name + "'")).get();
    }

    @Override
    public List<Delievery> findCurrentShiftDeliveries() {

        //now
        Date deliveryDate = DateTime.now().getTime();
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

        List<Delievery> deliveries = new ArrayList<>();

        final Map<String, Object> params = new HashMap<>();
        params.put("deliveryDate", deliveryDate);
        params.put("mealType", mealType);

        try {
            deliveries.addAll(match("e.DeliveryDate = :deliveryDate AND e.booking.meal.mealtype = :mealType", params));

        } catch (NoResultException e) {
            return null;
        }

        return deliveries;
    }
}
