/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.finance;

import eapli.framework.domain.ddd.ValueObject;
import java.io.Serializable;

/**
 *
 * @author Josué Lapa
 */
public enum POSState implements Serializable, ValueObject{
    OPEN, CLOSED;
    
    public static POSState[] POSStateValues() {
        return new POSState[]{OPEN,CLOSED};
    }
}
