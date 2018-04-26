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
public class Settings {
    LimitConfiguration limitConfig;
    
    public Settings() {
        limitConfig = new LimitConfiguration();
    }
    
    public boolean configureYellowLimit(long limitValue){
        return limitConfig.configureYellowLimit(limitValue);
    }
    
    public boolean configureRedLimit(long limitValue){
        return limitConfig.configureRedLimit(limitValue);
    }
}
