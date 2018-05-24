/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.movement.Movement;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.MovementRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import java.util.Calendar;
import java.util.Optional;

/**
 *
 * @author joao reis (1160600)
 */
public class CheckMovementsController {
    private MovementRepository movRep = PersistenceContext.repositories().movement();
    private CafeteriaUserRepository cafRep = PersistenceContext.repositories().cafeteriaUsers();
    
    
    public Iterable<Movement>showMovements(Calendar periodBeginning) {
       
        Optional<CafeteriaUser> user = cafRep.findBySystemUser(AuthorizationService.session().authenticatedUser());
        CafeteriaUser cafUser;
        if(user.isPresent()) {
            cafUser = user.get();
        } else {
            throw new NullPointerException("User is not a cafeteria user.");
        }
        Calendar periodEnd = Calendar.getInstance();
        return movRep.findAllByNIFandDatePeriod(cafUser.mecanographicNumber(), periodBeginning, periodEnd);
    }
}
