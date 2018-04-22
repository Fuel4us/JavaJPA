/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.booking;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.framework.visitor.Visitor;

/**
 *
 * @author Ana Mafalda Silva
 */
public class BookingPrinter implements Visitor<Booking>{

    @Override
    public void visit(Booking visitee) {
        System.out.printf("%-10s\n", visitee.sumaryList());
    }
    
}
