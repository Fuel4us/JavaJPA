/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafetaria.domain.movement.Movement;
import eapli.framework.persistence.repositories.DataRepository;

/**
 *
 * @author Hernani Gil
 */
public interface MovementRepository extends DataRepository<Movement, Long>{
    
}