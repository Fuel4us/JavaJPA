/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.kitchen.LimitConfiguration;
import eapli.ecafeteria.persistence.KitchenLimitRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;

/**
 *
 * @author 1160629@isep.ipp.pt
 */
public class InMemoryKitchenLimitRepository extends InMemoryRepository<LimitConfiguration, Long> implements KitchenLimitRepository {

    @Override
    protected Long newKeyFor(LimitConfiguration entity) {
        return entity.id();
    }
    
    @Override
    public LimitConfiguration first() {
        return findAll().iterator().next();
    }
}
