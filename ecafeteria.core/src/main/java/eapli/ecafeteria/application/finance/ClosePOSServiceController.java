/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.finance;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.finance.Shift;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.POSRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.ShiftRepository;
import eapli.framework.application.Controller;
import eapli.framework.util.DateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Josué Lapa
 */
public class ClosePOSServiceController implements Controller{
    
    //private final ShiftRepository shiftRepository = PersistenceContext.repositories().shift();
    private final BookingRepository bookingRepository = PersistenceContext.repositories().booking();
    private final CafeteriaUserRepository cafeteriaUserRepository = PersistenceContext.repositories().cafeteriaUsers();
    private final POSRepository posRepository = PersistenceContext.repositories().POS();
    
    //fecha o POS atual
    public boolean closeCurrentPOS(){
        return posRepository.closeCurrentPOS();    
    }
    //apresenta o sumario dos pratos entregues
    public String deliverySummary(){
        
        String summary = "";

        for(Booking b : currentCashierDeliveredBookings()){
                summary += b.toString() + "\n";
        }
        return summary;   
    }
    //faz logout do user
    //nao e possivel registar mais operacoes da refeicao em causa ----> LOGOUT
    //efetivar as vendas das reservas nao consumidas dessa caixa
    public List<Booking> unusedBookedMeals(){
        
        List<Booking> deliveredList = currentCashierDeliveredBookings();
        List<Booking> unusedList = new ArrayList<>();
        
        for(Booking b : bookingRepository.listBookedMealsByCUser(cafeteriaUserRepository.findBySystemUser(AuthorizationService.session().authenticatedUser()).get())){
            if(!deliveredList.contains(b)){
                unusedList.add(b);
            }
        }
        
        return unusedList;
    }
    
    public List<Booking> currentCashierDeliveredBookings(){
        
        List<Booking> list = new ArrayList<>();
        
        //now
        Date deliveryDate = DateTime.now().getTime();
        Calendar now = DateTime.now();
        MealType mealType = MealType.LUNCH;

        //current mealtype
        if (now.get(Calendar.HOUR_OF_DAY) > Shift.DINNER_INIT_HOUR) {
            mealType = MealType.DINNER;
        } else {
            if (now.get(Calendar.HOUR_OF_DAY) == Shift.DINNER_INIT_HOUR) {
                if (now.get(Calendar.MINUTE) > 0) {
                    mealType = MealType.DINNER;
                }
            }
        }

        for(Booking b : bookingRepository.findBookingsDelivered()){
            if(b.day().equals(deliveryDate) && b.getMeal().getMealType().equals(mealType)){
                list.add(b);
            }
        }
        
        return list;
    }
}
