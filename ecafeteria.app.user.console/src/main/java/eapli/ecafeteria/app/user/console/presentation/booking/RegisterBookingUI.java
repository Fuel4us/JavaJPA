/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.user.console.presentation.booking;

import eapli.ecafeteria.application.booking.RegisterBookingController;
import eapli.ecafeteria.application.meals.ShowAvailableMealsController;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.authz.UserSession;
import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.dishes.DishType;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.framework.domain.Designation;
import eapli.framework.domain.money.Money;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leandro
 */
public class RegisterBookingUI extends AbstractUI{

    private final RegisterBookingController controller = new RegisterBookingController();
    private final ShowAvailableMealsController SAMController = new ShowAvailableMealsController();
    
    Scanner input = new Scanner(System.in);
    
    @Override
    protected boolean doShow() {
        
        System.out.println("Escolha a Meal para reservar: \n");
        //Apresenta as meals possiveis para escolha
        //SAMController.showMeals();
        
        //Utilizador escolhe meal para booking
        int mealID = input.nextInt();
        Meal meal = controller.findMealByID(mealID);
        
        //Corrigir utilizador
        //Como é que obtenho o username?????
        CafeteriaUser cu = controller.findCafeteriaUser(new Username("900330"));
        
        try {
            controller.registerBooking(cu,meal);
        } catch (DataConcurrencyException ex) {
            Logger.getLogger(RegisterBookingUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DataIntegrityViolationException ex) {
            Logger.getLogger(RegisterBookingUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return true;
    }

    @Override
    public String headline() {
        return "Register Booking";
    }
    
}
