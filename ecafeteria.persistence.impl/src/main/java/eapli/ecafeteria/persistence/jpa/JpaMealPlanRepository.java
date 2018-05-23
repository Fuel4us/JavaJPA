package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.persistence.MealPlanRepository;
import javax.persistence.Query;
import eapli.ecafeteria.domain.kitchen.MealPlan;

/**
 *
 * @author Gonçalo Fonseca <1150503@isep.ipp.pt>
 */
public class JpaMealPlanRepository extends CafeteriaJpaRepositoryBase<MealPlan, Long> implements MealPlanRepository {

    @Override
    public void changeState(MealPlan mealPlan) {
    }

    @Override
    public MealPlan getLastMealPlan() {
        Query query = entityManager().createQuery("select mp from " + this.entityClass.getSimpleName() + " mp where mp.id in (select max(mp2.id) from " + this.entityClass.getSimpleName() + " mp2)");

        return (MealPlan) query.getSingleResult();
    }
}
