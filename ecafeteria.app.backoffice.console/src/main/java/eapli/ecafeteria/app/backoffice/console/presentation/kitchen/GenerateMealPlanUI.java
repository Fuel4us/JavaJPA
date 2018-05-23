package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.ecafeteria.application.kitchen.GenerateMealPlanController;
import eapli.ecafeteria.domain.kitchen.HeuristicConfiguration;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import java.util.Scanner;

/**
 * @author Gonçalo Silva (1161140)
 */
public class GenerateMealPlanUI extends AbstractUI {

    private final GenerateMealPlanController controller = new GenerateMealPlanController();
    Scanner scanner = new Scanner(System.in);

    @Override
    protected boolean doShow() {
        if (!controller.getAvailableHeuristics().iterator().hasNext() || !controller.getMealPlanHistory().iterator().hasNext()) {
            System.out.println("There are no available heuristics and/or past meal plans");
            return false;
        }

        SelectWidget<HeuristicConfiguration> heuristicSelector = new SelectWidget<>("Heuristics:", controller.getAvailableHeuristics());
        heuristicSelector.show();

        controller.generateMealPlan(heuristicSelector.selectedElement());

        return true;
    }

    @Override
    public String headline() {
        return "Generate Meal Plan";
    }
}
