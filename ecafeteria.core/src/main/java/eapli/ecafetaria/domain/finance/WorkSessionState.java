/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafetaria.domain.finance;

/**
 *
 * @author Hernani Gil
 */
public enum WorkSessionState {
    DELIVERYSESSION, DEPOSITSESSION;
    
    public static WorkSessionState[] WorkSessionStateValues() {
        return new WorkSessionState[]{DELIVERYSESSION,DEPOSITSESSION};
    }
}
