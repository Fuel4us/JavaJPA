/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.kitchen.MealPlan;
import eapli.framework.persistence.repositories.DataRepository;

/**
 *
 * @author Tiago Babo 1160760
 */
public interface MealPlanRepository extends DataRepository<MealPlan, Long> {

    public void changeState(MealPlan mealPlan);

    public MealPlan getLastMealPlan();
}
