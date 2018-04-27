/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.movement;

import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import eapli.ecafeteria.persistence.MovementRepository;
import eapli.ecafeteria.persistence.PersistenceContext;

/**
 *
 * @author Hernani Gil
 */
public class BalanceService {
    
    
    public static double balance(MecanographicNumber mecNumber){
        MovementRepository movementRepository = PersistenceContext.repositories().movement();
        Iterable<Movement> listMovements = movementRepository.findAllByNIF(mecNumber);
        
        double amount = 0;
        for(Movement m : listMovements){
            if(m.type().equals(MovementType.BOOKING)){
                amount = amount - m.money().amount();
            }
            if(m.type().equals(MovementType.CANCEL) || m.type().equals(MovementType.DEPOSIT)){
                amount = amount + m.money().amount();
            }
        }
        
        return amount;
    }
}
