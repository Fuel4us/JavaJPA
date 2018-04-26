package eapli.ecafeteria.domain.kitchen;

import eapli.ecafeteria.domain.menus.Menu;
import java.util.List;
import javax.persistence.*;

/**
 * @author Tiago Babo 1160760
 */
@Entity
public class MealPlan {
    
    public static Heuristic heuristicInUse;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Menu menu;
    private List<Integer> numberOfDishes;

    public MealPlan(Menu menu, List<Integer> numberOfDishes) {
        this.menu = menu;
        this.numberOfDishes = numberOfDishes;
    }

    protected MealPlan(){}

    public Menu getMenu() {
        return menu;
    }

    public List<Integer> getNumberOfDishes() {
        return numberOfDishes;
    }
    
    public static boolean changeHeuristicInUse(Heuristic newHeuristic){
        if(newHeuristic == null)
            return false;
        
        heuristicInUse = newHeuristic;
        return true;
    }
}
