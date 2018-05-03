/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.finance.POSUser;
import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import eapli.framework.persistence.repositories.DataRepository;
import java.util.Optional;

/**
 *
 * @author Hernani Gil
 */
public interface POSUserRepository extends DataRepository<POSUser, Long>{
    /**
     * returns the cafeteria user (utente) whose username is given
     *
     * @param name the username to search for
     * @return
     */
    Optional<POSUser> findByUsername(Username name);

    /**
     * returns the cafeteria user (utente) with the given mecanographic number
     *
     * @param number
     * @return
     */
    Optional<POSUser> findByMecanographicNumber(MecanographicNumber number);

    public Iterable<POSUser> findAllActive();
}
