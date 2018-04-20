package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.ecafeteria.application.kitchen.FindMealsByLotController;
import eapli.ecafeteria.domain.kitchen.MealLot;
import eapli.ecafeteria.domain.meals.Meal;
import java.util.Scanner;

/**
 * @author Gonçalo Silva (1161140)
 */
public class FindMealsByLotUI {

    private final FindMealsByLotController controller = new FindMealsByLotController();

    public FindMealsByLotUI() {
        getUsedLots();
    }

    public void getUsedLots() {
        System.out.println("Getting lots...");

        for (MealLot lot : controller.getUsedLots()) {
            System.out.println(lot);
        }

        getCookedMealsWithLot();
    }

    public void getCookedMealsWithLot() {
        Scanner reader = new Scanner(System.in);

        System.out.println("Select a lot");
        Long lotId = reader.nextLong();

        reader.close();

        for (Meal meal : controller.getCookedMealsWithLot(lotId)) {
            System.out.println(meal);
        }
    }
}
