/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.kitchen;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.domain.ddd.AggregateRoot;
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
public class MealLot implements AggregateRoot<Integer>,Serializable {

    private static final long serialVersionUID = 1L;

    //ORM primary key
    @Id
    @GeneratedValue
    private Long pk;

    @ManyToOne
    private Meal meal;
    @ManyToOne
    private Lot lot;
    private int quantityUsed;

    protected MealLot() {
        //for ORM
    }

    public MealLot(Meal meal, Lot lot, int quantityUsed) {
        this.meal = meal;
        this.lot = lot;
        this.quantityUsed = quantityUsed;
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

    @Override
    public boolean sameAs(Object other) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean is(Integer otherId) {
        return AggregateRoot.super.is(otherId); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer id() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
