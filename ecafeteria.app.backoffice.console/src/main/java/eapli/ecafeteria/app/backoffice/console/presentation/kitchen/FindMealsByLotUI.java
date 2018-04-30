package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.ecafeteria.application.kitchen.FindMealsByLotController;
import eapli.ecafeteria.domain.kitchen.Lot;
import eapli.ecafeteria.domain.kitchen.MealLot;
import eapli.framework.presentation.console.AbstractUI;
import java.util.Scanner;

/**
 * @author Gonçalo Silva (1161140)
 */
public class FindMealsByLotUI extends AbstractUI {

    private final FindMealsByLotController controller = new FindMealsByLotController();
    private final Scanner scanner = new Scanner(System.in);

    @Override
    protected boolean doShow() {
        for (Lot lot : controller.getUsedLots()) {
            System.out.println(lot.toString2());
            System.out.println();
        }

        System.out.println("Select a lot");
        int lotCode = scanner.nextInt();
        
        System.out.println();

        Long lotId = controller.getLotPkWithCode(lotCode);

        Lot lot = controller.getSelectedLot(lotId).get();

        for (MealLot mealLot : controller.getMealLotWithLot(lot)) {
            System.out.println(mealLot.getMeal().toStringOnlyMeal());
            System.out.println();
        }

        return true;
    }

    @Override
    public String headline() {
        return "Find Meals By Lot";
    }
}
