/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.kitchen;

import eapli.framework.util.Math;

/**
 *
 * @author 1160629@isep.ipp.pt
 */
public final class Limit {

    private long limitValue;

    /**
     * Initializes limit with value if appropriate;
     * @param limitValue value to initialize object with
     * @throws java.lang.Exception
     */
    public Limit(long limitValue) throws Exception{
        configureLimitValue(limitValue);
    }
    
    public long getLimitValue() {
        return limitValue;
    }

    // throws checked exception to force validation of value
    public final void configureLimitValue(long limitValue) throws Exception {
        if(Math.between(limitValue, 0, 100)){
            this.limitValue=limitValue;
        } else {
            throw new Exception("Invalid limit value. Limit must be between 0 and 100");
        }
   }
}
