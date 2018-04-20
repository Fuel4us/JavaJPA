package eapli.ecafeteria.domain.kitchen;

import eapli.ecafeteria.domain.meals.Meal;

import java.util.Date;

/**
 * @author Gonçalo Fonseca <1150503@isep.ipp.pt>
 */
public class MealPlan {

    private int id;
    private Meal meal;
    private Date initialDate;
    private Date finalDate;

    public MealPlan(Meal meal, Date initialDate, Date finalDate) {
        this.meal = meal;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
    }

    @Override
    public String toString() {
        return "MealPlan{" +
                "id=" + id +
                ", meal=" + meal +
                ", initialDate=" + initialDate +
                ", finalDate=" + finalDate +
                '}';
    }
}
