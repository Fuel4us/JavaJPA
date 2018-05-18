/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.cafeteriauser;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteriauser.NutritionalProfile;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import java.util.Optional;

/**
 *
 * @author Tiago Babo (1160760)
 */
public class EditNutritionalProfileController implements Controller{
    
    private final CafeteriaUserRepository cafeteriaUserRepo = PersistenceContext.repositories().cafeteriaUsers();
    CafeteriaUser currentUser;
    
    public EditNutritionalProfileController() {
        SystemUser user = AuthorizationService.session().authenticatedUser();
        Optional<CafeteriaUser> optional = cafeteriaUserRepo.findBySystemUser(user);

        if (optional.isPresent()) {
            this.currentUser = optional.get();
        }
    }
    
    public NutritionalProfile getUserNutritionalProfile(){
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.CHANGE_NUTRI_PROFILE);
        return currentUser.accessNutritionalProfile();
    }
    
    public void savetMaxSaltQuantity(int maxSalt){
        //GUARDAR NA BD
    }
    
    public void saveMaxCalorieQuantity(int maxCalorie){
        //GUARDAR NA BD
    }
}
