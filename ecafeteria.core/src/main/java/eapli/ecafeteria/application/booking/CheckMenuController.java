/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.application.menus.ListMenuService;
import eapli.ecafeteria.domain.meals.Meal;
import java.util.List;

/**
 *
 * @author joao reis (1160600)
 */
public class CheckMenuController {
    
    public List<Meal> currentWeekMenu(){
        return ListMenuService.menuForCurrentWeek();
    }
    public List<Meal> nextWeekMenu(){
        return ListMenuService.menuForNextWeek();
    }
}
