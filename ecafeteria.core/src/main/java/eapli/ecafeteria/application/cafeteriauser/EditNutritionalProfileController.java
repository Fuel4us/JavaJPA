/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.cafeteriauser;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.AuthenticationService;
import eapli.ecafeteria.domain.dishes.Allergen;
import eapli.ecafeteria.persistence.AllergenRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;

/**
 *
 * @author pedromonteiro
 */
public class EditNutritionalProfileController implements Controller{
    
    private final AllergenRepository repo = PersistenceContext.repositories().allergen();
    
    public Iterable<Allergen> getAllergens(){
        return repo.findAll();
    }
    
    public Iterable<Allergen> getUserAllergen(){
        return null;
    }
    
    public boolean removeAllergen(Allergen allergen){
//        AuthorizationService.session().authenticatedUser()
return false;
    }
    
    public boolean addAllergen(Allergen allergen){
        return false;
    }
}
