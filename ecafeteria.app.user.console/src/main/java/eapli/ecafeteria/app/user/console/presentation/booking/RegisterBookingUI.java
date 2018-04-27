/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.user.console.presentation.booking;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.application.booking.CheckBookingsForNextDaysController;
import eapli.ecafeteria.application.booking.CheckMenuController;
import eapli.ecafeteria.application.booking.RegisterBookingController;
import eapli.ecafeteria.application.meals.ShowAvailableMealsController;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leandro
 */
public class RegisterBookingUI extends AbstractUI{

    private final RegisterBookingController controller = new RegisterBookingController();
    private final CheckMenuController CheckMenuConttroller = new CheckMenuController();
    
    Scanner input = new Scanner(System.in);
    
    @Override
    protected boolean doShow() {
        boolean flag = true;
        
        //Conferir utilizador
        SystemUser su = AuthorizationService.session().authenticatedUser();
        CafeteriaUser cu = controller.findCafeteriaUser(su.username());
        
        System.out.println("Escolha a Meal para reservar:");
        //Apresenta as meals possiveis para escolha
        List<Meal> lstMeals = CheckMenuConttroller.nextWeekMenu();
        for (Meal m : lstMeals) {
            System.out.println(m);
        }
        
        //Utilizador escolhe meal para booking
        long mealID = input.nextLong();
        Meal meal = controller.findMealByID(mealID);
        
        try {
            flag = controller.registerBooking(cu,meal);
        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            Logger.getLogger(RegisterBookingUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(flag == false){
            System.out.println("Nao foi registado nenhum booking!");
        }else{
            System.out.println("Foi registado um booking!");
        }
        
        return true;
    }

    @Override
    public String headline() {
        return "Register Booking";
    }
    
}
