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
import javax.persistence.OneToOne;

/**
 *
 * @author Pedro Rodrigues (1140572)
 */
@Entity
public class Lot implements AggregateRoot<Integer>, Serializable {

    private static final long serialVersionUID = 1L;

    //ORM primary key
    @Id
    @GeneratedValue
    private Long pk;

    //Business id
    @Column(unique = true)
    private int lotCode;
    @OneToOne
    private Material ingredient;
    private int quantity;
    
    protected Lot() {
        //for ORM
    }

    public Lot(int lotCode, Material ingredient, int quantity) {
        this.lotCode = lotCode;
        this.ingredient = ingredient;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    /**
     * @return the ingredientCode
     */
    public Material getIngredient() {
        return ingredient;
    }

    public void changeQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public Integer id() {
        return this.lotCode;
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Lot)) {
            return false;
        }

        final Lot other = (Lot) o;
        return Objects.equals(id(), other.id());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.lotCode;
        return hash;
    }

    @Override
    public String toString() {
        return "MealLot { " + "ID: " + pk + ", Lot Code: " + lotCode + ", Ingredient: " + getIngredient() + ", Quantity: " + quantity  + '}' + "\n";
    }

    public String toString2() {
        return "Lot Code: " + lotCode + "\nMaterial: " + getIngredient().description() + "\nQuantity: " + quantity;
    }
    
    public String toString3(){
        return "Material: " + getIngredient().description() + "\nQuantity: " + quantity +"\n";
    }
}
