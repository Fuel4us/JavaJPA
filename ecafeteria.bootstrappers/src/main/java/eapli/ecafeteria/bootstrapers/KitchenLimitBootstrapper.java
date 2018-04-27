/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.kitchen.LimitConfiguration;
import eapli.ecafeteria.persistence.KitchenLimitRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author konng
 */
public class KitchenLimitBootstrapper implements Action {

    @Override
    public boolean execute() {
        register();
        return true;
    }
    
        private void register() {
        try {
            final KitchenLimitRepository kitchenLimitRepository = PersistenceContext.repositories().kitchenLimit();
            
            AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.ADMINISTER);
            
            LimitConfiguration entity = new LimitConfiguration();
            kitchenLimitRepository.save(entity);
        } catch (final DataConcurrencyException | DataIntegrityViolationException ex) {
            Logger.getLogger(KitchenLimitBootstrapper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
