/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.meals;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.domain.menus.Menu;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author Bernardo Carreira
 */
public class ListMealService {

    private final MealRepository mealRepository = PersistenceContext.repositories().meals();

    public Iterable<Meal> allMeals() {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);

        return this.mealRepository.findAll();
    }

    public Iterable<Meal> allMealsOfMenu(Long id) {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);
        return this.mealRepository.findByIdMenu(id);
    }

    public Iterable<Meal> allMealsAvailablesToMenu(Menu menu) {
        return this.mealRepository.findAllMealsAvailables(menu);
    }

    /**
     * @autor Pedro ALves
     * @return
     */
    public Iterable<MealType> allMealTypes() {
        return MealType.MealTypeValues();
    }

    /**
     * Joao Reis - 1160600
     *
     * @param beginning beginning of the time period
     * @param end end of the time period
     * @return a menu that either starts or ends on the specified time period
     */
    public List<Meal> mealsByPeriod(Date beginning, Date end) {
        List<Meal> mealList = new ArrayList<>();
        Iterable<Meal> mealsInMenu;
        mealsInMenu = mealRepository.findByDatePeriod(beginning, end);
        for (Meal meal : mealsInMenu) {
            mealList.add(meal);
        }

        Collections.sort(mealList, Meal.compareDates());
        return mealList;
    }

    /**
     * Joao Reis - 1160600
     *
     * @return
     */
    public List<Meal> menuForCurrentWeek() {
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

        return mealsByPeriod(beginning, end);
    }

    /**
     * Joao Reis - 1160600
     *
     * @return
     */
    public List<Meal> menuForNextWeek() {
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

        return mealsByPeriod(beginning, end);
    }
}
