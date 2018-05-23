/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.finance.ChargeCardController;
import eapli.ecafeteria.application.booking.RegisterBookingController;
import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.util.DateTime;
import java.util.Date;
import java.util.Iterator;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leandro
 */
public class BookingBootstrapper implements Action {

    final RegisterBookingController controller = new RegisterBookingController();

    @Override
    public boolean execute() {
        try {
            final MealRepository repMeal = PersistenceContext.repositories().meals();
            final BookingRepository repBook = PersistenceContext.repositories().booking();
            final CafeteriaUserRepository repCU = PersistenceContext.repositories().cafeteriaUsers();

            Iterator<Meal> itMeals = repMeal.findAll().iterator();

            final Meal meal1 = itMeals.next();
            final Meal meal2 = itMeals.next();
            final Meal meal3 = itMeals.next();

            Optional<Meal> meal46 = repMeal.findById(Long.parseLong("46"));
            Optional<Meal> meal47 = repMeal.findById(Long.parseLong("47"));
            Optional<Meal> meal48 = repMeal.findById(Long.parseLong("48"));
            Optional<Meal> meal49 = repMeal.findById(Long.parseLong("49"));

            final CafeteriaUser cu1 = repCU.findByUsername(new Username(TestDataConstants.USER_1000330)).get();
            final CafeteriaUser cu2 = repCU.findByUsername(new Username(TestDataConstants.USER_1000332)).get();

            final ChargeCardController CC = new ChargeCardController();
            try {
                CC.selectUser(cu1.user().username());
                CC.ChargeCard(500);
                CC.selectUser(cu2.user().username());
                CC.ChargeCard(490);
            } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
                Logger.getLogger(BookingBootstrapper.class.getName()).log(Level.SEVERE, null, ex);
            }

            registerBooking(cu1, meal1);
            registerBooking(cu1, meal2);
            registerBooking(cu1, meal3);
            registerBooking(cu2, meal2);

            registerBooking(cu1, meal46.get(), DateTime.newCalendar(DateTime.currentYear(), DateTime.currentMonth() - 1, 1).getTime());
            controller.canceledBooking(repBook.findOne(Long.parseLong("5")).get());
            registerBooking(cu2, meal46.get(), DateTime.newCalendar(DateTime.currentYear(), DateTime.currentMonth() - 1, 1).getTime());
            controller.canceledBooking(repBook.findOne(Long.parseLong("6")).get());
            registerBooking(cu1, meal46.get(), DateTime.newCalendar(DateTime.currentYear(), DateTime.currentMonth() - 1, 1).getTime());
            controller.deliveredBooking(repBook.findOne(Long.parseLong("7")).get());
            registerBooking(cu2, meal46.get(), DateTime.newCalendar(DateTime.currentYear(), DateTime.currentMonth() - 1, 1).getTime());
            controller.deliveredBooking(repBook.findOne(Long.parseLong("8")).get());
            registerBooking(cu1, meal46.get(), DateTime.newCalendar(DateTime.currentYear(), DateTime.currentMonth() - 1, 1).getTime());

            registerBooking(cu1, meal47.get(), DateTime.newCalendar(DateTime.currentYear(), DateTime.currentMonth() - 1, 1).getTime());
            controller.deliveredBooking(repBook.findOne(Long.parseLong("10")).get());
            registerBooking(cu2, meal47.get(), DateTime.newCalendar(DateTime.currentYear(), DateTime.currentMonth() - 1, 1).getTime());
            controller.deliveredBooking(repBook.findOne(Long.parseLong("11")).get());
            registerBooking(cu1, meal47.get(), DateTime.newCalendar(DateTime.currentYear(), DateTime.currentMonth() - 1, 1).getTime());
            controller.deliveredBooking(repBook.findOne(Long.parseLong("12")).get());

            registerBooking(cu1, meal48.get(), DateTime.newCalendar(DateTime.currentYear(), DateTime.currentMonth() - 1, 1).getTime());
            controller.canceledBooking(repBook.findOne(Long.parseLong("13")).get());
            registerBooking(cu2, meal48.get(), DateTime.newCalendar(DateTime.currentYear(), DateTime.currentMonth() - 1, 1).getTime());
            controller.deliveredBooking(repBook.findOne(Long.parseLong("14")).get());
            registerBooking(cu1, meal48.get(), DateTime.newCalendar(DateTime.currentYear(), DateTime.currentMonth() - 1, 1).getTime());
            controller.deliveredBooking(repBook.findOne(Long.parseLong("15")).get());
            registerBooking(cu2, meal48.get(), DateTime.newCalendar(DateTime.currentYear(), DateTime.currentMonth() - 1, 1).getTime());
            controller.deliveredBooking(repBook.findOne(Long.parseLong("16")).get());
            registerBooking(cu1, meal48.get(), DateTime.newCalendar(DateTime.currentYear(), DateTime.currentMonth() - 1, 1).getTime());
            registerBooking(cu1, meal48.get(), DateTime.newCalendar(DateTime.currentYear(), DateTime.currentMonth() - 1, 1).getTime());
            registerBooking(cu2, meal48.get(), DateTime.newCalendar(DateTime.currentYear(), DateTime.currentMonth() - 1, 1).getTime());
            registerBooking(cu1, meal48.get(), DateTime.newCalendar(DateTime.currentYear(), DateTime.currentMonth() - 1, 1).getTime());

            registerBooking(cu1, meal49.get(), DateTime.newCalendar(DateTime.currentYear(), DateTime.currentMonth() - 1, 1).getTime());
            controller.canceledBooking(repBook.findOne(Long.parseLong("21")).get());
            registerBooking(cu2, meal49.get(), DateTime.newCalendar(DateTime.currentYear(), DateTime.currentMonth() - 1, 1).getTime());
            controller.canceledBooking(repBook.findOne(Long.parseLong("22")).get());
            registerBooking(cu1, meal49.get(), DateTime.newCalendar(DateTime.currentYear(), DateTime.currentMonth() - 1, 1).getTime());
            controller.canceledBooking(repBook.findOne(Long.parseLong("23")).get());
            registerBooking(cu2, meal49.get(), DateTime.newCalendar(DateTime.currentYear(), DateTime.currentMonth() - 1, 1).getTime());
            controller.deliveredBooking(repBook.findOne(Long.parseLong("24")).get());
            registerBooking(cu1, meal49.get(), DateTime.newCalendar(DateTime.currentYear(), DateTime.currentMonth() - 1, 1).getTime());
            controller.deliveredBooking(repBook.findOne(Long.parseLong("25")).get());
            registerBooking(cu1, meal49.get(), DateTime.newCalendar(DateTime.currentYear(), DateTime.currentMonth() - 1, 1).getTime());
            controller.deliveredBooking(repBook.findOne(Long.parseLong("26")).get());
            registerBooking(cu2, meal49.get(), DateTime.newCalendar(DateTime.currentYear(), DateTime.currentMonth() - 1, 1).getTime());
            controller.deliveredBooking(repBook.findOne(Long.parseLong("27")).get());
            registerBooking(cu1, meal49.get(), DateTime.newCalendar(DateTime.currentYear(), DateTime.currentMonth() - 1, 1).getTime());
            controller.deliveredBooking(repBook.findOne(Long.parseLong("28")).get());
            registerBooking(cu2, meal49.get(), DateTime.newCalendar(DateTime.currentYear(), DateTime.currentMonth() - 1, 1).getTime());
            controller.deliveredBooking(repBook.findOne(Long.parseLong("29")).get());
            registerBooking(cu1, meal49.get(), DateTime.newCalendar(DateTime.currentYear(), DateTime.currentMonth() - 1, 1).getTime());
            controller.deliveredBooking(repBook.findOne(Long.parseLong("30")).get());

            return true;
        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            Logger.getLogger(BookingBootstrapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    private void registerBooking(CafeteriaUser cu, Meal meal) {

        try {
            controller.registerBooking(cu, meal);
        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            Logger.getLogger(MealBootstrapper.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void registerBooking(CafeteriaUser cu1, Meal meal3, Date time) {

        try {
            controller.registerBooking(cu1, meal3, time);
        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            Logger.getLogger(MealBootstrapper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
