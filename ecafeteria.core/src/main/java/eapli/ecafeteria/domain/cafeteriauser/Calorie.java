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
    
    int maxCalorieQuantity;
    
    public Calorie(int maxCalorieQuantity){
        if(maxCalorieQuantity < 0){
            throw new IllegalArgumentException("Max Calorie quantity should be a positive integer.");
        }
        
        this.maxCalorieQuantity = maxCalorieQuantity;
    }

    public int getMaxCalorieQuantity() {
        return maxCalorieQuantity;
    }
    
    public void setMaxCalorieQuantity(int maxCalorieQuantity) {
        this.maxCalorieQuantity = maxCalorieQuantity;
    }
}
