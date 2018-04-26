/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafetaria.domain.movement;

/**
 *
 * @author Hernani Gil
 */
public enum MovementType {
    BOOKING, DEPOSIT, CANCEL;
    
    public static MovementType[] MovementTypeValues(){
        return new MovementType[]{BOOKING, DEPOSIT, CANCEL};
    }
}
