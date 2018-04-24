package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.ecafeteria.application.kitchen.CreateMealPlanController;
import eapli.ecafeteria.domain.kitchen.MealPlan;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.menus.Menu;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Tiago Babo 1160760
 */
public class CreateMealPlanUI {
    private final CreateMealPlanController controller = new CreateMealPlanController();
    private final Scanner input = new Scanner(System.in);

    public Menu selectMenu() {
        System.out.println("Select the menu for which you wish to create the meal plan:");
        List<Menu> menuList = controller.getExistingMenus();
        int i = 0;

        for (Menu menu : menuList) {
            System.out.println(i + ". Menu " + menu.id());
            i++;
        }
        System.out.printf("OPCAO: ");
        Integer opcao = input.nextInt();

        Menu selectedMenu = controller.getMenu(menuList, opcao);

        return selectedMenu;
    }

    public void setDishQuantity() {
        Menu selectedMenu = selectMenu();

        MealPlan mealPlan = controller.createMealPlan(selectedMenu);

        System.out.println("Assign the number of dishes for each of the meals:");

        int i = 0;
        Integer numberOfDishes;

        for (Meal meal : mealPlan.getMenu().getMealList()) {
            System.out.printf(i + " --> %s || %s || %s || %s\n",
                                                    controller.getMealDate(meal),
                                                    controller.getMealDishType(meal),
                                                    controller.getMealDishName(meal),
                                                    controller.getMealType(meal));
            System.out.printf("Number of dishes: ");
            numberOfDishes = input.nextInt();
            System.out.printf("\n");

            controller.setDishQuantity(mealPlan, numberOfDishes);

            i++;
        }
        
        controller.saveMealPlan();
        
        System.out.println("Meal plan saved successfully!");
    }
}
