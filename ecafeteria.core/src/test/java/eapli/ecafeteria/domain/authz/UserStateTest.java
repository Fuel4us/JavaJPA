/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.authz;

import java.util.Calendar;
import java.util.GregorianCalendar;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for UserState Class.
 * @author pedromonteiro
 */
public class UserStateTest {
    
    public UserStateTest() {
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
     * Test of accept method, of class UserState.
     */
    @Test
    public void testAccept() {
        System.out.println("Test Accept user ----------------\n");
        UserState instance = new UserState();
        
        UserState.UserType expected = UserState.UserType.STAND_BY;
        UserState.UserType result = instance.state();
        assertEquals(expected, result); // When instantiated/request sign, an user should be in STANDY BY mode
        
        instance.accept();
        
        result = instance.state();
        assertNotEquals(expected, result);  // Should not be equal, because now instance is ACCEPTED
        
        expected = UserState.UserType.ACCEPTED;
        assertEquals(expected, result); // Should be ACCEPTED because instance.accept() [line 53]
        
        UserState instance_2 = new UserState();
        
        instance_2.reject();
        
        boolean result_bool = instance_2.accept();
        
        assertFalse(result_bool); //Sould be False because a rejected user cannot be ACCEPTED
        
        
        
        
    }

    /**
     * Test of reject method, of class UserState.
     */
    @Test
    public void testReject() {
        System.out.println("Test reject user ----------------\n");
        UserState instance = new UserState();
        
        UserState.UserType expected = UserState.UserType.STAND_BY;
        UserState.UserType result = instance.state();
        assertEquals(expected, result); // When instantiated/request sign, an user should be in STANDY BY mode
        
        instance.reject();
        
        result = instance.state();
        assertNotEquals(expected, result);  // Should not be equal, because now instance is REJECTED
        
        expected = UserState.UserType.REJECTED;
        assertEquals(expected, result); // Should be REJECTED because instance.reject() [line 86]
        
        UserState instance_2 = new UserState();
        
        instance_2.accept();
        
        boolean result_bool = instance.reject();
        
        assertFalse(result_bool); //Sould be False because an accepted user cannot be REJECTED
    }

    /**
     * Test of deactivate method, of class UserState.
     */
    @Test
    public void testDeactivate() {
        System.out.println("Test deactivate user ----------------\n");
        UserState instance = new UserState();
        instance.accept();
        
        UserState.UserType expected = UserState.UserType.ACCEPTED;
        UserState.UserType result = instance.state();
        assertEquals(expected, result); // When we want to deactivate a user, it should be already accepted
        
        instance.deactivate(Calendar.getInstance(), ReasonType.REASON1, "Comment test");
        
        result = instance.state();
        assertNotEquals(expected, result);  // Should not be equal, because now instance is DEACTIVATED
        
        expected = UserState.UserType.DEACTIVATED;
        assertEquals(expected, result); // Should be DEACTIVATED because instance.reject() [line 116]
        
        UserState instance_2 = new UserState();
        
        instance_2.reject();
        
        boolean result_bool = instance.deactivate(Calendar.getInstance(), ReasonType.REASON2, "Comment test");
        
        assertFalse(result_bool); //Sould be False because an rejected user cannot be DEACTIAVTED
        assertNotEquals(instance_2.state(), UserState.UserType.DEACTIVATED); //Sould be Not Equal because the a rejected user can no be DEACTIVATED
    }

    /**
     * Test of deactivatedReason method, of class UserState.
     */
    @Test
    public void testDeactivatedReason() {
        System.out.println("Test Deactivated Reason ----------------\n");
        UserState instance = new UserState();
        UserState instance_2 = new UserState();
        UserState instance_3 = new UserState();
        
        assertNull(instance.deactivatedReason()); // Should be null because it isn't DEACTIVATED
        
        instance_2.accept();
        assertNull(instance.deactivatedReason()); // Should be null because it isn't DEACTIVATED
        
        instance_3.reject();
        assertNull(instance.deactivatedReason()); // Should be null because it isn't DEACTIVATED
        
        String reasonComment = "Reason Comment";
        Reason r = new Reason(ReasonType.REASON1, reasonComment);
        instance_2.deactivate(Calendar.getInstance(), ReasonType.REASON1, reasonComment);
        
        //The method equals of Class Reason is being compared with an auto generated value (id), so we'll test the Reason type and comment
        assertEquals(r.comment(), instance_2.deactivatedReason().comment());
        assertEquals(r.reasonType(), instance_2.deactivatedReason().reasonType());
        
    }

    /**
     * Test of dateOfDeactivation method, of class UserState.
     */
    @Test
    public void testDateOfDeactivation() {
        System.out.println("Test Date of Deactivation ----------------\n");
        UserState instance = new UserState();
        instance.accept();
        
        assertNull(instance.dateOfDeactivation());
        
        instance.deactivate(new GregorianCalendar(2017, 12, 30, 17, 30, 20), ReasonType.REASON1, "Comment");
        Calendar result = new GregorianCalendar(2017, 12, 30, 17, 30, 20);
        
        assertEquals(instance.dateOfDeactivation(), result);
    }

    /**
     * Test of state method, of class UserState.
     */
    @Test
    public void testState() {
        System.out.println("Test State ----------------\n");
        UserState instance = new UserState();
        UserState instance_2 = new UserState();
        
        assertEquals(instance.state(), UserState.UserType.STAND_BY);
        
        instance.accept();
        
        assertEquals(instance.state(), UserState.UserType.ACCEPTED);
        
        instance_2.reject();
        
        assertEquals(instance_2.state(), UserState.UserType.REJECTED);
        
        instance.deactivate(Calendar.getInstance(), ReasonType.REASON1, "Comment");
        
        assertEquals(instance.state(), UserState.UserType.DEACTIVATED);
    }
    
}
