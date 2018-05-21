/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.cafeteriauser;

import eapli.framework.domain.ddd.ValueObject;
import javax.persistence.Embeddable;

/**
 *
 * @author Tiago Babo (1160760)
 */
@Embeddable
public class Salt implements ValueObject{
    
    private static final long serialVersionUID = 1L;
    
    /**
     * Max salt quantity
     */
    int maxSaltQuantity;
    
    /**
     * Main contructor for class
     * @param maxSaltQuantity 
     */
    public Salt(int maxSaltQuantity){
        if(maxSaltQuantity < 0){
            throw new IllegalArgumentException("Max Salt quantity should be a positive integer.");
        }
        
        this.maxSaltQuantity = maxSaltQuantity;
    }
    
    /**
     * Empty constructor for ORM
     */
    protected Salt(){
        maxSaltQuantity = -1;
    }
    
    /**
     * Gets the max salt quantity
     * @return max salt quantity
     */
    public int getMaxSaltQuantity() {
        return maxSaltQuantity;
    }
    
    /**
     * Sets the max salt quantity
     * @param maxSaltQuantity 
     */
    public void setMaxSaltQuantity(int maxSaltQuantity) {
        this.maxSaltQuantity = maxSaltQuantity;
    }
}
