package eapli.ecafeteria.domain.kitchen;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import eapli.ecafeteria.domain.menus.Menu;
import eapli.framework.domain.ddd.AggregateRoot;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 * @author Tiago Babo 1160760, Gonçalo Fonseca 1150503
 */
@Entity
public class MealPlan implements AggregateRoot<Long>{
    
    private static final long serialVersionUID = 1L;
    
    /**
     * Heuristic used
     */
    public static HeuristicConfiguration heuristicInUse;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Menu of the meal plan
     */
    @ManyToOne
    private Menu menu;
    
    /**
     * List of MealPlanItemQuantity
     */
    @ManyToMany(cascade = CascadeType.ALL)
    private List<MealPlanItemQuantity> itemQuantityList;
    
    /**
     * State of the meal plan
     */
    private boolean closed;
    
    /**
     * Main constructor of class
     * @param menu 
     */
    public MealPlan(Menu menu) {
        this.menu = menu;
        this.closed = false; //no inicio esta aberto logo closed=false
        this.itemQuantityList = new ArrayList<>();
    }
    
    /**
     * Empty constructor
     */
    public MealPlan(){}
    
    /**
     * Sets the state of the meal plan
     * @param closed 
     */
    public void setClosed(boolean closed) {
        this.closed = closed;
    }
    
    /**
     * Gets the menu of the meal plan
     * @return menu of the meal plan
     */
    public Menu getMenu() {
        return menu;
    }
    
    /**
     * Checks if the meal plan is closed
     * @return true - if closed; false - if not closed
     */
    public boolean isClosed() {
        return closed;
    }
    
    /**
     * Sets the list of Item Quantity
     * @param itemQuantityList 
     */
    public void setDishNumber(List<MealPlanItemQuantity> itemQuantityList){
        this.itemQuantityList = itemQuantityList;
    }
    
    /**
     * Gets the list of Item Quantity
     * @return 
     */
    public List<MealPlanItemQuantity> getItemQuantityList(){
        return itemQuantityList;
    }
    
    /**
     * Changes heuristic
     * @param newHeuristic
     * @return true - if changed; false - if not changed
     */
    public static boolean changeHeuristicInUse(HeuristicConfiguration newHeuristic){
        if(newHeuristic == null)
            return false;
        
        heuristicInUse = newHeuristic;
        return true;
    }
    
    /**
     * Changes state to get it ready for the day.
     * @param meal
     */
    public void changeState(MealPlan meal){
        meal.setClosed(true);
    }

    @Override
    public String toString() {
        return "MealPlan{" +
                ", menu=" + menu +
                ", closed=" + closed +
                '}';
    }

    @Override
    public boolean sameAs(Object other) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Returns meal plan id
     * @return meal plan id
     */
    @Override
    public Long id() {
        return this.id;
    }
}
