/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafetaria.application.finance;

import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.PersistenceContext;

/**
 *
 * @author Hernani Gil
 */
public class ChargeCardController {
    private CafeteriaUser selectedUser;
    
    private final CafeteriaUserRepository cafeteriaUserRepository = PersistenceContext.repositories().cafeteriaUsers();
    //private final Movement movementRepository = PersistenceContext.repositories().transactions();
    
    public Iterable<CafeteriaUser> activeUsers() {

        return this.cafeteriaUserRepository.findAllActive();
    }
    
    public void selectUser(Username username){ //boolean
        //this.selectedUser = cafeteriaUserRepository.findByUsername(username);
    }
    
    public boolean ChargeCard(double amount){  //bolean
        if(selectedUser!=null){
            ChargeCardMovementFactory
        }
        
            Transaction transaction = new Transaction(moneyAmount, currency);
            if (selectedUser.account().chargeCard(moneyAmount, transaction)) {
                this.userRepository.save(selectedUser);
            }
        
        return false;
    }
}
