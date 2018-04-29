package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.domain.kitchen.MealPlan;
import eapli.ecafeteria.domain.menus.Menu;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.MealPlanRepository;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.framework.domain.Designation;
import java.util.ArrayList;
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
    
    /*===============STAR OF CODE FOR CREATING A NEW MEAL PLAN===============*/
    
    public List<Menu> getExistingMenus(){
        
        List<Menu> resultingList = (List<Menu>) menuRepo.findAll();
        
        return resultingList;
    }
    
    public Menu getMenu(List<Menu> menuList, Integer opcao){
        Menu menu = menuList.get(opcao);
        
        return menu;
    }
    
    public MealPlan createMealPlan(Menu menu){
        List<Integer> numberDishes = new ArrayList<>();
        
        MealPlan mPlan = new MealPlan(menu, numberDishes);
        
        return mPlan;
    }
    
    public void setDishQuantity(MealPlan mealPlan, Integer numberOfDishes){
        mealPlan.getNumberOfDishes().add(numberOfDishes);
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
    
    public void saveMealPlan(MealPlan mealPlan){
        try {
            mealPlanRepo.save(mealPlan);
        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            Logger.getLogger(CreateMealPlanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*===============END OF CODE FOR CREATING A NEW MEAL PLAN================*/
    
//    /*=============STAR OF CODE FOR EDITING AN EXISTING MEAL PLAN============*/
//    
//    public List<MealPlan> getExistingMealPlan(){
//        List<MealPlan> resultingList = (List<MealPlan>) mealPlanRepo.findAll();
//        
//        return resultingList;
//    }
//    
//    public MealPlan getMealPlan(List<MealPlan> mealPlanList, Integer opcao){
//        MealPlan mealPlan = mealPlanList.get(opcao);
//        
//        return mealPlan;
//    }
//    
//    public void cleanNumberOfDishes(MealPlan mealPlan){
//        mealPlan.cleanNumberOfDishes();
//    }
//    
//    /*=============END OF CODE FOR EDITING AN EXISTING MEAL PLAN=============*/
}
