package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.ecafeteria.application.kitchen.CreateMealPlanController;
import eapli.ecafeteria.domain.kitchen.MealPlan;
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
                    break;
//                case 2:
//                    editMealPlan();
//                    break;
                default:
                    System.out.println("\nINVALID OPTION!");
                    break;
            }
        }while(option != 0);
    }
    
    public int menuUI(){
        System.out.println("");
        System.out.println("1. Create meal plan");
//        System.out.println("2. Edit meal plan");
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
            System.out.println(i + ". Menu with starting date: " + menu.getStartDate()
                                 + "and finishing date: " + menu.getEndDate());
            i++;
        }
        System.out.printf("\nOPTION: ");
        Integer opcao = input.nextInt();

        Menu selectedMenu = controller.getMenu(menuList, opcao);

        return selectedMenu;
    }

    public void setDishQuantity(){
        Menu selectedMenu = selectMenu();
        
        MealPlan mealPlan = controller.createMealPlan(selectedMenu);

        System.out.println("\nAssign the number of dishes for each of the meals:");

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
//    /*=======================================================================*/
//    
//    public MealPlan selectMealPlan() {
//        List<MealPlan> mealPlanList = controller.getExistingMealPlan();
//        
//        System.out.println("\n## Select the meal plan you wish to edit: ##");
//        
//        for(MealPlan mp: mealPlanList){
//            int i = 0;
//            System.out.println(i + ". Meal Plan of Menu with starting date: " + mp.getMenu().getStartDate()
//                                 + "and finishing date: " + mp.getMenu().getEndDate());
//            i++;
//        }
//        System.out.printf("\nOPTION: ");
//        Integer opcao = input.nextInt();
//        
//        MealPlan selectedMealPlan = controller.getMealPlan(mealPlanList, opcao);
//        
//        return selectedMealPlan;
//    }
//    
//    public void editMealPlan(){
//        MealPlan selectedMealPlan = selectMealPlan();
//        
//        System.out.println("\nAssign the new number of dishes for each of the meals:");
//
//        int i = 0;
//        Integer numberOfDishes;
//        
//        List<Integer> oldNumberOfDishes = selectedMealPlan.getNumberOfDishes();
//        
//        controller.cleanNumberOfDishes(selectedMealPlan);
//        
//        for (Meal meal : selectedMealPlan.getMenu().getMealList()) {
//            for(Integer numDish: oldNumberOfDishes){
//                System.out.printf(i + " --> %s || %s || %s || %s\n",
//                                                        controller.getMealDate(meal),
//                                                        controller.getMealDishType(meal),
//                                                        controller.getMealDishName(meal),
//                                                        controller.getMealType(meal));
//                System.out.printf("Old Number of Dishes -> %d\n", numDish);
//                System.out.printf("New Number of dishes: ");
//                numberOfDishes = input.nextInt();
//                System.out.printf("\n");
//
//                controller.setDishQuantity(selectedMealPlan, numberOfDishes);
//
//                i++;
//            }
//        }
//        
//        controller.saveMealPlan(selectedMealPlan);
//    }
//    
//    /*=======================================================================*/
}
