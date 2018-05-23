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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Pedro Alves <1150372@isep.ipp.pt>
 */
public class CheckRatingsController {

    private final BookingRepository bookingRepository = PersistenceContext.repositories().booking();
    private final MealRepository mealRepository = PersistenceContext.repositories().meals();
    private final MenuRepository menuRepository = PersistenceContext.repositories().menus();
    private final DishRepository dishRepository = PersistenceContext.repositories().dishes();

    private final ListMealService svc = new ListMealService();

    private final RegisterMenuController theRegisterMenuController = new RegisterMenuController();

    /**
     * Method that returns the list of menus that have already been delivered,
     * ie the end date of the menu is lower than the current date.
     *
     * @return
     */
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

    /**
     * Method that returns the list of meals that have already been delivered,
     * ie the meal date is less than the current date.
     *
     * @return
     */
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

    /**
     * Method that returns all MealTypes.
     *
     * @return
     */
    public Iterable<MealType> getAllMealTypes() {
        return this.svc.allMealTypes();
    }

    /**
     * Method that return all Dishes.
     *
     * @return
     */
    public Iterable<Dish> getAllDishes() {
        return dishRepository.findAll();
    }

    /**
     * Method that returns the statistics by date.
     *
     * @param date
     * @return
     */
    public Set<Integer> getRatingsByDate(Date date) {
        List<Meal> listMeal = getAllMealsByDate(date);

        return getRatings(listMeal);
    }

    /**
     * Method that returns a list of meals that have already been delivered on
     * that date.
     *
     * @param date
     * @return
     */
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

    /**
     * Method that returns the statistics by dish.
     *
     * @param dish
     * @return
     */
    public Set<Integer> getRatingsByDish(Dish dish) {
        List<Meal> listMeal = getAllMealsByDish(dish);

        return getRatings(listMeal);
    }

    /**
     * Method that returns a list of meals that have already been delivered and
     * contain the dish.
     *
     * @param dish
     * @return
     */
    private List<Meal> getAllMealsByDish(Dish dish) {
        List<Meal> mealsOfDish = new ArrayList<>();
        Iterable<Meal> allMealsDelivered = getAllMealsDelivered();
        for (Meal meal : allMealsDelivered) {
            if (meal.getDish().equals(dish)) {
                mealsOfDish.add(meal);
            }
        }
        return mealsOfDish;
    }

    /**
     * Method that returns the statistics by meal Type.
     *
     * @param mealType
     * @return
     */
    public Set<Integer> getRatingsByMealType(MealType mealType) {
        List<Meal> listMeal = getAllMealsByMealType(mealType);

        return getRatings(listMeal);
    }

    /**
     * Method that returns a list of meals that have already been delivered and
     * that are of that type.
     *
     * @param MealType
     * @return
     */
    private List<Meal> getAllMealsByMealType(MealType MealType) {
        List<Meal> mealsByMealType = new ArrayList<>();
        Iterable<Meal> allMealsDelivered = getAllMealsDelivered();
        for (Meal meal : allMealsDelivered) {
            if (meal.getMealType().equals(MealType)) {
                mealsByMealType.add(meal);
            }
        }
        return mealsByMealType;
    }

    /**
     * Method that returns the statistics by menu.
     *
     * @param menu
     * @return
     */
    public Set<Integer> getRatingsByMenu(Menu menu) {
        List<Meal> listMeal = (List<Meal>) getAllMealsByMenu(menu);

        return getRatings(listMeal);
    }

    /**
     * Method that returns a list of meals that have already been delivered and
     * that are from that menu.
     *
     * @param menu
     * @return
     */
    private Iterable<Meal> getAllMealsByMenu(Menu menu) {
        return theRegisterMenuController.getAllMealsOfMenu(menu);
    }

    /**
     * Method that returns the statistics by meal.
     *
     * @param meal
     * @return
     */
    public Set<Integer> getRatingsByMeal(Meal meal) {
        Set<Integer> ratingsDate = new HashSet<>();

        Iterable<Booking> bookingOfMeal;
        int numPlaneado = 0;
        int numReservado = 0;
        int numDistribuido = 0;

        bookingOfMeal = bookingRepository.findBookingsForMeal(meal);
        for (Booking booking : bookingOfMeal) {
            if (booking.isReserved()) {
                numReservado++;
            } else {
                if (booking.isDelivered()) {
                    numReservado++;
                    numDistribuido++;
                }
            }
        }
        //falta ir buscar o numero planeado na cozinha

        ratingsDate.add(numReservado);
        ratingsDate.add(numDistribuido);
        ratingsDate.add(numPlaneado);

        return ratingsDate;
    }

    /**
     * Method that returns a set of integers with organized, reserved,
     * distributed, and planned statistics.
     *
     * @param listMeal
     * @return
     */
    private Set<Integer> getRatings(List<Meal> listMeal) {
        Set<Integer> ratingsDate = new HashSet<>();
        Iterable<Booking> bookingOfMeal;
        int numPlaneado = 0;
        int numReservado = 0;
        int numDistribuido = 0;

        for (Meal meal : listMeal) {
            bookingOfMeal = bookingRepository.findBookingsForMeal(meal);
            for (Booking booking : bookingOfMeal) {
                if (booking.isReserved()) {
                    numReservado++;
                } else {
                    if (booking.isDelivered()) {
                        numReservado++;
                        numDistribuido++;
                    }
                }
            }
            //falta ir buscar o numero planeado na cozinha
        }

        ratingsDate.add(numReservado);
        ratingsDate.add(numDistribuido);
        ratingsDate.add(numPlaneado);

        return ratingsDate;
    }

}
