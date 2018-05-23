package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.domain.kitchen.MealPlan;
import eapli.ecafeteria.domain.kitchen.MealPlanItemQuantity;
import eapli.ecafeteria.domain.menus.Menu;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.MealPlanItemQuantityRepository;
import eapli.ecafeteria.persistence.MealPlanRepository;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.framework.domain.Designation;
import java.util.List;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tiago Babo 1160760
 */
public class CreateMealPlanController {
    
    private final MealPlanRepository mealPlanRepo = PersistenceContext.repositories().mealplans();
    private final MenuRepository menuRepo = PersistenceContext.repositories().menus();
    private final MealPlanItemQuantityRepository mpiqRepo = PersistenceContext.repositories().mealplanitemquantities();
    
    /*===============STAR OF CODE FOR CREATING A NEW MEAL PLAN===============*/
    
    /**
     * Gets existing menus from DB
     * @return 
     */
    public List<Menu> getExistingMenus(){
        
        List<Menu> resultingList = (List<Menu>) menuRepo.findAll();
        
        return resultingList;
    }
    
    /**
     * Gets a Menu from a list
     * @param menuList
     * @param option
     * @return choosen Menu from the list
     */
    public Menu getMenu(List<Menu> menuList, int option){
        Menu menu = menuList.get(option);
        
        return menu;
    }
    
    /**
     * Creates new instance of MealPlan
     * @param menu
     * @return created meal plan
     */
    public MealPlan createMealPlan(Menu menu){
        
        MealPlan mPlan = new MealPlan(menu);
        
        return mPlan;
    }
    
    /**
     * Gets all the meals from a Menu
     * @param mealPlan
     * @return Set of Meals
     */
    public Set<Meal> getMealList(MealPlan mealPlan){
        return mealPlan.getMenu().getMealList();
    }
    
    /**
     * Gets the Meal date
     * @param meal
     * @return Meal date
     */
    public String getMealDate(Meal meal){
        return meal.getMealDate().toString();
    }
    
    /**
     * Gets the Dish Type of a Meal
     * @param meal
     * @return Dish type
     */
    public String getMealDishType(Meal meal){
        return meal.getDish().dishType().id();
    }
    
    /**
     * Gets the dish name
     * @param meal
     * @return dish name
     */
    public Designation getMealDishName(Meal meal){
        return meal.getDish().name();
    }
    
    /**
     * Gets meal type
     * @param meal
     * @return meal type
     */
    public String getMealType(Meal meal){
        return meal.getMealType().toString();
    }
    
    /**
     * Gets the list of MealPlanItemQuantity from the MealPlan
     * @param mealPlan
     * @return list of MealPlanItemQuantity
     */
    public List<MealPlanItemQuantity> getItemQuantityList(MealPlan mealPlan){
        return mealPlan.getItemQuantityList();
    }
    
    /**
     * Saves Meal Plan in DB
     * @param mealPlan 
     */
    public void saveMealPlan(MealPlan mealPlan){
        try {
            mealPlanRepo.save(mealPlan);
        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            Logger.getLogger(CreateMealPlanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Creates new instance of MealPlanItemQuantity
     * @param quantity
     * @param meal
     * @return new MealPlanItemQuantity
     */
    public MealPlanItemQuantity createMealPlanItemQuantity(int quantity, Meal meal){
        MealPlanItemQuantity itemQuantity = new MealPlanItemQuantity(quantity, meal);
        return itemQuantity;
    }
    
    /*===============END OF CODE FOR CREATING A NEW MEAL PLAN================*/
}
