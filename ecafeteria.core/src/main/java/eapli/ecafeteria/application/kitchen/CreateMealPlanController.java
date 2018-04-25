package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.domain.kitchen.MealPlan;
import eapli.ecafeteria.domain.menus.Menu;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.domain.Designation;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Tiago Babo 1160760
 */
public class CreateMealPlanController {
    
    public List<Menu> getExistingMenus(){
        
        //DEVERA TER DE FAZER ALGUMA COISA COM A BASE DE DADOS PARA OBTER OS MENUS
        
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("mealplan");
        EntityManager manager = factory.createEntityManager();
        
        Query query = manager.createQuery("SELECT m FROM Menu m");
        
        List<Menu> resultingList = query.getResultList();
        
        return resultingList;
    }
    
    public Menu getMenu(List<Menu> menuList, Integer opcao){
        Menu menu = menuList.get(opcao);
        
        return menu;
    }
    
    public MealPlan createMealPlan(Menu menu){
        List<Integer> numberDishes = new ArrayList<>();
        
        MealPlan mPlan = new MealPlan(menu, numberDishes); //false - não está fechado quando criado
        
        return mPlan;
    }
    
    public void setDishQuantity(MealPlan mealPlan, Integer numberOfDishes){
        mealPlan.getNumberOfDishes().add(numberOfDishes);
    }
    
    public String getMealDate(Meal meal){
        return meal.getDate().toString();
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
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("mealplan");
        EntityManager manager = factory.createEntityManager();
        
        MealPlan mPlan = mealPlan;
        
        manager.getTransaction().begin();
        manager.persist(mPlan);
        manager.getTransaction().commit();
        manager.close();
    }
}
