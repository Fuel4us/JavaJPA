/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.ecafeteria.application.kitchen.RegisterMealsActuallyCookedController;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos Figueiredo (1140317)
 */
public class RegisterMealsActuallyCookedUI extends AbstractUI {

    private final RegisterMealsActuallyCookedController controller = new RegisterMealsActuallyCookedController();

    protected Controller controller() {
        return this.controller;
    }

    @Override
    protected boolean doShow() {
        List<Meal> list = controller.getAllMeals();
        if (!list.isEmpty()) {
            controller.setMealList(list);
        }
        int choice = Console.readInteger("What information do you want to use for the search?\n1 - Day\n2 - Meal Type\n3 - Dish\n4 - Meal");
        if (choice > 0 && choice < 5) {
            switch (choice) {
                case 1:
                    displayMealsByDate(list);
                    mealsActuallyMade();
                    break;
                case 2:
                    displayMealsByMealType(list);
                    mealsActuallyMade();
                    break;
                case 3:
                    displayMealsByDish(list);
                    mealsActuallyMade();
                    break;
                case 4:
                    displayMealsByMeal(list);
                    mealsActuallyMade();
                    break;
                default:
                    break;
            }
        } else {
            System.out.println("There is no such choice");

        }
        return true;
    }

    public boolean mealsActuallyMade() {

        final long mealCode = Console.readLong("Meal ID: ");
        final int cookedMeals = Console.readInteger("Meals Actually Made: ");
        try {
            if (this.controller.checkedMeal(mealCode)) {
                this.controller.registerMealsActuallyMade(mealCode, cookedMeals);
            }
        } catch (DataIntegrityViolationException | DataConcurrencyException ex) {
            Logger.getLogger(RegisterMealsActuallyCookedUI.class.getName()).log(Level.SEVERE, null, ex);
        }


        return false;

    }


@Override
        public String headline() {
        return "Check Meals By Data";
    }

    public void displayMealsByDate(List<Meal> list) {

        if (!list.isEmpty()) {
            List<Date> dateList = controller.displayMealsByDate();
            for (int i = 0; i < dateList.size(); i++) {
                System.out.println(i + 1 + " Meal Date: " + dateList.get(i) + "\n");
            }

            int choice = Console.readInteger("Which date do you want to see ?\n");
            if (choice > 0 && choice < dateList.size() + 1) {
                for (Meal meal
                        : list) {
                    if (meal.getMealDate().equals(dateList.get(choice - 1))) {
                        System.out.println(meal.getId()+ " : " + meal);
                    }
                }
            } else {
                System.out.println("That option doesn't exist");
                displayMealsByDate(list);
            }
        } else {
            System.out.println("There are no meals for this filter");
        }
    }

    public void displayMealsByDish(List<Meal> list) {

        if (!list.isEmpty()) {
            List<Dish> dishList = controller.displayMealsByDish();
            for (int i = 0; i < dishList.size(); i++) {
                System.out.println(i + 1 + "  Dish: " + dishList.get(i) + "\n");
            }

            int choice = Console.readInteger("Which Dish do you want to see ?\n");
            if (choice > 0 && choice < dishList.size() + 1) {
                for (Meal meal
                        : list) {
                    if (meal.getDish().equals(dishList.get(choice - 1))) {
                        System.out.println(meal.getId()+ " : " + meal);
                    }
                }
            } else {
                System.out.println("That option doesn't exist");
                displayMealsByDish(list);
            }
        } else {
            System.out.println("There are no meals for this filter");
        }
    }

    public void displayMealsByMeal(List<Meal> list) {

        if (list.isEmpty()) {
            List<Meal> mealList = controller.displayMealsByMeal();
            for (int i = 0; i < mealList.size(); i++) {
                System.out.println(i + 1 + "  Meal: " + mealList.get(i) + "\n");
            }

            int choice = Console.readInteger("Which Meal do you want to see ?\n");
            if (choice > 0 && choice < mealList.size() + 1) {
                for (Meal meal
                        : list) {
                    if (meal.equals(mealList.get(choice - 1))) {
                        System.out.println(meal.getId()+ " : " + meal);
                    }
                }
            } else {
                System.out.println("That option doesn't exist");
                displayMealsByMeal(list);
            }
        } else {
            System.out.println("There are no meals for this filter");
        }
    }

    public void displayMealsByMealType(List<Meal> list) {
        if (!list.isEmpty()) {
            List<MealType> mealTypeList = controller.displayMealsByMealType();
            for (int i = 0; i < mealTypeList.size(); i++) {
                System.out.println(i + 1 + " Booking Meal Type: " + mealTypeList.get(i) + "\n");
            }
            int choice = Console.readInteger("Which Meal Type do you want to see ?\n");
            if (choice > 0 && choice < mealTypeList.size() + 1) {
                for (Meal meal
                        : list) {
                    if (meal.getMealType().equals(mealTypeList.get(choice - 1))) {
                        System.out.println(meal.getId()+ " : " + meal);
                    }
                }
            } else {
                System.out.println("That option doesn't exist");
                displayMealsByMealType(list);
            }
        } else {
            System.out.println("There are no meals for this filter");
        }
    }
}
