/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.domain.finance.POS;
import eapli.ecafeteria.persistence.POSRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hernani Gil
 */
public class POSBootstrapper implements Action {

    @Override
    public boolean execute() {

        for (int i = 0; i < 4; i++) {
            registerPOS();
        }

        return true;
    }

    public void registerPOS() {

        final POSRepository posRepo = PersistenceContext.repositories().POS();

        try {
            posRepo.save(new POS());
        } catch (DataConcurrencyException | DataIntegrityViolationException e) {
            Logger.getLogger(POSBootstrapper.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
