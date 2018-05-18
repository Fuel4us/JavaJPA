/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.movement.Movement;
import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import eapli.ecafeteria.persistence.MovementRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepositoryWithLongPK;
import java.util.Calendar;

/**
 *
 * @author Hernani Gil
 */
public class InMemoryMovementRepository extends InMemoryRepositoryWithLongPK<Movement> implements MovementRepository {

    @Override
    public Iterable<Movement> findAllByNIF(MecanographicNumber nif) {
        return match( e -> e.nif().number().equals(nif.number()));
    }

    @Override
    public Iterable<Movement> findAllByNIFandDatePeriod(MecanographicNumber nif, Calendar periodBeginning, Calendar periodEnd) {
        return match(e ->e.nif().equals(nif) && e.date().after(periodBeginning) && e.date().before(periodEnd));
    }
    
}
