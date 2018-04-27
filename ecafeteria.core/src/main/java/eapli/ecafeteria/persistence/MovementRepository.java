/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.movement.Movement;
import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import eapli.framework.persistence.repositories.DataRepository;
import java.util.Optional;

/**
 *
 * @author Hernani Gil
 */
public interface MovementRepository extends DataRepository<Movement, Long>{
    
    Iterable<Movement> findAllByNIF(MecanographicNumber nif);
}
