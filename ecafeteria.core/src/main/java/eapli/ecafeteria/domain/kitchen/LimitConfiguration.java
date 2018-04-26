/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.kitchen;

/**
 *
 * @author 1160629@isep.ipp.pt
 */
public class LimitConfiguration {
    private Limit yellowLimit;
    private Limit redLimit;
    public LimitConfiguration(Limit yellowLimit, Limit redLimit){
        
    }
    
    /**
     * Initializes limits with default values
     */
    public LimitConfiguration() {
        try {
            yellowLimit = new Limit(50);
            redLimit = new Limit(70);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public boolean configureYellowLimit(long limitValue){
        return configureLimit(yellowLimit, limitValue);
    }
    
    public boolean configureRedLimit(long limitValue){
        return configureLimit(redLimit, limitValue);
    }
    
    private boolean configureLimit(Limit limit, long limitValue){
        try{
            limit.configureLimitValue(limitValue);
            return true;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
