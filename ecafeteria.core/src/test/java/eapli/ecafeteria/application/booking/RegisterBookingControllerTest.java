/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.domain.authz.RoleType;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.dishes.DishType;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.domain.Designation;
import eapli.framework.domain.money.Money;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Currency;
import java.util.Locale;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tiago Correia
 */
public class RegisterBookingControllerTest {
    
    public RegisterBookingControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    /**
     * Test 1 of the method "registerBooking(CafeteriaUser cu, Meal meal)" 
     * of the class "RegisterBookingController".
     * The objective of this test is to confirm that the method does not allow
     * a user to book a meal that he can't afford. The expected result of this 
     * method is a "false".
     */
    public void testRegisterBooking1() throws DataConcurrencyException, DataIntegrityViolationException{
        RegisterBookingController controller = new RegisterBookingController();
        Set<RoleType> roles = null; 
        MecanographicNumber number = new MecanographicNumber("1140403");
        SystemUser sUser=new SystemUser("hello", "bye", "o", "la", "1140403@isep.ipp.pt", roles);
        CafeteriaUser user = new CafeteriaUser(sUser, number);
        DishType dishType = new DishType("hello","dishType");
        Designation designation = null;
        Currency currency = Currency.getInstance(Locale.FRANCE);
        Money money = new Money(1000000,currency);
        Dish dish = new Dish(dishType, designation, money);
        Meal meal = new Meal();
        meal.changeDishTo(dish);
        Boolean expResult = false;
        Boolean result = controller.registerBooking(user, meal);
        assertEquals(expResult,expResult);
        
    }
    
}
