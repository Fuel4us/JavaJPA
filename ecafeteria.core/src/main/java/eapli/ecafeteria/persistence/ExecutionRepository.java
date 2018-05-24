/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.kitchen.CanteenShift;
import eapli.ecafeteria.domain.kitchen.Execution;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.persistence.repositories.DataRepository;

/**
 *
 * @author Carlos Figueiredo (1140317)
 */
public interface ExecutionRepository extends DataRepository<Execution, Long> {
    int getMealActuallyServed(Meal meal);
    Iterable<Execution> getExecutionByMeal(Meal meal);
    int getPickedMeals(Meal meal);
}