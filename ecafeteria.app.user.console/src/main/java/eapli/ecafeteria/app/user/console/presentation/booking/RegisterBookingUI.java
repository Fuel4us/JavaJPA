/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.user.console.presentation.booking;

import eapli.ecafeteria.application.booking.RegisterBookingController;
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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leandro
 */
public class RegisterBookingUI extends AbstractUI{

    private final RegisterBookingController controller = new RegisterBookingController();
    
    @Override
    protected boolean doShow() {
        
        //Apresenta as meals possiveis para escolha
        
        //Utilizador escolhe meal para booking
        
        //Corrigir utilizador
        CafeteriaUser cu = controller.findCafeteriaUser(new Username("900330"));
        
        //This data is for testing
        try {
            controller.registerBooking(cu,new Meal(MealType.LUNCH, new Date(), new Dish(new DishType("srvev", "srgbrsb"), Designation.valueOf("wegwrg"), new Money(2, Currency.getInstance(Locale.ITALY))), Designation.valueOf("ssrbdb")));
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
