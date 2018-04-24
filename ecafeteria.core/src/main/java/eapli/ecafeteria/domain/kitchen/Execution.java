/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.kitchen;

import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Carlos Figueiredo (1140317)
 */
@Entity
public class Execution implements AggregateRoot<Integer>, Serializable {

    private static final long serialVersionUID = 1L;

    //ORM primary key
    @Id
    @GeneratedValue
    private Long pk;

    //Business id
    @Column(unique = true)
    private int cookedMeals;

    //for ORM
    protected Execution() {

    }

    public Execution(int cookedMeals) {
        this.cookedMeals = cookedMeals;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.cookedMeals;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Execution other = (Execution) obj;
        if (this.cookedMeals != other.cookedMeals) {
            return false;
        }
        return true;
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
        return "Execution{" + "cookedMeals=" + cookedMeals + '}';
    }

}
