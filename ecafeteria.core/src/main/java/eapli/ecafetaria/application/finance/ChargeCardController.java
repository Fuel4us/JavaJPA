/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafetaria.application.finance;

import eapli.ecafetaria.domain.movement.Movement;
import eapli.ecafetaria.domain.movement.MovementType;
import eapli.ecafeteria.Application;
import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.MovementRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Currency;
import java.util.Locale;
import java.util.Optional;

/**
 *
 * @author Hernani Gil
 */
public class ChargeCardController {
    private CafeteriaUser selectedUser;
    
    private final CafeteriaUserRepository cafeteriaUserRepository = PersistenceContext.repositories().cafeteriaUsers();
    private final MovementRepository movementRepository = PersistenceContext.repositories().movement();
//    public Iterable<CafeteriaUser> activeUsers() {
//        return this.cafeteriaUserRepository.findAllActive();
//    }
    
    public boolean selectUser(Username username){ //boolean
        Optional<CafeteriaUser> OpCU = cafeteriaUserRepository.findByUsername(username);
        
        if(OpCU.isPresent()){
            selectedUser = OpCU.get();
            return true;
        }else{
            System.out.println("No user selected");
            return false;
        }
        
    }
    
    public void ChargeCard(double amount) throws DataConcurrencyException, DataIntegrityViolationException{  //bolean
        //AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.SALE);
        if(selectedUser!=null){
            Currency currency = Currency.getInstance(Locale.FRANCE); 
            Movement movement = new Movement(new MecanographicNumber(this.selectedUser.mecanographicNumber().number()), MovementType.DEPOSIT, amount, currency);
      
            movementRepository.save(movement);
        }
    }
}
