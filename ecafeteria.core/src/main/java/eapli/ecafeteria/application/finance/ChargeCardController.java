/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.finance;

import eapli.ecafeteria.domain.movement.BalanceService;
import eapli.ecafeteria.domain.movement.Movement;
import eapli.ecafeteria.domain.movement.MovementType;
import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import eapli.ecafeteria.domain.movement.MovementFactory;
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
    private MovementFactory factory;
    
    public boolean selectUser(Username username){ //boolean
        Optional<CafeteriaUser> OpCU = cafeteriaUserRepository.findByUsername(username);
        
        
        //se o existe e se o seu estado de SystemUser está ativo
        if(OpCU.isPresent() && OpCU.get().user().isActive()){
            selectedUser = OpCU.get();
            return true;
        }else{
            System.out.println("No user selected");
            return false;
        }
        
    }
    
    public double ChargeCard(double amount) throws DataConcurrencyException, DataIntegrityViolationException{ 
        //AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.SALE);
        if(selectedUser!=null){
            factory = new MovementFactory(new MecanographicNumber(this.selectedUser.mecanographicNumber().number()), MovementType.DEPOSIT, amount);
            
            if(factory.createMovement()){
                factory.saveMovement();
            }
        }
        
        return BalanceService.balance(this.selectedUser.mecanographicNumber());
    }
}
