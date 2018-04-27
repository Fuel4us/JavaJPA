package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.meals.RegisterMealController;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.domain.menus.Menu;
import eapli.ecafeteria.persistence.DishRepository;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.domain.Designation;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.util.DateTime;
import java.util.Date;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pedro Alves <1150372@isep.ipp.pt>
 */
public class MealBootstrapper implements Action{

    private final MenuRepository menuRepository = PersistenceContext.repositories().menus();
    
    @Override
    public boolean execute() {
        final DishRepository repDish = PersistenceContext.repositories().dishes();
        final Dish dish1 = repDish.findByName(Designation.valueOf(TestDataConstants.DISH_NAME_PICANHA)).get();
        final Dish dish2 = repDish.findByName(Designation.valueOf(TestDataConstants.DISH_NAME_BACALHAU_A_BRAZ)).get();
//        Optional<Menu> findById = menuRepository.findById(Long.getLong("26"));
        
        registerMeal(dish1, MealType.LUNCH, DateTime.newCalendar(DateTime.currentYear(), DateTime.currentMonth() + 1, 1).getTime());
        registerMeal(dish1, MealType.DINNER, DateTime.newCalendar(DateTime.currentYear(), DateTime.currentMonth() + 1, 5).getTime());
        registerMeal(dish2, MealType.LUNCH, DateTime.newCalendar(DateTime.currentYear(), DateTime.currentMonth() + 1, 9).getTime());
        registerMeal(dish2, MealType.DINNER, DateTime.newCalendar(DateTime.currentYear(), DateTime.currentMonth() + 2, 14).getTime());
        registerMeal(dish1, MealType.LUNCH, DateTime.newCalendar(DateTime.currentYear(), DateTime.currentMonth() + 2, 18).getTime());
        registerMeal(dish1, MealType.DINNER, DateTime.newCalendar(DateTime.currentYear(), DateTime.currentMonth() + 3, 20).getTime());
        registerMeal(dish1, MealType.DINNER, DateTime.newCalendar(DateTime.currentYear(), DateTime.currentMonth(), 28).getTime());
        registerMeal(dish1, MealType.LUNCH, DateTime.newCalendar(DateTime.currentYear(), DateTime.currentMonth(), 29).getTime());
        registerMeal(dish1, MealType.DINNER, DateTime.newCalendar(DateTime.currentYear(), DateTime.currentMonth() + 1, 2).getTime());
        registerMeal(dish2, MealType.LUNCH, DateTime.newCalendar(DateTime.currentYear(), DateTime.currentMonth() + 1, 3).getTime());
        registerMeal(dish2, MealType.DINNER, DateTime.newCalendar(DateTime.currentYear(), DateTime.currentMonth() + 1, 4).getTime());
        registerMeal(dish1, MealType.LUNCH, DateTime.newCalendar(DateTime.currentYear(), DateTime.currentMonth() + 1, 5).getTime());
        registerMeal(dish1, MealType.LUNCH, DateTime.newCalendar(DateTime.currentYear(), DateTime.currentMonth() + 1, 20).getTime());
        registerMeal(dish2, MealType.DINNER, DateTime.newCalendar(DateTime.currentYear(), DateTime.currentMonth(), 20).getTime());
        
        return true;
    }
    
    private void registerMeal(Dish dish , MealType mealType,Date date){
        final RegisterMealController controller = new RegisterMealController();
        
        try {
            controller.registerMeal(mealType, date, dish);
        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            Logger.getLogger(MealBootstrapper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void registerMeal(Dish dish, MealType mealType, Date date, Menu menu) {
        final RegisterMealController controller = new RegisterMealController();
        
        try {
            controller.registerMeal(mealType, date, dish, menu);
        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            Logger.getLogger(MealBootstrapper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
