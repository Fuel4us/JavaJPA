package eapli.ecafeteria.domain.kitchen;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author joaotiagow
 */
public interface Heuristic extends Serializable {

    public void doHeuristicLogic();

    public List<MealPlanItemQuantity> returnMealPlanItemQuantityList();
}
