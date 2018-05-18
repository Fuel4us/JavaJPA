package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.domain.kitchen.HeuristicConfiguration;
import eapli.ecafeteria.domain.kitchen.MealPlan;
import eapli.ecafeteria.persistence.HeuristicRepository;
import eapli.ecafeteria.persistence.MealPlanRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import java.util.Optional;

/**
 * @author Gonçalo Silva (1161140)
 */
public class GenerateMealPlanController {

    private final MealPlanRepository mealPlanRepository = PersistenceContext.repositories().mealplans();
    private final HeuristicRepository heuristicRepository = PersistenceContext.repositories().heuristics();

    public Iterable<HeuristicConfiguration> getAvailableHeuristics() {
        return heuristicRepository.findAll();
    }

    public Optional<HeuristicConfiguration> getSelectedHeuristic(HeuristicConfiguration heuristic) {
        return heuristicRepository.findOne(heuristic.id());
    }

    public void generateMealPlan(HeuristicConfiguration heuristic) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Iterable<MealPlan> getMealPlanHistory() {
        return mealPlanRepository.findAll();
    }
}
