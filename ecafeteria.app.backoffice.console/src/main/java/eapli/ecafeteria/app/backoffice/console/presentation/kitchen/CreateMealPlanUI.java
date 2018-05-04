package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.ecafeteria.application.kitchen.CreateMealPlanController;
import eapli.ecafeteria.domain.kitchen.MealPlan;
import eapli.ecafeteria.domain.kitchen.MealPlanItemQuantity;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.menus.Menu;
import eapli.framework.presentation.console.AbstractUI;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Tiago Babo 1160760
 */
public class CreateMealPlanUI extends AbstractUI {

    private final CreateMealPlanController controller = new CreateMealPlanController();
    private final Scanner input = new Scanner(System.in);

    public void mainMenu() {
        int option = 0;

        do {
            option = menuUI();

            switch (option) {
                case 0:
                    System.out.println("\n# END OF CREATE MEAL #");
                    break;
                case 1: 
                    if(setDishQuantity()){
                        System.out.println("\nMeal plan saved successfully!");
                    }else{
                        System.out.println("\nNOT POSSIBLE TO SAVE THE MEAL PLAN DUE TO LACK OF MEALS FOR THE CHOSEN MENU!");
                    }
                    break;
                default:
                    System.out.println("\nINVALID OPTION!");
                    break;
            }
        } while (option != 0);
    }

    public int menuUI() {
        System.out.println("");
        System.out.println("1. Create meal plan");
        System.out.println("0. Leave");
        System.out.printf("OPTION: ");
        int option = input.nextInt();

        return option;
    }

    public Menu selectMenu() {
        List<Menu> menuList = controller.getExistingMenus();
        int opcao;
        int i;

        do {
            i = 0;
            System.out.println("\n## Select the menu for which you wish to create the meal plan: ##");
            for (Menu menu : menuList) {
                System.out.println(i + ". Menu with starting date " + menu.getStartDate()
                        + " and finishing date " + menu.getEndDate());
                i++;
            }
            System.out.printf("OPTION: ");
            opcao = input.nextInt();
        } while (opcao >= menuList.size());

        Menu selectedMenu = controller.getMenu(menuList, opcao);

        return selectedMenu;
    }

    public boolean setDishQuantity() {
        Menu selectedMenu = selectMenu();

        MealPlan mealPlan = controller.createMealPlan(selectedMenu);
        int quantity;
        
        Set<Meal> list = controller.getMealList(mealPlan);
        List<MealPlanItemQuantity> number = controller.getItemQuantityList(mealPlan);
        
        if (list.isEmpty()) {
            return false;
        }
        
        System.out.println("\nAssign the number of dishes for each of the meals:");
        
        for (Meal meal : list) {
            System.out.printf(" --> Date: %s || Meal Type: %s || Dish Name: %s || Dish Type: %s\n",
                                    controller.getMealDate(meal),
                                    controller.getMealType(meal),
                                    controller.getMealDishType(meal),
                                    controller.getMealDishName(meal));
            System.out.printf("Number of dishes: ");
            quantity = input.nextInt();
            System.out.printf("\n");
            MealPlanItemQuantity mpiq = controller.createMealPlanItemQuantity(quantity, meal);
            number.add(mpiq);
            
        }
        controller.saveMealPlan(mealPlan);
        
        return true;
    }

    @Override
    public boolean doShow() {
        mainMenu();
        return true;
    }

    @Override
    public String headline() {
        return "CREATE MEAL PLAN";
    }
}
