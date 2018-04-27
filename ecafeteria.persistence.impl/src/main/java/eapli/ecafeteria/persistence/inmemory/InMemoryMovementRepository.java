/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafetaria.domain.movement.Movement;
import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import eapli.ecafeteria.persistence.MovementRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepositoryWithLongPK;
import java.util.Optional;

/**
 *
 * @author Hernani Gil
 */
public class InMemoryMovementRepository extends InMemoryRepositoryWithLongPK<Movement> implements MovementRepository {

    @Override
    public Iterable<Movement> findAllByNIF(MecanographicNumber nif) {
        return match( e -> e.nif().number().equals(nif.number()));
    }
    
}
