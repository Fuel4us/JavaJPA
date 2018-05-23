/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.kitchen;

import eapli.ecafeteria.domain.meals.Meal;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Tiago Babo 1160760
 */
@Entity
public class MealPlanItemQuantity {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    /**
     * Quantity of the item
     */
    private int itemQuantity;
    
    /**
     * Meal associated to the item
     */
    @OneToOne
    private Meal meal;
    
    /**
     * Main constructor of class
     * @param itemQuantity
     * @param meal 
     */
    public MealPlanItemQuantity(int itemQuantity, Meal meal){
        this.itemQuantity = itemQuantity;
        this.meal = meal;
    }
    
    /**
     * Empty constructor
     */
    public MealPlanItemQuantity(){}
    
    /**
     * Gets the quantity of the item
     * @return 
     */
    public int getItemQuantity() {
        return itemQuantity;
    }
    
    /**
     * Gets the Meal of that item
     * @return meal of that item
     */
    public Meal getMeal() {
        return meal;
    }
}
