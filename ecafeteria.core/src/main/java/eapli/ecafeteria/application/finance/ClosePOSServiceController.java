/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.finance;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.delivery.Delievery;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.DelieveryRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.ShiftRepository;

/**
 *
 * @author Josué Lapa
 */
public class ClosePOSServiceController {
    
     private final ShiftRepository shiftRepository = PersistenceContext.repositories().shift();
     private final DelieveryRepository deliveryRepository = PersistenceContext.repositories().delieveries();
     private final BookingRepository bookingRepository = PersistenceContext.repositories().booking();
    
    //fecha o turno atual (almoco ou jantar)
    public boolean closeCurrentShift(){
        return shiftRepository.closeCurrentShift();    
    }
    //apresenta o sumario dos pratos entregues
    public String deliverySummary(){
        
        String summary = "";

        for(Delievery d : deliveryRepository.findCurrentShiftDeliveries()){
            if(d.getUser().user().equals(AuthorizationService.session().authenticatedUser())){
                summary += d.toString() + "\n";
            }
        }
        return summary;   
    }
    //faz logout do user
    public boolean autoUserLogout(){
       // AuthorizationService.session().authenticatedUser().
        return false;
    }
    //nao e possivel registar mais operacoes da refeicao em causa ----> LOGOUT
    //efetivar as vendas das reservas nao consumidas dessa caixa
    public void unusedBookedMeals(){
        //tem de ser revisto!!!
         for(Delievery d : deliveryRepository.findCurrentShiftDeliveries()){
             for(Booking b : bookingRepository.findAll()){
                 if(d.getBooking().equals(b)){
                     //adicionar a uma lista
                 }
             }
        }
    }
}
