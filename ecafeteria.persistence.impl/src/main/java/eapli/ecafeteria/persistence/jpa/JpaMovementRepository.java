/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.movement.Movement;
import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import eapli.ecafeteria.persistence.MovementRepository;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Hernani Gil
 */
public class JpaMovementRepository extends CafeteriaJpaRepositoryBase<Movement, Long> implements MovementRepository{

    @Override
    public Iterable<Movement> findAllByNIF(MecanographicNumber nif) {
        final Map<String, Object> params = new HashMap<>();
        params.put("nif", nif);

        return match("e.nif = :nif", params);
    }

    @Override
    public Iterable<Movement> findAllByNIFandDatePeriod(MecanographicNumber nif, Calendar periodBeginning, Calendar periodEnd) {
        final Map<String, Object> params = new HashMap<>();
        params.put("nif", nif);
        params.put("beg", periodBeginning);
        params.put("end", periodEnd);

        return match("e.nif = :nif and e.date >= :beg and e.date <= :end", params);
    }
}
