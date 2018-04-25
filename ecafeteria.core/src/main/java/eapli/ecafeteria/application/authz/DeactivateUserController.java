/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.application.authz;

import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.authz.Reason;
import eapli.ecafeteria.domain.authz.ReasonType;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.ReasonRepository;
import eapli.ecafeteria.persistence.UserRepository;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.util.DateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fernando
 */
public class DeactivateUserController implements Controller {

//    private final UserRepository userRepository = PersistenceContext.repositories().users(null);
    private final CafeteriaUserRepository cafetariaUserRepository = PersistenceContext.repositories().cafeteriaUsers();

//    public Iterable<SystemUser> activeUsers() {
    public Iterable<CafeteriaUser> activeUsers() {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.ADMINISTER);

//        return this.userRepository.findAll();
//        return this.cafetariaUserRepository.findAll();
          return this.cafetariaUserRepository.findAllActive();
    }
    
    public ReasonType[] reasons(){
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.ADMINISTER);
        return ReasonType.values();
    }

//    public SystemUser deactivateUser(SystemUser user, ReasonType rType, String comment) throws DataConcurrencyException, DataIntegrityViolationException {
    public CafeteriaUser deactivateUser(CafeteriaUser user, ReasonType rType, String comment) throws DataConcurrencyException, DataIntegrityViolationException {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.ADMINISTER);

        try{
//            user.deactivate(DateTime.now(), rType, comment);
            user.user().deactivate(DateTime.now(), rType, comment);
        } catch (IllegalArgumentException arg_ex){
            Logger.getLogger(DeactivateUserController.class.getName()).log(Level.SEVERE, null, arg_ex);
        }
        
//        return this.userRepository.save(user);
        return this.cafetariaUserRepository.save(user);
    }
}
