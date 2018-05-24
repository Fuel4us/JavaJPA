/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.kitchen.MealPlanItemQuantity;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.MealPlanItemQuantityRepository;
import java.util.Optional;
import javax.persistence.Query;

/**
 *
 * @author Tiago Babo 1160760
 */
public class JpaMealPlanItemQuantityRepository extends CafeteriaJpaRepositoryBase<MealPlanItemQuantity, Long> implements MealPlanItemQuantityRepository {

    @Override
    public MealPlanItemQuantity findByMeal(Meal meal) {
        Query query = entityManager().createQuery("select e from " + this.entityClass.getSimpleName() + " e where e.meal = :meal ");
        query.setParameter("meal", meal);
        if (!query.getResultList().isEmpty()) {
            return (MealPlanItemQuantity) query.getResultList().get(0);
        }
        return null;

//        Map<String, Object> params = new HashMap<>();
//        params.put("meal", meal);
//        return matchOne("e.meal = :meal", params);
    }

}
