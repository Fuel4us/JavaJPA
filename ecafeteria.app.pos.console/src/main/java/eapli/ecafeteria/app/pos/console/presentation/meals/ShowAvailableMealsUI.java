/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.pos.console.presentation.meals;

import eapli.ecafeteria.application.meals.ShowAvailableMealsController;
import eapli.ecafeteria.domain.dishes.DishType;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import java.util.Map;

/**
 *
 * @author João Pires <your.name at your.org>
 */
public class ShowAvailableMealsUI extends AbstractUI {

    private final ShowAvailableMealsController controller = new ShowAvailableMealsController();

    protected Controller controller() {
        return (Controller) this.controller;
    }

    @Override
    protected boolean doShow() {

        // show lista de meal types
        final Iterable<MealType> mealTypes = this.controller.listMealTypes();

        final SelectWidget selector = new SelectWidget(BORDER, mealTypes);

        /* change to MealTypePrinter */
        selector.show();

        final MealType mealType = (MealType) selector.selectedElement();

        final Iterable<DishType> dishTypes = controller.listDishTypes();

        Map<DishType, Integer> hashMap = controller.listAvailableMeals(mealType, dishTypes);

        hashMap.entrySet().forEach((entry) -> {
            System.out.println(entry.getKey().id() + ": " + entry.getValue());
        });

        return false;
    }

    @Override
    public String headline() {
        return "Show Available Meals";
    }
}
