package eapli.ecafeteria.domain.kitchen;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.presentation.console.Menu;
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

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Menu menu;
    private List<Integer> numberOfDishes;

    public MealPlan(Menu menu, List<Integer> numberOfDishes) {
        this.menu = menu;
        this.numberOfDishes = numberOfDishes;
    }

    protected MealPlan(){}
}
