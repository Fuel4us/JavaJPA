/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.cafeteriauser;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leandro
 */
public class DefineUserBalancelimitsController implements Controller{
    
    /**
     * Cafeteria user repository
     */
    private final CafeteriaUserRepository cafeteriaUserRepo = PersistenceContext.repositories().cafeteriaUsers();
    
    /**
     * Current Cafeteria user loggin in
     */
    CafeteriaUser cu;
    
    /**
     * Controller constructor
     */
    public DefineUserBalancelimitsController(){
        SystemUser su = AuthorizationService.session().authenticatedUser();
        Optional<CafeteriaUser> optional = cafeteriaUserRepo.findBySystemUser(su);

        if (optional.isPresent()) {
            this.cu = optional.get();
        }
    }
    
    /**
     * 
     * @param limit
     * @return 
     */
    public boolean setUserBalanceLimit(double limit){
        //Buiseness rule
        if(limit >= 0){
            //Save the limit
            
            cu.accessProfile().addBalanceLimit(limit);
            
            //returns false if it´s done
            //Do not repeat
            return false;
        }
        //Limit is not saved
        return true;
    }
    
    /**
     * 
     * @return 
     */
    public boolean saveUserBalanceLimit(){
        try {
            cafeteriaUserRepo.save(cu);
            return true;
        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            Logger.getLogger(EditNutritionalProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    
}
