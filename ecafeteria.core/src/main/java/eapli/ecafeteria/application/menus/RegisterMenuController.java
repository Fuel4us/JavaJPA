/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.menus;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.application.meals.ListMealService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.menus.Menu;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.domain.Designation;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Pedro Alves <1150372@isep.ipp.pt>
 */
public class RegisterMenuController {

    private final MenuRepository menuRepository = PersistenceContext.repositories().menus();

    private final MealRepository mealRepository = PersistenceContext.repositories().meals();

    Iterable<Meal> allMeals;

    private Menu menu = new Menu();

    public Menu registerMenu(Date menuDateBeg, Date menuDateEnd) throws DataConcurrencyException, DataIntegrityViolationException {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);
        return validateDates(menuDateBeg, menuDateEnd);
    }

    public Menu registerMenu(Date menuDateBeg, Date menuDateEnd, String name) throws DataConcurrencyException, DataIntegrityViolationException {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);
        return validateDates(menuDateBeg, menuDateEnd, Designation.valueOf(name));
    }

    private Menu validateDates(Date begDate, Date endDate) throws DataConcurrencyException, DataIntegrityViolationException {
        final Menu newMenu = new Menu(begDate, endDate);
        this.menu = newMenu;
        return this.menuRepository.save(newMenu);
    }

    private Menu validateDates(Date menuDateBeg, Date menuDateEnd, Designation valueOf) throws DataConcurrencyException, DataIntegrityViolationException {
        final Menu newMenu = new Menu(menuDateBeg, menuDateEnd, valueOf);
        this.menu = newMenu;
        return this.menuRepository.save(newMenu);
    }

    public Iterable<Meal> getAllMealsAvailablesToMenu(Menu menu) {

        Set<Meal> mealsAvailables = new HashSet<>();
        allMeals = this.mealRepository.findAll();
        for (Meal meal : allMeals) {
            if (meal.getMealDate() != null) {
                if (meal.getMenu() == null && meal.validaMenu(menu)) {
                    mealsAvailables.add(meal);
                }
            }
        }
        return mealsAvailables;
    }

    public Iterable<Meal> getAllMealsOfMenu(Menu menu) {
        Set<Meal> mealsAvailables = new HashSet<>();
        allMeals = this.mealRepository.findAll();

        if (allMeals == null) {
            return new HashSet<>();
        }
        for (Meal meal : allMeals) {
            if (meal.getMenu() != null) {
                if (meal.getMenu().id().equals(menu.id())) {
                    mealsAvailables.add(meal);
                }
            }
        }
        return mealsAvailables;
    }

    public void printerMenuInformations(Menu menu) {
        Iterable<Meal> mealsOfMenu;
        mealsOfMenu = getAllMealsOfMenu(menu);
        System.out.println("***     MENU BEM INSERIDO ***");
        System.out.println(menu.toString());
        System.out.println("*****       MEALS DO MENU       *****");
        System.out.println(mealsOfMenu.toString());
    }

}
