/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.application.menus.ListMenuService;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.menus.Menu;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.RepositoryFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
