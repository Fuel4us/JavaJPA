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
import eapli.ecafeteria.domain.cafeteriauser.Calorie;
import eapli.ecafeteria.domain.cafeteriauser.NutritionalProfile;
import eapli.ecafeteria.domain.cafeteriauser.Salt;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Controller for editing nutritional profile
 * @author Tiago Babo (1160760)
 */
public class EditNutritionalProfileController implements Controller{
    
    /**
     * Respository of CafeteriaUser
     */
    private final CafeteriaUserRepository cafeteriaUserRepo = PersistenceContext.repositories().cafeteriaUsers();
    
    /**
     * Currently logged in user
     */
    CafeteriaUser currentUser;
    
    /**
     * Constructor for the controller
     */
    public EditNutritionalProfileController() {
        SystemUser user = AuthorizationService.session().authenticatedUser();
        Optional<CafeteriaUser> optional = cafeteriaUserRepo.findBySystemUser(user);

        if (optional.isPresent()) {
            this.currentUser = optional.get();
        }
    }
    
    /**
     * Gets the nutritional profile from the current loged in user
     * @return nutritional profile from the current loged in user
     */
    public NutritionalProfile getUserNutritionalProfile(){
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.CHANGE_NUTRI_PROFILE);
        return currentUser.accessNutritionalProfile();
    }

    public boolean setMaxSaltQuantity(int maxSalt) {
        Salt maxSaltQuantity;
        
        try {
            maxSaltQuantity = new Salt(maxSalt);
        }catch(IllegalArgumentException e){
            return false;
        }
        
        currentUser.accessNutritionalProfile().setMaxSaltQuantity(maxSaltQuantity);
        return true;
    }

    public boolean setMaxCalorieQuantity(int maxCalorie) {
        Calorie maxCalorieQuantity;
        
        try{
            maxCalorieQuantity = new Calorie(maxCalorie);
        }catch(IllegalArgumentException e){
            return false;
        }
        
        currentUser.accessNutritionalProfile().setMaxCalorieQuantity(maxCalorieQuantity);
        return true;
    }
    
    public void saveEdit(){
        try {
            cafeteriaUserRepo.save(currentUser);
        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            Logger.getLogger(EditNutritionalProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
