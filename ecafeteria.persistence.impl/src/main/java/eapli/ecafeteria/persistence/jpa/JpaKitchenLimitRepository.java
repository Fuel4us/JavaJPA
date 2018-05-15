/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.kitchen.LimitConfiguration;
import eapli.ecafeteria.persistence.KitchenLimitRepository;
import eapli.framework.persistence.repositories.impl.jpa.JpaAutoTxRepository;

/**
 *
 * @author 1160629@isep.ipp.pt
 */
public class JpaKitchenLimitRepository extends JpaAutoTxRepository<LimitConfiguration, Long> implements KitchenLimitRepository {
    
    public JpaKitchenLimitRepository(String persistenceUnitName) {
        super(persistenceUnitName);
    }

    @Override
    public LimitConfiguration first() {
        return findAll().iterator().next();
    }
    
}
