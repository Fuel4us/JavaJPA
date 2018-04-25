package eapli.ecafeteria.domain.kitchen;

import eapli.ecafeteria.domain.menus.Menu;
import java.util.List;
import javax.persistence.*;

/**
 * @author Tiago Babo 1160760
 */
@Entity
public class MealPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Menu menu;
    private List<Integer> numberOfDishes;
    private boolean closed;

    public MealPlan(Menu menu, List<Integer> numberOfDishes, boolean closed) {
        this.menu = menu;
        this.numberOfDishes = numberOfDishes;
        this.closed = closed;
    }

    protected MealPlan(){}

    public Menu getMenu() {
        return menu;
    }

    public List<Integer> getNumberOfDishes() {
        return numberOfDishes;
    }

    public boolean isClosed() {
        return closed;
    }
    
    public boolean toogleState(){
        this.closed = !this.closed;
        
        return isClosed();
    }
}
