/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.kitchen;

import eapli.ecafeteria.domain.meals.Meal;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Pedro Rodrigues (1140572)
 */
@Entity
public class MealLot implements Serializable {

    private static final long serialVersionUID = 1L;

    //ORM primary key
    @Id
    @GeneratedValue
    private Long pk;

    @ManyToOne
    private Meal meal;
    @ManyToOne
    private Lot lot;

    protected MealLot() {
        //for ORM
    }

    public MealLot(Meal meal, Lot lot) {
        this.meal = meal;
        this.lot = lot;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public Lot getLot() {
        return lot;
    }

    public void setLot(Lot lot) {
        this.lot = lot;
    }

    @Override
    public String toString() {
        return "MealLot{" + "ID=" + pk + ", Meal=" + meal + ", Lot=" + lot + '}';
    }

}
