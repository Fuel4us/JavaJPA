/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafetaria.domain.finance;

/**
 *
 * @author Josué Lapa
 */
public enum POSState {
    OPEN, CLOSED;
    
    public static POSState[] POSStateValues() {
        return new POSState[]{OPEN,CLOSED};
    }
}
