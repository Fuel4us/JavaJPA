/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.kitchen;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Embeddable;

/**
 *
 * @author 1160629@isep.ipp.pt
 */
@Embeddable
public class Limit implements Serializable{

    private double limitValue;
    
    protected Limit(){
        
    }

    /**
     * Initializes limit with value if appropriate;
     * @param limitValue value to initialize object with
     * @throws java.lang.Exception
     */
    public Limit(long limitValue) throws Exception{
        configureLimitValue(limitValue);
    }
    
    public double getLimitValue() {
        return limitValue;
    }

    // throws checked exception to force validation of value
    public final void configureLimitValue(double limitValue) throws Exception {
        BigDecimal value = BigDecimal.valueOf(limitValue);
        if(value.compareTo(BigDecimal.valueOf(0))>=0 && value.compareTo(BigDecimal.valueOf(100))<=0){
            this.limitValue=limitValue;
        } else {
            throw new Exception("Invalid limit value. Limit must be between 0 and 100");
        }
   }
}
