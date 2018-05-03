/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.administration;

import eapli.ecafeteria.domain.kitchen.LimitConfiguration;
import eapli.ecafeteria.persistence.KitchenLimitRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 1160629@isep.ipp.pt
 */
public class ConfigureKitchenAlarmLimitsController implements Controller{
    
    private final KitchenLimitRepository kitchenLimitRepository = PersistenceContext.repositories().kitchenLimit();
    
    //There is only one LimitConfiguration..?
    LimitConfiguration limitConfig = kitchenLimitRepository.first();
    
    public boolean configureYellowLimit(double limitValue){
        return limitConfig.configureYellowLimit(limitValue);
    }
    
    public boolean configureRedLimit(double limitValue){
        return limitConfig.configureRedLimit(limitValue);
    }
    
    public void save(){
        try {
            kitchenLimitRepository.save(limitConfig);
        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            Logger.getLogger(ConfigureKitchenAlarmLimitsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
