/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.movement;

import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import eapli.ecafeteria.persistence.MovementRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Currency;
import java.util.Locale;

/**
 *
 * @author Hernani Gil
 */

public class MovementFactory {
    private final MovementRepository movementRepository = PersistenceContext.repositories().movement();
    private final Currency currency = Currency.getInstance(Locale.FRANCE);
    private MecanographicNumber nif;
    private MovementType movementType;
    private double amount;
    private Movement movement;

    public MovementFactory(MecanographicNumber nif, MovementType movementType, double amount) throws DataConcurrencyException, DataIntegrityViolationException {
        this.nif = nif;
        this.movementType = movementType;
        this.amount = amount;
    }

    public boolean createMovement(){
        if (amount > 0) {
            movement = new Movement(nif, movementType, amount, currency);  
            return true;
        }
            System.out.println("Movement amount isn't valid\n");
        return false;
    }
    
    public boolean saveMovement() throws DataConcurrencyException, DataIntegrityViolationException{
        if(movement != null){
            movementRepository.save(movement);
            return true;
        }
        return false;
    }
}
