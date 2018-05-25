/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.booking;

import eapli.ecafeteria.domain.authz.RoleType;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.dishes.DishType;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.framework.domain.Designation;
import eapli.framework.domain.money.Money;
import java.util.Date;
import java.util.HashSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Hernani Gil
 */
public class ComplaintTest {
    private Complaint instance;
    private Complaint instance2;
    private Complaint instance3;
    
    public ComplaintTest() {
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
     * Test of sameAs method, of class Complaint.
     */
    @Test
    public void testSameAs() {
        System.out.println("sameAs");
        DishType peixe = new DishType("Peixe", "Peixe");
        final Designation name = Designation.valueOf("Test Change");
        
        Dish dish = new Dish(peixe, name, Money.euros(5));
        Meal meal = new Meal(MealType.DINNER, new Date(), dish);
 
        String username = "1000330";
        String firstName = "Gil";
        String password = "Password1";
        String lastName = "Gil";
        String email = "gil@smith.com";
        boolean activateUser = true;
            
        SystemUser user = new SystemUser(username, password, firstName, lastName, email, new HashSet<RoleType>());
        MecanographicNumber number = new MecanographicNumber("1000330");
        CafeteriaUser cafeteriaUser = new CafeteriaUser(user, number);
        Description description = new Description("teste");
        Description description2 = new Description("teste2");
        
        instance = new Complaint(meal, cafeteriaUser, description, ComplaintState.ANONYMOUS);
        instance2 = new Complaint(meal, cafeteriaUser, description2, ComplaintState.ANONYMOUS);

        
        boolean expResult = false;
        boolean result = instance.sameAs(instance2);
        System.out.println(result);
        assertEquals(expResult, result);
    }

    

    /**
     * Test of equals method, of class Complaint.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        DishType peixe = new DishType("Peixe", "Peixe");
        final Designation name = Designation.valueOf("Test Change");
        
        Dish dish = new Dish(peixe, name, Money.euros(5));
        Meal meal = new Meal(MealType.DINNER, new Date(), dish);
 
        String username = "1000330";
        String firstName = "Gil";
        String password = "Password1";
        String lastName = "Gil";
        String email = "gil@smith.com";
            
        SystemUser user = new SystemUser(username, password, firstName, lastName, email, new HashSet<RoleType>());
        MecanographicNumber number = new MecanographicNumber("1000330");
        CafeteriaUser cafeteriaUser = new CafeteriaUser(user, number);
        
        String username2 = "1000331";
        String firstName2 = "John";
        String password2 = "Password1";
        String lastName2 = "Gil";
        String email2 = "john@smith.com";
            
        SystemUser user2 = new SystemUser(username2, password2, firstName2, lastName2, email2, new HashSet<RoleType>());
        MecanographicNumber number2 = new MecanographicNumber("1000331");
        CafeteriaUser cafeteriaUser2 = new CafeteriaUser(user2, number2);
        Description description = new Description("teste");
        Description description2 = new Description("teste2");
        instance = new Complaint(meal, cafeteriaUser, description, ComplaintState.ANONYMOUS);
        instance2 = new Complaint(meal, cafeteriaUser2, description, ComplaintState.AVAILABLE);
        instance3 = new Complaint(meal, cafeteriaUser, description2, ComplaintState.AVAILABLE);
        
        boolean expResult = false;
        boolean result = instance.equals(instance2);
        assertEquals(expResult, result);
        
        boolean expResult2 = true;
        boolean result2 = instance.equals(instance3);
        assertEquals(expResult2, result2);
    }

    /**
     * Test of toString method, of class Complaint.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        DishType peixe = new DishType("Peixe", "Peixe");
        final Designation name = Designation.valueOf("Test Change");
        
        Dish dish = new Dish(peixe, name, Money.euros(5));
        Meal meal = new Meal(MealType.DINNER, new Date(), dish);
 
        String username = "1000330";
        String firstName = "Gil";
        String password = "Password1";
        String lastName = "Gil";
        String email = "gil@smith.com";
        boolean activateUser = true;
            
        SystemUser user = new SystemUser(username, password, firstName, lastName, email, new HashSet<RoleType>());
        MecanographicNumber number = new MecanographicNumber("1000330");
        CafeteriaUser cafeteriaUser = new CafeteriaUser(user, number);
        Description description = new Description("teste");
        instance = new Complaint(meal, cafeteriaUser, description, ComplaintState.ANONYMOUS);
        
        String expResult = "Complaint{id=0, meal=Dish: Test Change <--> Meal Type: DINNER <-->Meal Date: 25/05/2018, cafeteriaUser=1000330, description=teste}";
        
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
