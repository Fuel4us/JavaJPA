/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.domain.movement.BalanceService;
import eapli.ecafeteria.domain.movement.Movement;
import eapli.ecafeteria.domain.movement.MovementType;
import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import eapli.ecafeteria.domain.dishes.Allergen;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.MovementRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Date;
import java.util.Optional;
import eapli.framework.util.DateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author Leandro
 */
public class RegisterBookingController {

    private final BookingRepository repBooking = PersistenceContext.repositories().booking();
    private final MealRepository repMeal = PersistenceContext.repositories().meals();
    private final CafeteriaUserRepository repCafeteriaUser = PersistenceContext.repositories().cafeteriaUsers();
    private final MovementRepository repMovements = PersistenceContext.repositories().movement();

    private final static long MILLIS_PER_DAY = 24 * 60 * 60 * 1000L;

    public Meal findMealByID(long id) {
        //Needs revision
        Optional<Meal> OpMeal = repMeal.findOne(id);
        if (OpMeal.isPresent()) {
            return OpMeal.get();
        } else {
            return null;
        }
    }

    public CafeteriaUser findCafeteriaUser(Username username) {
        Optional<CafeteriaUser> OpCU = repCafeteriaUser.findByUsername(username);
        if (OpCU.isPresent()) {
            return OpCU.get();
        } else {
            return null;
        }
    }

    /**
     * Register a booking
     *
     * @param cu Cafeteria User to register the booking
     * @param meal Meal in case
     * @return Logical value of the success of the operation
     * @throws DataConcurrencyException
     * @throws DataIntegrityViolationException
     */
    public boolean registerBooking(CafeteriaUser cu, Meal meal) throws DataConcurrencyException, DataIntegrityViolationException {
        //Check if the meal is reserved 24 hours before the actual meal
        Date CurrentDate = new Date();
        Date mealDate = meal.getMealDate();

        final MecanographicNumber mn = new MecanographicNumber(cu.mecanographicNumber().number());
        final double amount = meal.getDish().currentPrice().amount();

        boolean moreThanDay = Math.abs(mealDate.getTime() - CurrentDate.getTime()) > MILLIS_PER_DAY;

        if (BalanceService.balance(mn) >= amount) {
            Currency currency = Currency.getInstance(Locale.FRANCE);

            Calendar mealDateCalendar = DateTime.dateToCalendar(mealDate);
            Calendar currentDateCalendar = DateTime.dateToCalendar(CurrentDate);

            if (moreThanDay == true && DateTime.isBefore(currentDateCalendar, mealDateCalendar)) {
                Movement movement = new Movement(mn, MovementType.BOOKING, amount, currency);
                repMovements.save(movement);

                //More then 24 hours
                Booking booking = new Booking(cu, meal);

                repBooking.save(booking);
                
                booking.notifyObservers(cu);

                return true;
            } else {
                return false;
            }
        }

        return false;
    }

    /**
     * Determine the salt of a dish
     *
     * @param meal Meal that the dish is part of
     * @return Amount of salt
     */
    public int getDishNutritionalInfoSalt(Meal meal) {
        return meal.getDish().nutricionalInfo().salt();
    }

    /**
     * Determine the calories of a dish
     *
     * @param meal Meal that the dish is part of
     * @return Amount of calories
     */
    public int getDishNutritionalInfoCalories(Meal meal) {
        return meal.getDish().nutricionalInfo().calories();
    }

    /**
     * Determine how much calories will the current user consume on the current
     * week if he confirms the booking
     *
     * @param cu Current user
     * @param meal Meal
     * @return Amount of calories of the current week
     */
    public int getDishWeeklyCaloricIntakeAfter(CafeteriaUser cu, Meal meal) {
        int totalCalories = getDishNutritionalInfoCalories(meal);

        for (Booking b : repBooking.checkBookingsForCurrentWeek(cu)) {
            totalCalories += b.getMeal().getDish().nutricionalInfo().calories();
        }

        return totalCalories;
    }

    /**
     * Determines if a meal has allergens
     *
     * @param meal Meal
     * @return Logic value of the operation
     */
    public boolean mealHasAllergens(Meal meal) {
        return meal.getDish().allergens().size() > 0;
    }

    /**
     * Determines the allergens of a meal
     *
     * @param meal Meal
     * @return Meal allergens' list
     */
    public List<String> getAllergens(Meal meal) {
        List<String> allergens = new ArrayList<>();

        for (Allergen a : meal.getDish().allergens()) {
            allergens.add(a.getDescription());
        }

        return allergens;
    }

    /**
     * Determines the allergens of a meal a user is affected by
     *
     * @param cu Current user
     * @param meal Meal
     * @return List of allergens of a meal the user is affected by
     */
    public List<String> getAllergensUserIsAffectedBy(CafeteriaUser cu, Meal meal) {
        List<String> allergensUserIsAffectedBy = new ArrayList<>();

        for (Allergen a : cu.accessNutritionalProfile().userAllergens()) {
            if (meal.getDish().allergens().contains(a)) {
                allergensUserIsAffectedBy.add(a.getDescription());
            }
        }

        return allergensUserIsAffectedBy;
    }

    public void registerBooking(CafeteriaUser cu, Meal meal, Date time) throws DataConcurrencyException, DataIntegrityViolationException {
        Date mealDate = meal.getMealDate();

        final MecanographicNumber mn = new MecanographicNumber(cu.mecanographicNumber().number());
        final double amount = meal.getDish().currentPrice().amount();

        boolean moreThanDay = Math.abs(mealDate.getTime() - time.getTime()) > MILLIS_PER_DAY;

        if (BalanceService.balance(mn) >= amount) {
            Currency currency = Currency.getInstance(Locale.FRANCE);

            Calendar mealDateCalendar = DateTime.dateToCalendar(mealDate);
            Calendar currentDateCalendar = DateTime.dateToCalendar(time);

            if (moreThanDay == true && DateTime.isBefore(currentDateCalendar, mealDateCalendar)) {
                Movement movement = new Movement(mn, MovementType.BOOKING, amount, currency);
                repMovements.save(movement);

                //More then 24 hours
                Booking booking = new Booking(cu, meal, time);

                repBooking.save(booking);
            }
        }
    }

    public void deliveredBooking(Booking booking) throws DataConcurrencyException, DataIntegrityViolationException {
        booking.changeState(BookingState.DELIVERED);
        repBooking.updateBookingStateDelivered(booking);
    }

    public void canceledBooking(Booking booking) throws DataConcurrencyException, DataIntegrityViolationException {
        booking.changeState(BookingState.CANCELED);
        repBooking.updateBookingStateCanceled(booking);
    }

}
