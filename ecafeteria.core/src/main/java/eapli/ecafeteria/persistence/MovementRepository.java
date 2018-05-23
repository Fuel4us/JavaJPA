/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.movement.Movement;
import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import eapli.framework.persistence.repositories.DataRepository;
import java.util.Calendar;

/**
 *
 * @author Hernani Gil
 */
public interface MovementRepository extends DataRepository<Movement, Long> {

    Iterable<Movement> findAllByNIF(MecanographicNumber nif);

    /**
     * finds all the movements in a certain time period belonging to a certain
     * user/mechanographic number (joao reis - 1160600)
     *
     * @param nif mechanograpic number
     * @param periodBeginning beginning of the specified time period
     * @param periodEnd end of the specified time period
     * @return all the movements in a certain time period belonging to a certain
     * user/mechanographic number
     */
    Iterable<Movement> findAllByNIFandDatePeriod(MecanographicNumber nif, Calendar periodBeginning, Calendar periodEnd);
}
