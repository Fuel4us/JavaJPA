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
    
    public List<Menu> getExistingMenus(){
        
        List<Menu> resultingList = (List<Menu>) menuRepo.findAll();
        
        return resultingList;
    }
    
    public Menu getMenu(List<Menu> menuList, int opcao){
        Menu menu = menuList.get(opcao);
        
        return menu;
    }
    
    public MealPlan createMealPlan(Menu menu){
        
        MealPlan mPlan = new MealPlan(menu);
        
        return mPlan;
    }
    
    public Set<Meal> getMealList(MealPlan mealPlan){
        return mealPlan.getMenu().getMealList();
    }
    
    public String getMealDate(Meal meal){
        return meal.getMealDate().toString();
    }
    
    public String getMealDishType(Meal meal){
        return meal.getDish().dishType().id();
    }
    
    public Designation getMealDishName(Meal meal){
        return meal.getDish().name();
    }
    
    public String getMealType(Meal meal){
        return meal.getMealType().toString();
    }
    
    public List<MealPlanItemQuantity> getItemQuantityList(MealPlan mealPlan){
        return mealPlan.getItemQuantityList();
    }
    
    public void saveMealPlan(MealPlan mealPlan){
        try {
            mealPlanRepo.save(mealPlan);
        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            Logger.getLogger(CreateMealPlanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public MealPlanItemQuantity createMealPlanItemQuantity(int quantity, Meal meal){
        MealPlanItemQuantity itemQuantity = new MealPlanItemQuantity(quantity, meal);
        return itemQuantity;
    }
    
    /*===============END OF CODE FOR CREATING A NEW MEAL PLAN================*/
}
