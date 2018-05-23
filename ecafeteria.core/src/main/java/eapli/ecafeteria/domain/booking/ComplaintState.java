/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.booking;

/**
 *
 * @author Hernani Gil
 */
public enum ComplaintState {
    WAITING, AVAILABLE, ANONYMOUS;
    
     public static ComplaintState[] ComplaintStateValues() {
        return new ComplaintState[]{WAITING, AVAILABLE, ANONYMOUS};
    }
}
