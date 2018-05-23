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
import eapli.ecafeteria.domain.dishes.Allergen;
import eapli.ecafeteria.persistence.AllergenRepository;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Change User allergens Controller
 * @author pedromonteiro
 */
public class ChangeUserAllergensController implements Controller {

    CafeteriaUser my_user;

    private final AllergenRepository allergenRepo = PersistenceContext.repositories().allergen();
    private final CafeteriaUserRepository cafeteriaUserRepo = PersistenceContext.repositories().cafeteriaUsers();

    /**
     * Constructor
     */
    public ChangeUserAllergensController() {
        SystemUser user = AuthorizationService.session().authenticatedUser();
        Optional<CafeteriaUser> optional = cafeteriaUserRepo.findBySystemUser(user);

        if (optional.isPresent()) {
            this.my_user = optional.get();
        }
    }

    /**
     * Return all the allergens in the Repository.
     *
     * @return all allergens;
     */
    public Iterable<Allergen> getAllergens() {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.CHANGE_NUTRI_PROFILE);
        return allergenRepo.findAll();
    }
    
    /**
     * Return the user allergens
     *
     * @return User allergens
     */
    public Iterable<Allergen> getUserAllergen() {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.CHANGE_NUTRI_PROFILE);

            return my_user.accessNutritionalProfile().userAllergens();
    }

    /**
     * Remove a requested allergen from users allergens.
     *
     * @param allergen allergen to remove
     * @return true if removed, false if not
     */
    public boolean removeAllergen(Allergen allergen) {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.CHANGE_NUTRI_PROFILE);
        try {
            if (my_user.accessNutritionalProfile().removeAllergen(allergen)) {
                return this.cafeteriaUserRepo.save(my_user) != null;
            }
        } catch (DataConcurrencyException | DataIntegrityViolationException | NullPointerException ex) {
            Logger.getLogger(ChangeUserAllergensController.class.getName()).log(Level.SEVERE, null, ex);

        } 
        return false;
    }

    /**
     * Add an allergen to user's allergen
     *
     * @param allergen allergen to add
     * @return true if added, false if not
     */
    public boolean addAllergen(Allergen allergen) {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.CHANGE_NUTRI_PROFILE);
        try {
            if (my_user.accessNutritionalProfile().addAllergen(allergen)) {

                return this.cafeteriaUserRepo.save(my_user) != null;

            }
        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            Logger.getLogger(ChangeUserAllergensController.class.getName()).log(Level.SEVERE, null, ex);
        } catch(IllegalArgumentException ill_arg){
            System.out.println(ill_arg.getMessage());
        }
        
        return false;
    }

}
