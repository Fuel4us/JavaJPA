/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.booking.RegisterBookingController;
import eapli.ecafeteria.application.meals.RegisterMealController;
import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leandro
 */
public class BookingBootstrapper implements Action{

    @Override
    public boolean execute() {
        final MealRepository repMeal = PersistenceContext.repositories().meals();
        final CafeteriaUserRepository repCU = PersistenceContext.repositories().cafeteriaUsers();
        
        final Meal meal1 = repMeal.findAll().iterator().next();
        
        final CafeteriaUser cu1 = repCU.findByUsername(new Username(TestDataConstants.USER_900330)).get();
        
        registerBooking(cu1,meal1);
        
        return true;
    }
    
    private void registerBooking(CafeteriaUser cu, Meal meal){
        final RegisterBookingController controller = new RegisterBookingController();
        
        try {
            controller.registerBooking(cu, meal);
        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            Logger.getLogger(MealBootstrapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
