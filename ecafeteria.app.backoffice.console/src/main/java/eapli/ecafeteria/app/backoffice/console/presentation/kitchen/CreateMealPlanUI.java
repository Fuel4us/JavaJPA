package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.ecafeteria.application.kitchen.CreateMealPlanController;
import eapli.ecafeteria.domain.kitchen.MealPlan;
import eapli.ecafeteria.domain.kitchen.MealPlanItem;
import eapli.ecafeteria.domain.kitchen.MealPlanItemQuantity;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.menus.Menu;
import eapli.framework.presentation.console.AbstractUI;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Tiago Babo 1160760
 */
public class CreateMealPlanUI extends AbstractUI{
    private final CreateMealPlanController controller = new CreateMealPlanController();
    private final Scanner input = new Scanner(System.in);
    
    public void mainMenu(){
        int option = 0;
        
        do{
            option = menuUI();
            
            switch(option){
                case 0:
                    System.out.println("\n# END OF CREATE MEAL #");
                    break;
                case 1:
                    setDishQuantity();
                    System.out.println("Meal plan saved successfully!");
                    break;
                default:
                    System.out.println("\nINVALID OPTION!");
                    break;
            }
        }while(option != 0);
    }
    
    public int menuUI(){
        System.out.println("");
        System.out.println("1. Create meal plan");
        System.out.println("0. Leave");
        System.out.printf("OPTION: ");
        int option = input.nextInt();
        
        return option;
    }
    
    public Menu selectMenu() {
        
        List<Menu> menuList = controller.getExistingMenus();
        
        System.out.println("\n## Select the menu for which you wish to create the meal plan: ##");
        
        int i = 0;

        for (Menu menu : menuList) {
            System.out.println(i + ". Menu with starting date " + menu.getStartDate()
                                 + " and finishing date " + menu.getEndDate());
            i++;
        }
        System.out.printf("OPTION: ");
        Integer opcao = input.nextInt();

        Menu selectedMenu = controller.getMenu(menuList, opcao);

        return selectedMenu;
    }

    public void setDishQuantity(){
        Menu selectedMenu = selectMenu();
        
        MealPlan mealPlan = controller.createMealPlan(selectedMenu);
        MealPlanItem item = null;
        MealPlanItemQuantity itemQuantity = null;

        System.out.println("\nAssign the number of dishes for each of the meals:");

        int i = 0;
        int quantity;

        for (Meal meal : mealPlan.getMenu().getMealList()) {
            System.out.printf(i + " --> %s || %s || %s || %s\n",
                                                    controller.getMealDate(meal),
                                                    controller.getMealDishType(meal),
                                                    controller.getMealDishName(meal),
                                                    controller.getMealType(meal));
            System.out.printf("Number of dishes: ");
            quantity = input.nextInt();
            System.out.printf("\n");

            item = controller.setPlanItem(meal, mealPlan);
            itemQuantity = controller.setItemQuantity(meal, quantity, mealPlan, item);

            i++;
        }
        
        controller.saveMealPlan(mealPlan);
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
