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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pedromonteiro
 */
public class ChangeUserAllergensController implements Controller {

    CafeteriaUser my_user;

    private final AllergenRepository allergenRepo = PersistenceContext.repositories().allergen();
    private final CafeteriaUserRepository cafeteriaUserRepo = PersistenceContext.repositories().cafeteriaUsers();

    public ChangeUserAllergensController() {
        SystemUser user = AuthorizationService.session().authenticatedUser();
        Optional<CafeteriaUser> optional = cafeteriaUserRepo.findBySystemUser(user);

        if (optional.isPresent()) {
            this.my_user = optional.get();
        }
    }

    public Iterable<Allergen> getAllergens() {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.CHANGE_NUTRI_PROFILE);
        return allergenRepo.findAll();
    }

    /**
     * Available Allergens for user to add (get all allergens execept user allergens)
     *
     * @return
     */
    public Iterable<Allergen> getAvailableAllergens() {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.CHANGE_NUTRI_PROFILE);

        List<Allergen> availableAllergens = iterableToList(allergenRepo.findAll().iterator()); //At this pont available allergens = all allergens
        
        availableAllergens.removeAll(iterableToList(my_user.accessNutritionalProfile().userAllergens().iterator())); //Removes user allergens from all allergens
        
        return availableAllergens;

    }

    public Iterable<Allergen> getUserAllergen() {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.CHANGE_NUTRI_PROFILE);
        return my_user.accessNutritionalProfile().userAllergens();
    }

    public boolean removeAllergen(Allergen allergen) {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.CHANGE_NUTRI_PROFILE);
        if (my_user.accessNutritionalProfile().removeAllergen(allergen)) {
            try {
                return this.cafeteriaUserRepo.save(my_user) != null;
            } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
                Logger.getLogger(ChangeUserAllergensController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public boolean addAllergen(Allergen allergen) {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.CHANGE_NUTRI_PROFILE);

        if (my_user.accessNutritionalProfile().addAllergen(allergen)) {
            try {
                return this.cafeteriaUserRepo.save(my_user) != null;
            } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
                Logger.getLogger(ChangeUserAllergensController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return false;
    }

    /**
     *
     * @param <T>
     * @param iter
     * @return
     */
    private <T> List<T> iterableToList(Iterator<T> it) {
        List<T> copy = new ArrayList<>();
        while (it.hasNext()) {
            copy.add(it.next());
        }
        return copy;
    }

}
