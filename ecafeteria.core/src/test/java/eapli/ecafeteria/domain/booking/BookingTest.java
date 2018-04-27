/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.booking;

import eapli.ecafeteria.domain.authz.Name;
import eapli.ecafeteria.domain.authz.Password;
import eapli.ecafeteria.domain.authz.RoleType;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.dishes.DishType;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.framework.domain.Designation;
import eapli.framework.domain.EmailAddress;
import eapli.framework.domain.money.Money;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Leandro
 */
public class BookingTest {
    
    public BookingTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        roles.add(RoleType.ADMIN);

    }
    
    @After
    public void tearDown() {
    }

    final Set<RoleType> roles = new HashSet<>();
    
    private final DishType dishType = new DishType("dishtype", "dishtype description");
    private final Dish dish = new Dish(dishType, Designation.valueOf("prato"), new Money(2, Currency.getInstance(Locale.ITALY)));
    private final Date date = new Date();
    private final Meal meal = new Meal(MealType.LUNCH, date, dish);
    private final Meal meal2 = new Meal(MealType.DINNER, date, dish);
    private final SystemUser su = new SystemUser("TestUsername", "TestPassword1", "TestFirstName","TestLastName", "Testeemail@hotmail.com", roles);
    private final MecanographicNumber mn = new MecanographicNumber("123456");
    private final CafeteriaUser cu = new CafeteriaUser(su, mn);
    private final Booking booking = new Booking(cu, meal);
    
    /**
     * Test of day method, of class Booking.
     */
    @Test
    public void testDay() {
        System.out.println("day");
        
        //Has the meal gets the current date
        //when the OS is creating the booking its a few milliseconds ahead of meal date
        //So we have to check for the seconds and not the actual date
        Date expResult = date;
        Date result = booking.day();
        
        long exp = expResult.getTime();
        long res = result.getTime();
        
        boolean flag = false;
        if(Math.abs(exp - res) < 100){
            flag = true;
        }
        
        assertEquals(flag, true);
    }

    /**
     * Test of sameAs method, of class Booking.
     */
    @Test
    public void testSameAs() {
        System.out.println("sameAs");
        
        boolean expResult = false;
        boolean result = booking.sameAs(null);
        
        assertEquals(expResult, result);
    }

    /**
     * Test of getMeal method, of class Booking.
     */
    @Test
    public void testGetMeal() {
        System.out.println("getMeal");
        
        Booking instance = new Booking(cu, meal);
        Meal expResult = booking.getMeal();
        Meal result = instance.getMeal();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of getBookingState method, of class Booking.
     */
    @Test
    public void testGetBookingState() {
        System.out.println("getBookingState");
        
        Booking instance = new Booking(cu, meal);;
        BookingState expResult = booking.getBookingState();
        BookingState result = instance.getBookingState();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of changeState method, of class Booking.
     */
    @Test
    public void testChangeToReservedState() {
        System.out.println("changeState");
        
        BookingState newState = BookingState.DELIVERED;
        Booking instance = new Booking(cu, meal);
        
        instance.changeState(newState);
    }
    
    /**
     * Test of changeState method, of class Booking.
     */
    @Test
    public void testChangeToCanceledState() {
        System.out.println("changeState");
        
        BookingState newState = BookingState.CANCELED;
        Booking instance = new Booking(cu, meal);
        
        instance.changeState(newState);
    }

    /**
     * Test of sumaryList method, of class Booking.
     */
    @Test
    public void testSumaryList() {
        System.out.println("sumaryList");
        
        Booking instance = new Booking(cu, meal);
        SimpleDateFormat data = new SimpleDateFormat("dd-MM-yyyy");
        String expResult = "DAY: " + data.format(date.getTime()) + " /PLATE: " + dish.id() + " /TYPE: " + MealType.LUNCH.toString() + " /MEAL: " + dish.dishType().id();
        String result = instance.sumaryList();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Booking.
     */
//    @Test
//    public void testHashCode() {
//        System.out.println("hashCode");
//        
//        Booking instance = new Booking(cu, meal);
//        
//        int hash = 5;
//        int expResult = 1 * hash;
//        int result = instance.hashCode();
//        
//        assertEquals(expResult, result);
//    }

    /**
     * Test of equals method, of class Booking.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        
        Booking instance = new Booking(cu, meal);
        boolean expResult = true;
        boolean result = instance.equals(booking);
        
        assertEquals(expResult, result);
    }
    
    /**
     * Test of equals method, of class Booking.
     */
    @Test
    public void testNotEquals() {
        System.out.println("not equals");
        
        Booking instance = new Booking(cu, meal2);
        boolean expResult = false;
        boolean result = instance.equals(booking);
        
        assertEquals(expResult, result);
    }

    /**
     * Test of isReserved method, of class Booking.
     */
    @Test
    public void testIsReserved() {
        System.out.println("isReserved");
        
        boolean expResult = true;
        boolean result = booking.isReserved();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of isCancelled method, of class Booking.
     */
    @Test
    public void testIsCancelled() {
        System.out.println("isCancelled");
        
        boolean expResult = false;
        boolean result = booking.isCancelled();
        
        assertEquals(expResult, result);
    }
    
    /**
     * Test of isCancelled method, of class Booking.
     */
    @Test
    public void testIsInCancelledSate() {
        System.out.println("isCancelled");
        
        Booking aux_booking = new Booking(cu, meal);
        aux_booking.changeState(BookingState.CANCELED);
        boolean expResult = true;
        boolean result = aux_booking.isCancelled();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of isDelivered method, of class Booking.
     */
    @Test
    public void testIsDelivered() {
        System.out.println("isDelivered");
        
        Booking instance = new Booking(cu, meal);
        instance.changeState(BookingState.DELIVERED);
        boolean expResult = true;
        boolean result = instance.isDelivered();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of rating method, of class Booking.
     */
    @Test
    public void testRating() {
        System.out.println("rating");
        
        Rating rating = new Rating(10, "Rating");
        
        booking.rating(rating);
    }

    /**
     * Test of toString method, of class Booking.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        
        String expResult = "Booking{bookingID=null, id=123456Meal{dish=eapli.ecafeteria.domain.dishes.Dish@65f955a, "
                + "mealType=LUNCH, mealDate=" + booking.getMeal().getMealDate().toString() + "}, user=TestFirstName TestLastName"
                + ", meal=Meal{dish=eapli.ecafeteria.domain.dishes.Dish@65f955a, mealType=LUNCH, "
                + "mealDate=" + booking.getMeal().getMealDate().toString() + "}, bookingState=RESERVED, rating=null}";
        String result = booking.toString();
        
        assertEquals(expResult, result);
    }
    
}
