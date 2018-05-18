package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.application.meals.ListMealService;
import eapli.ecafeteria.application.menus.RegisterMenuController;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.domain.menus.Menu;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.DishRepository;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Pedro Alves <1150372@isep.ipp.pt>
 */
public class CheckRatingsMealsController {

    private final BookingRepository bookingRepository = PersistenceContext.repositories().booking();
    private final MealRepository mealRepository = PersistenceContext.repositories().meals();
    private final MenuRepository menuRepository = PersistenceContext.repositories().menus();
    private final DishRepository dishRepository = PersistenceContext.repositories().dishes();

    private final ListMealService svc = new ListMealService();

    private final RegisterMenuController theRegisterMenuController = new RegisterMenuController();

    public List<Menu> getAllMenusDelivered() {
        List<Menu> menusDelivered = new ArrayList<>();
        Iterable<Menu> allMenusPublish = menuRepository.findByState(true);
        for (Menu menu : allMenusPublish) {
            if (!menu.getEndDate().after(new Date())) {
                menusDelivered.add(menu);
            }
        }
        return menusDelivered;
    }

    public List<Meal> getAllMealsDelivered() {
        List<Meal> mealsDelivered = new ArrayList<>();
        Iterable<Meal> allMeals = mealRepository.findAll();
        for (Meal meal : allMeals) {
            if (!meal.getMealDate().after(new Date())) {
                mealsDelivered.add(meal);
            }
        }
        return mealsDelivered;
    }

    public Iterable<MealType> getAllMealTypes() {
        return this.svc.allMealTypes();
    }

    public Iterable<Dish> getAllDishes() {
        return dishRepository.findAll();
    }

    private List<Meal> getAllMealsByDate(Date date) {
        List<Meal> mealsDate = new ArrayList<>();
        Iterable<Meal> allMeals = getAllMealsDelivered();
        for (Meal meal : allMeals) {
            if (meal.getMealDate().equals(date)) {
                mealsDate.add(meal);
            }
        }
        return mealsDate;
    }

    private List<Meal> getAllMealByDish(Dish dish) {
        List<Meal> mealsOfDish = new ArrayList<>();
        Iterable<Meal> allMealsDelivered = getAllMealsDelivered();
        for (Meal meal : allMealsDelivered) {
            if (meal.getDish().equals(dish)) {
                mealsOfDish.add(meal);
            }
        }
        return mealsOfDish;
    }

    private List<Meal> getAllMealByMealType(MealType MealType) {
        List<Meal> mealsByMealType = new ArrayList<>();
        Iterable<Meal> allMealsDelivered = getAllMealsDelivered();
        for (Meal meal : allMealsDelivered) {
            if (meal.getMealType().equals(MealType)) {
                mealsByMealType.add(meal);
            }
        }
        return mealsByMealType;
    }

    private Iterable<Meal> getAllMealsByMenu(Menu menu) {
        return theRegisterMenuController.getAllMealsOfMenu(menu);
    }

}
