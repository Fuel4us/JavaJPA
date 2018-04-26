/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.pos.console.presentation.booking;

import eapli.ecafeteria.application.booking.DeliverBookingController;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.util.Console;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.NoResultException;

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
        
        Optional<CafeteriaUser> user;
        try {
            user = theController.findUserByNumber(mn);
            final Iterable<MealType> mealTypes = theController.listMealTypes();
            
            final SelectWidget selector;
            selector = new SelectWidget(BORDER, mealTypes);
            
            selector.show();
            final MealType mealType = (MealType) selector.selectedElement();
            BookingState bookingState = BookingState.RESERVED;
            
            Iterable<Booking> bookings = theController.findBookingByUserAndDate(user, mealType, bookingState);
            
            final SelectWidget selector2;
            selector2 = new SelectWidget(BORDER, bookings);
            
            selector2.show();
            final Booking booking = (Booking) selector2.selectedElement();
            
            System.out.println("Confirma a entrega da reserva" + booking.toString() + "\n");
            String resposta = Console.readLine("[S/N]\n");
            
            if(resposta.equalsIgnoreCase("s")){
                try {
                    try {
                        theController.deliverBookingState(booking);
                    } catch (IllegalArgumentException e){
                        System.out.println("A reserva que escolheu não está reservada");
                    }
                } catch (DataConcurrencyException | DataIntegrityViolationException ex){
                    Logger.getLogger(DeliverBookingUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.println("Nao resultou");
            }
        } catch (DataConcurrencyException ex) {
            Logger.getLogger(DeliverBookingUI.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("O numero do cliente nao e valido");
        } catch (NoResultException el){
            System.out.println("O numero do cliente nao é valido");
        }   
        return true;
    }

    @Override
    public String headline() {
        return "Confirme Deliver Booking";
    }
  
    
}
