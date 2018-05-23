/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.kitchen.MealPlanItemQuantity;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.persistence.repositories.DataRepository;
import java.util.Optional;

/**
 *
 * @author Tiago Babo 1160760
 */
public interface MealPlanItemQuantityRepository extends DataRepository<MealPlanItemQuantity, Long> {

    Optional<MealPlanItemQuantity> findByMeal(Meal meal);
}
