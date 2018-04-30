package eapli.ecafeteria.domain.kitchen;

import eapli.ecafeteria.domain.menus.Menu;
import javax.persistence.*;

/**
 * @author Tiago Babo 1160760, Gonçalo Fonseca 1150503
 */
@Entity
public class MealPlan {
    
    private static final long serialVersionUID = 1L;
    
    public static HeuristicConfiguration heuristicInUse;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Menu menu;
    
    private boolean closed;

    public MealPlan(Menu menu) {
        this.menu = menu;
        this.closed = false; //no inicio esta aberto logo closed=false
    }

    public MealPlan(){}

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public Menu getMenu() {
        return menu;
    }

    public boolean isClosed() {
        return closed;
    }
    
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
}
