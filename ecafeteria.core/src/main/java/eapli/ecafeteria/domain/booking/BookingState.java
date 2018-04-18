/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.booking;

/**
 *
 * @author Ana Mafalda Silva
 */
public enum BookingState {
    RESERVED, DELIVERED, CANCELED;
    
     public static BookingState[] ReservationStateValues() {
        return new BookingState[]{RESERVED, DELIVERED, CANCELED};
    }
}
