package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.ecafeteria.application.kitchen.GenerateMealPlanController;
import eapli.ecafeteria.domain.kitchen.HeuristicConfiguration;
import eapli.ecafeteria.domain.kitchen.MealPlan;
import eapli.ecafeteria.domain.menus.Menu;
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
        if (!controller.getAvailableHeuristics().iterator().hasNext()) {
            System.out.println("There are no available heuristics");
            return false;
        } else if (!controller.getMealPlanHistory().iterator().hasNext()) {
            System.out.println("There are no past meal plans");
            return false;
        } else if (!controller.getExistingMenus().iterator().hasNext()) {
            System.out.println("There are no existing menus");
            return false;
        }

        SelectWidget<Menu> menuSelector = new SelectWidget<>("MENUS", controller.getExistingMenus());
        menuSelector.show();

        SelectWidget<HeuristicConfiguration> heuristicSelector = new SelectWidget<>("HEURISTICS", controller.getAvailableHeuristics());
        heuristicSelector.show();

        MealPlan mealPlan = controller.generateMealPlan(menuSelector.selectedElement(), heuristicSelector.selectedElement());

        System.out.println(mealPlan);

        System.out.println("Do you wish to save the meal plan above? (Y/N)");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();

        if (answer.equals("Y") || answer.equals("y")) {
            controller.saveMealPlan(mealPlan);
            System.out.println("Meal plan saved!");
        } else if (answer.equals("N") || answer.equals("n")) {
            System.out.println("Meal plan not saved");
        } else {
            System.out.println("Invalid option!");
        }

        return true;
    }

    @Override
    public String headline() {
        return "Generate Meal Plan";
    }
}
