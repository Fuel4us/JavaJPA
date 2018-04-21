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

    public MealPlan(Menu menu, List<Integer> numberOfDishes) {
        this.menu = menu;
        this.numberOfDishes = numberOfDishes;
    }

    protected MealPlan(){}
}
