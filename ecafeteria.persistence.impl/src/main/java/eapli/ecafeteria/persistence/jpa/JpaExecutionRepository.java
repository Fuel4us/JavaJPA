/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.kitchen.CanteenShift;
import eapli.ecafeteria.domain.kitchen.Execution;
import eapli.ecafeteria.domain.kitchen.Picked;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.ExecutionRepository;

import javax.persistence.Query;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Carlos Figueiredo (1140317)
 */
public class JpaExecutionRepository extends CafeteriaJpaRepositoryBase<Execution, Long> implements ExecutionRepository {

    @Override
    public int getMealActuallyServed(Meal meal) {
        Query query =entityManager().createQuery("SELECT e.cookedMeals from Execution e where e.meal= :id");
        query.setParameter("id",meal);

        return (int) query.getResultList().get(0);

    }

    @Override
    public Iterable<Execution> getExecutionByMeal(Meal meal) {
        Query query =entityManager().createQuery("SELECT e from Execution e where e.meal= :id");
        query.setParameter("id",meal);

        return query.getResultList();
    }

    @Override
    public int getPickedMeals(Meal meal) {
        Query query =entityManager().createQuery("SELECT e.picked from Execution e where e.meal= :id");
        query.setParameter("id",meal);
        Picked picked = (Picked) query.getResultList().get(0);
        return picked.getPickedQuantity();
    }
}
