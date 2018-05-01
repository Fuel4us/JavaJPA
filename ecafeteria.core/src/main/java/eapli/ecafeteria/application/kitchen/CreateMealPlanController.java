package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.domain.kitchen.MealPlan;
import eapli.ecafeteria.domain.kitchen.MealPlanItem;
import eapli.ecafeteria.domain.kitchen.MealPlanItemQuantity;
import eapli.ecafeteria.domain.menus.Menu;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.MealPlanItemQuantityRepository;
import eapli.ecafeteria.persistence.MealPlanItemRepository;
import eapli.ecafeteria.persistence.MealPlanRepository;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.framework.domain.Designation;
import java.util.List;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tiago Babo 1160760
 */
public class CreateMealPlanController {
    
    private final MealPlanRepository mealPlanRepo = PersistenceContext.repositories().mealplans();
    private final MenuRepository menuRepo = PersistenceContext.repositories().menus();
    private final MealPlanItemRepository mpiRepo = PersistenceContext.repositories().mealplanitems();
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
    
    public MealPlanItemQuantity createItemQuantity(int quantity, MealPlanItem item){
        MealPlanItemQuantity itemQuantity = new MealPlanItemQuantity(quantity, item);
        
        return itemQuantity;
    }
    
    public MealPlanItem createPlanItem(Meal meal, MealPlan mealPlan){
        MealPlanItem item = new MealPlanItem(meal, mealPlan);
        
        return item;
    }
    
    public void saveMealPlan(MealPlan mealPlan){
        try {
            mealPlanRepo.save(mealPlan);
        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            Logger.getLogger(CreateMealPlanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void saveMealPlanItem(MealPlanItem mpItem){
        try {
            mpiRepo.save(mpItem);
        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            Logger.getLogger(CreateMealPlanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void saveMealPlanItemQuantity(MealPlanItemQuantity mpItemQuantity){
        try {
            mpiqRepo.save(mpItemQuantity);
        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            Logger.getLogger(CreateMealPlanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /*===============END OF CODE FOR CREATING A NEW MEAL PLAN================*/
}
