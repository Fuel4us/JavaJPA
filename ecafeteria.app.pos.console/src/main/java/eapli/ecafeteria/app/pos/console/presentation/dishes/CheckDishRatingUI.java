/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.pos.console.presentation.dishes;

import eapli.ecafeteria.application.dishes.CheckDishRatingController;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import java.util.Map;

/**
 *
 * @author João Pires <your.name at your.org>
 */
public class CheckDishRatingUI extends AbstractUI {

    private final CheckDishRatingController controller = new CheckDishRatingController();

    protected Controller controller() {
        return (Controller) this.controller;
    }

    @Override
    protected boolean doShow() {

        // show list of dishes
        Iterable<Dish> dishes = this.controller.listDishes();

        Iterable<String> dishNames = this.controller.listNameDishes();

        SelectWidget selector = new SelectWidget(BORDER, dishNames);

        /* change to DishPrinter */
        selector.show();

        final String dishName = (String) selector.selectedElement();

        for (Dish dish : dishes) {
            if (dishName.equals(dish.name().toString())) {
                Map<Dish, Integer> map = controller.getRatingPerDish(dish);

                map.entrySet().forEach((entry) -> {
                    System.out.println(entry.getKey().id() + " : " + entry.getValue());
                });
            }
        }

        return false;
    }

    @Override
    public String headline() {
        return "Check Dish Rating";
    }

}
