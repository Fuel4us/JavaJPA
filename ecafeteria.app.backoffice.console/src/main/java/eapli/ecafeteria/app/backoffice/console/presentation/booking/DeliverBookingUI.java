/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.booking;

import eapli.ecafeteria.application.booking.DeliverBookingController;
import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ana Mafalda Silva
 */
public class DeliverBookingUI extends AbstractUI {
    
    private final DeliverBookingController theController  =  new DeliverBookingController();

    @Override
    protected boolean doShow() {
        
        Integer clientNumber = Console.readInteger("Introduza o nº do cliente");
        MecanographicNumber mn = new MecanographicNumber (clientNumber + "");
        
        Optional<CafeteriaUser> user = null;
        try {
            user = theController.findUserByNumber(mn);
            final Iterable<MealType> mealTypes = theController.listMealTypes();
            
            
        } catch (DataConcurrencyException ex) {
            Logger.getLogger(DeliverBookingUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        return true;
    }

    @Override
    public String headline() {
        return "Confirme Deliver Booking";
    }
  
    
}
