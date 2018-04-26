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
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author Berccar
 */
public class ListMenuService {

    private static final MenuRepository menuRepository = PersistenceContext.repositories().menus();

    public Iterable<Menu> unpublishedMenus() {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);

        return this.menuRepository.findByState(false);
    }

    /**
     * Joao Reis - 1160600
     *
     * @param beginning beginning of the time period
     * @param end end of the time period
     * @return a menu that either starts or ends on the specified time period
     */
    public static List<Meal> menuByPeriod(Date beginning, Date end) {
        Iterable<Menu> menuList = menuRepository.findByMenuPeriod(beginning, end);
        List<Meal> mealList = new ArrayList<>();

        for (Menu m : menuList) {
            mealList.addAll(m.mealsInPeriod(beginning, end));
        }

        Collections.sort(mealList, Meal.compareDates());
        return mealList;
    }

    /**
     * Joao Reis - 1160600
     *
     * @return
     */
    public static List<Meal> menuForCurrentWeek() {
        Date beginning, end, aux;
        aux = new Date();

        Calendar cal = new GregorianCalendar();
        cal.setTime(aux);
        while (cal.get(Calendar.DAY_OF_WEEK) > cal.getFirstDayOfWeek()) {
            cal.add(Calendar.DATE, -1);
        }
        beginning = cal.getTime();
        cal.add(Calendar.DATE, 7);
        end = cal.getTime();

        return menuByPeriod(beginning, end);
    }

    /**
     * Joao Reis - 1160600
     *
     * @return
     */
    public static List<Meal> menuForNextWeek() {
        Date beginning, end, aux;
        aux = new Date();

        Calendar cal = new GregorianCalendar();
        cal.setTime(aux);
        while (cal.get(Calendar.DAY_OF_WEEK) > cal.getFirstDayOfWeek()) {
            cal.add(Calendar.DATE, -1);
        }
        cal.add(Calendar.DATE, 8);
        beginning = cal.getTime();
        cal.add(Calendar.DATE, 7);
        end = cal.getTime();

        return menuByPeriod(beginning, end);
    }
}
