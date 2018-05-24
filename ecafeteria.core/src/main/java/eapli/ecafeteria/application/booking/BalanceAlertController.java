/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.movement.BalanceService;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Rúben Ribeiro (1160998)
 */
public class BalanceAlertController extends Observable implements Observer {
    
    public BalanceAlertController(){
        initializeObserver();
    }
    
    @Override
    public void update(Observable o, Object arg) {
        CafeteriaUser user = (CafeteriaUser) arg;

        double balance = BalanceService.balance(user.mecanographicNumber());
        double balanceLimit = user.accessProfile().getBalanceLimit();

        if(balance <= balanceLimit)
            setChanged();
        
        notifyObservers();
    }
    
    private void initializeObserver(){
        addObserver(new BalanceAlertUI());
    }
}
