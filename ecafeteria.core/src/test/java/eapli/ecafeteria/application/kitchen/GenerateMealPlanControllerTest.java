package eapli.ecafeteria.application.kitchen;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import eapli.ecafeteria.domain.kitchen.Heuristic;
import eapli.ecafeteria.domain.kitchen.HeuristicA;
import eapli.ecafeteria.domain.kitchen.HeuristicB;
import eapli.ecafeteria.domain.kitchen.HeuristicConfiguration;
import eapli.ecafeteria.domain.kitchen.MealPlan;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Gonçalo Silva (1161140)
 */
public class GenerateMealPlanControllerTest {
    
    GenerateMealPlanController controller = mock(GenerateMealPlanController.class);

    @Test
    public void testNoAvailableHeuristics() {
        List<HeuristicConfiguration> list = new ArrayList<>();

        when(controller.getAvailableHeuristics()).thenReturn(list);
        assertEquals(controller.getAvailableHeuristics(), list);
    }

    @Test
    public void testAvailableHeuristics() {
        List<HeuristicConfiguration> list = new ArrayList<>();
        Heuristic h1 = new HeuristicA("TestA");
        Heuristic h2 = new HeuristicB("TestB");
        HeuristicConfiguration hc1 = new HeuristicConfiguration(h1);
        HeuristicConfiguration hc2 = new HeuristicConfiguration(h2);

        list.add(hc1);
        list.add(hc2);

        when(controller.getAvailableHeuristics()).thenReturn(list);
        assertEquals(controller.getAvailableHeuristics(), list);
    }

    @Test
    public void testNoPastMealPlans() {
        List<MealPlan> list = new ArrayList<>();

        when(controller.getMealPlanHistory()).thenReturn(list);
        assertEquals(controller.getMealPlanHistory(), list);
    }

    @Test
    public void testPastMealPlans() {
        List<MealPlan> list = new ArrayList<>();

        MealPlan mp1 = new MealPlan();
        MealPlan mp2 = new MealPlan();

        list.add(mp1);
        list.add(mp2);

        when(controller.getMealPlanHistory()).thenReturn(list);
        assertEquals(controller.getMealPlanHistory(), list);
    }
}
