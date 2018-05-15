package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.kitchen.MealPlan;
import eapli.ecafeteria.persistence.MealPlanRepository;

/**
 *
 * @author Gonçalo Fonseca <1150503@isep.ipp.pt>
 */
public class JpaMealPlanRepository extends CafeteriaJpaRepositoryBase<MealPlan, Long> implements MealPlanRepository{

    @Override
    public void changeState(MealPlan mealPlan) {

    }
}
