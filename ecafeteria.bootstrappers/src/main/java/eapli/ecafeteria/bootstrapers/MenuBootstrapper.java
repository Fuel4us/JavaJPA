package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.meals.RegisterMealController;
import eapli.ecafeteria.application.menus.RegisterMenuController;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.menus.Menu;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.util.DateTime;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pedro Alves 
 */
public class MenuBootstrapper implements Action {

    Menu menu = new Menu();
    RegisterMenuController controller = new RegisterMenuController();
    RegisterMealController controllerMeal = new RegisterMealController();

    @Override
    public boolean execute() {

        Date date1 = DateTime.newCalendar(DateTime.currentYear(), DateTime.currentMonth() + 1, 1).getTime();
        Date date2 = DateTime.newCalendar(DateTime.currentYear(), DateTime.currentMonth() + 3, 1).getTime();
        Date date3 = DateTime.newCalendar(DateTime.currentYear(), DateTime.currentMonth() + 6, 1).getTime();

        Date date4 = DateTime.newCalendar(DateTime.currentYear(), DateTime.currentMonth() - 1, 12).getTime();
        Date date5 = DateTime.newCalendar(DateTime.currentYear(), DateTime.currentMonth() - 1, 14).getTime();

        registerMenu(date1, date2, "Menu01");
        Iterable it = controller.getAllMealsAvailablesToMenu(menu);
        try {
            for (Object meal : it) {
                controllerMeal.updateMeal((Meal) meal, menu);
            }
        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            Logger.getLogger(MenuBootstrapper.class.getName()).log(Level.SEVERE, null, ex);
        }

        registerMenu(date2, date3, "Menu02");
        Iterable itr = controller.getAllMealsAvailablesToMenu(menu);
        try {
            for (Object meal : itr) {
                controllerMeal.updateMeal((Meal) meal, menu);
            }
        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            Logger.getLogger(MenuBootstrapper.class.getName()).log(Level.SEVERE, null, ex);
        }

        registerMenu(date4, date5, "MenuBooking");
        Iterable iter = controller.getAllMealsAvailablesToMenu(menu);
        try {
            for (Object meal : iter) {
                controllerMeal.updateMeal((Meal) meal, menu);
                controllerMeal.updateMenuState(menu, true);
            }
        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            Logger.getLogger(MenuBootstrapper.class.getName()).log(Level.SEVERE, null, ex);
        }

        registerMenu(date1, date3, "Menu03");
        return true;
    }

    private void registerMenu(Date begDate, Date endDate, String nome) {
        try {
            menu = controller.registerMenu(begDate, endDate, nome);
        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            Logger.getLogger(MealBootstrapper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
