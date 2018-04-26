/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.menus;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.menus.Menu;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Berccar
 */
public class ListMenuService {

    private final MenuRepository menuRepository = PersistenceContext.repositories().menus();
    
    public Iterable<Menu> unpublishedMenus(){
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);

        return this.menuRepository.findByState(false);
    }
    public List<Meal> menuByPeriod(Date beginning, Date end) {
        Iterable<Menu> menuList =  menuRepository.findByMenuPeriod(beginning, end);
        List<Meal> mealList = new ArrayList<>();
        
        for(Menu m: menuList) {
            mealList.addAll(m.mealsInPeriod(beginning, end));
        }
        
        Collections.sort(mealList, Meal.compareDates());
        return mealList;
    }
}
