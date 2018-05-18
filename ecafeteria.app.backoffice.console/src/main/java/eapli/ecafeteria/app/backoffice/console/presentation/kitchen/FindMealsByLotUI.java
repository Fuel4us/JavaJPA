package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.ecafeteria.application.kitchen.FindMealsByLotController;
import eapli.ecafeteria.domain.kitchen.Lot;
import eapli.ecafeteria.domain.kitchen.MealLot;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

/**
 * @author Gonçalo Silva (1161140)
 */
public class FindMealsByLotUI extends AbstractUI {

    private final FindMealsByLotController controller = new FindMealsByLotController();

    @Override
    protected boolean doShow() {
        SelectWidget<Lot> lotSelector = new SelectWidget<>("Lots:", controller.getUsedLots());
        lotSelector.show();

        for (MealLot mealLot : controller.getCookedMealsWithLot(lotSelector.selectedElement())) {
            System.out.println(mealLot.getMeal().toString());
            System.out.println();
        }

        return true;
    }

    @Override
    public String headline() {
        return "Find Meals By Lot";
    }
}
