/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.finance.ChargeCardController;
import eapli.ecafeteria.application.booking.RegisterBookingController;
import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Iterator;
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
            
            Iterator<Meal> itMeals = repMeal.findAll().iterator();
            
            final Meal meal1 = itMeals.next();
            final Meal meal2 = itMeals.next();
            final Meal meal3 = itMeals.next();
            
            final CafeteriaUser cu1 = repCU.findByUsername(new Username(TestDataConstants.USER_900330)).get();
            final CafeteriaUser cu2 = repCU.findByUsername(new Username(TestDataConstants.USER_900332)).get();
            
            final ChargeCardController CC = new ChargeCardController();
            try {    
                CC.selectUser(cu1.user().username());
                CC.ChargeCard(100);
                CC.selectUser(cu2.user().username());
                CC.ChargeCard(90);
            } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
                Logger.getLogger(BookingBootstrapper.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            registerBooking(cu1, meal1);
            registerBooking(cu1, meal2);
            registerBooking(cu1, meal3);
            registerBooking(cu2, meal2);
            
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
