package eapli.ecafeteria.domain.kitchen;

import eapli.ecafeteria.domain.meals.Meal;

import javax.persistence.*;

/**
 * @author Gonçalo Fonseca <1150503@isep.ipp.pt>
 */
@Entity
public class MealPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Meal meal;
    private int dishNumber;

    public MealPlan(Meal meal, int dishNumber) {
        this.meal = meal;
        this.dishNumber = dishNumber;
    }

    protected MealPlan() {}

    @Override
    public String toString() {
        return "MealPlan{" +
                "id=" + id +
                ", meal=" + meal +
                ", dishnumber=" + dishNumber +
                '}';
    }
}
