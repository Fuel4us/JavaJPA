/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.kitchen.Lot;
import eapli.ecafeteria.domain.kitchen.MealLot;
import eapli.framework.persistence.repositories.DataRepository;

/**
 *
 * @author Pedro Rodrigues (1140572)
 */
public interface MealLotRepository extends DataRepository<MealLot, Long>{
    
    Iterable<MealLot> findAllByLot(Lot lot);
}
