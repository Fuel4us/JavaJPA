/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.domain.kitchen.MealPlan;
import eapli.ecafeteria.domain.menus.Menu;
import java.util.List;

/**
 *
 * @author Tiago Babo 1160760
 */
public class CreateMealPlanController {
    
    public List<Menu> getExistingMenus(){
        List<Menu> resultingList = null;
        
        //PROVAVELMENTE DEVERA TER DE FAZER ALGUMA COISA COM A BD PARA OBTER OS MENUS
        
        return resultingList;
    }
    
    public Menu getMenu(List<Menu> menuList, int opcao){
        Menu menu = menuList.get(opcao);
        
        
        return menu;
    }
    
    public MealPlan createMealPlan(Menu menu){
        List<Integer> numberDishes = null;
        
        MealPlan mPlan = new MealPlan(menu, numberDishes);
        
        return mPlan;
    }
}
