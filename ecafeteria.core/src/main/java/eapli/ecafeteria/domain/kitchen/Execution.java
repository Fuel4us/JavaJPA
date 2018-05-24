/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.kitchen;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.domain.ddd.AggregateRoot;
import jdk.nashorn.internal.ir.annotations.Immutable;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;

/**
 *
 * @author Carlos Figueiredo (1140317)
 */
@Entity
public class Execution implements AggregateRoot<Integer>, Serializable {

    private static final long serialVersionUID = 1L;

    //ORM primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pk;

    //Business id
    private int cookedMeals;


    private Leftover leftover;

    private Picked picked;

    private NotPicked notPicked;

    @OneToOne
    private Meal meal;

    //for ORM
    protected Execution() {
this.leftover=new Leftover();
    }

    public Execution(Meal meal,int cookedMeals) {
        this.cookedMeals = cookedMeals;
        this.meal=meal;
        this.leftover=new Leftover();
        this.picked= new Picked(0);
        this.notPicked= new NotPicked(0);
    }

    public Picked getPicked() {
        return picked;
    }

    public void setPicked(Picked picked) {
        this.picked = picked;
    }

    public NotPicked getNotPicked() {
        return notPicked;
    }

    public void setNotPicked(NotPicked notPicked) {
        this.notPicked = notPicked;
    }

    public Meal getMeal() {
        return meal;
    }

    public int getCookedMeals() {
        return cookedMeals;
    }

    public Leftover getLeftover(){
        return this.leftover;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Execution execution = (Execution) o;
        return cookedMeals == execution.cookedMeals &&
                Objects.equals(pk, execution.pk) &&
                Objects.equals(leftover, execution.leftover) &&
                Objects.equals(picked, execution.picked) &&
                Objects.equals(notPicked, execution.notPicked) &&
                Objects.equals(meal, execution.meal);
    }

    @Override
    public int hashCode() {

        return Objects.hash(pk, cookedMeals, leftover, picked, notPicked, meal);
    }

    @Override
    public Integer id() {
        return this.cookedMeals;
    }

    @Override
    public boolean is(Integer otherId) {
        return Objects.equals(id(), otherId);
    }

    @Override
    public boolean sameAs(Object other) {
        //Implementation needed
        return false;
    }

    @Override
    public String toString() {
        return "Execution{" + "ID: " + pk + "cookedMeals=" + cookedMeals + '}';
    }

}
