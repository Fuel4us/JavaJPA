/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.administration;

import eapli.ecafeteria.persistence.KitchenLimitRepository;
import eapli.ecafeteria.persistence.PersistenceContext;

/**
 *
 * @author Pedro Rodrigues (1140572)
 */
public class KitchenLimitsServices {

    private final KitchenLimitRepository kitchenLimitsRepo = PersistenceContext.repositories().kitchenLimit();

    public KitchenLimitsServices() {

    }
    
    public double findYellowLimit() {
        return kitchenLimitsRepo.first().getYellowLimit().getLimitValue();
    }
    
    public double findRedLimit() {
        return kitchenLimitsRepo.first().getRedLimit().getLimitValue();
    }
}
