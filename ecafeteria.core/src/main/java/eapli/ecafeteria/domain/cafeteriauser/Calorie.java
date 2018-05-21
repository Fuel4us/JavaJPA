/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.cafeteriauser;

import eapli.framework.domain.ddd.ValueObject;

/**
 *
 * @author Tiago Babo (1160760)
 */
public class Calorie implements ValueObject{
    
    private static final long serialVersionUID = 1L;
    
    /**
     * Max calorie quantity
     */
    int maxCalorieQuantity;
    
    /**
     * Main contructor for class
     * @param maxCalorieQuantity 
     */
    public Calorie(int maxCalorieQuantity){
        if(maxCalorieQuantity < 0){
            throw new IllegalArgumentException("Max Calorie quantity should be a positive integer.");
        }
        
        this.maxCalorieQuantity = maxCalorieQuantity;
    }

    /**
     * Gets the max calorie quantity
     * @return max calorie quantity
     */
    public int getMaxCalorieQuantity() {
        return maxCalorieQuantity;
    }
    
    /**
     * Sets the max calorie quantity
     * @param maxCalorieQuantity 
     */
    public void setMaxCalorieQuantity(int maxCalorieQuantity) {
        this.maxCalorieQuantity = maxCalorieQuantity;
    }
}
