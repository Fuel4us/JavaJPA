/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.ecafeteria.application.kitchen.CreateMealPlanController;
import eapli.ecafeteria.domain.kitchen.MealPlan;
import eapli.ecafeteria.domain.menus.Menu;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Tiago Babo 1160760
 */
public class CreateMealPlanUI {
    CreateMealPlanController controller;
    Scanner input = new Scanner(System.in);
    
    public void selectMenu(){
        System.out.println("Select the menu for which you wish to create the meal plan:");
        List<Menu> menuList = controller.getExistingMenus();
        int i = 1;
        
        for(Menu menu: menuList){
            System.out.println(i + ". " + menu);
            i++;
        }
        System.out.printf("OPCAO: ");
        int opcao = input.nextInt();
        
        Menu selectedMenu = controller.getMenu(menuList, opcao);
        
        MealPlan mealPlan = controller.createMealPlan(selectedMenu);
        
        System.out.println("Assign the number of dishes for each of the the meals:");
        
    }
}
