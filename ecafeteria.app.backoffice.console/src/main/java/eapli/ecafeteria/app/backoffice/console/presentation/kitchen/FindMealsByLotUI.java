package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.ecafeteria.application.kitchen.FindMealsByLotController;
import eapli.ecafeteria.domain.kitchen.Lot;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.presentation.console.AbstractUI;
import java.util.Scanner;

/**
 * @author Gonçalo Silva (1161140)
 */
public class FindMealsByLotUI extends AbstractUI {

    private final FindMealsByLotController controller = new FindMealsByLotController();

    public void getUsedLots() {
        for (Lot lot : controller.getUsedLots()) {
            System.out.println(lot);
        }

        getCookedMealsWithLot();
    }

    public void getCookedMealsWithLot() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select a lot");
        Long lotId = scanner.nextLong();

        for (Meal meal : controller.getCookedMealsWithLot(lotId)) {
            System.out.println(meal);
        }

        scanner.close();
    }

    @Override
    protected boolean doShow() {
        getUsedLots();
        return true;
    }

    @Override
    public String headline() {
        return "Find Meals By Lot";
    }
}
