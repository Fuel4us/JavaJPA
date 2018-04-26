/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.kitchen.Lot;
import eapli.ecafeteria.persistence.LotRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepositoryWithLongPK;

/**
 *
 * @author Pedro Rodrigues (1140572)
 */
public class InMemoryMealLotRepository extends InMemoryRepositoryWithLongPK<Lot> implements LotRepository{
    
}
