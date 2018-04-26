package eapli.ecafeteria.domain.kitchen;

import eapli.ecafeteria.domain.menus.Menu;
import java.util.List;
import javax.persistence.*;

/**
 * @author Tiago Babo 1160760
 */
@Entity
public class MealPlan {
    
    public static HeuristicConfiguration heuristicInUse;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Menu menu;
    private List<Integer> numberOfDishes;
    private boolean closed;

    public MealPlan(Menu menu, List<Integer> numberOfDishes) {
        this.menu = menu;
        this.numberOfDishes = numberOfDishes;
        this.closed = false; //no inicio esta aberto logo closed=false
    }

    protected MealPlan(){}

    public Menu getMenu() {
        return menu;
    }

    public List<Integer> getNumberOfDishes() {
        return numberOfDishes;
    }
    
    public static boolean changeHeuristicInUse(HeuristicConfiguration newHeuristic){
        if(newHeuristic == null)
            return false;
        
        heuristicInUse = newHeuristic;
        return true;
    }
}
