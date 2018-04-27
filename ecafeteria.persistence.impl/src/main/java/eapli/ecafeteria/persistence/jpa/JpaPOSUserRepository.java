/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.finance.POSUser;
import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import eapli.ecafeteria.persistence.POSUserRepository;
import java.util.Optional;

/**
 *
 * @author Hernani Gil
 */
public class JpaPOSUserRepository extends CafeteriaJpaRepositoryBase<POSUser, Long> implements POSUserRepository{

    @Override
    public Optional<POSUser> findByUsername(Username name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Optional<POSUser> findByMecanographicNumber(MecanographicNumber number) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterable<POSUser> findAllActive() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
