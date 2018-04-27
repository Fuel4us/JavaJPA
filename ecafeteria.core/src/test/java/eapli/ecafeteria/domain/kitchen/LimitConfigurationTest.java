/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.kitchen;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author konng
 */
public class LimitConfigurationTest {
    LimitConfiguration instance;
    public LimitConfigurationTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance= new LimitConfiguration();
    }
    
    @After
    public void tearDown() {
        System.out.println("");
    }

    /**
     * Test of configureYellowLimit method, of class LimitConfiguration.
     */
    @Test
    public void testConfigureYellowLimit() {
        System.out.println("configureYellowLimit");
        System.out.println("Test 0 is accepted");
        double limitValue = 0.0;
        boolean result = instance.configureYellowLimit(limitValue);
        assertEquals(true, result);
        System.out.println("Test 100 is accepted");
        limitValue = 100.0;
        result = instance.configureYellowLimit(limitValue);
        assertEquals(true, result);
        System.out.println("Test in between 0 and 100 is accepted");
        limitValue = 50.0;
        result = instance.configureYellowLimit(limitValue);
        assertEquals(true, result);
        System.out.println("Test >100 not accepted");
        limitValue = 120.0;
        result = instance.configureYellowLimit(limitValue);
        assertEquals(false, result);
        System.out.println("Test <0 not accepted");
        limitValue = -20.0;
        result = instance.configureYellowLimit(limitValue);
        assertEquals(false, result);
    }

    /**
     * Test of configureRedLimit method, of class LimitConfiguration.
     */
    @Test
    public void testConfigureRedLimit() {
        System.out.println("configureYellowLimit");
        System.out.println("Test 0 is accepted");
        double limitValue = 0.0;
        boolean result = instance.configureYellowLimit(limitValue);
        assertEquals(true, result);
        System.out.println("Test 100 is accepted");
        limitValue = 100.0;
        result = instance.configureYellowLimit(limitValue);
        assertEquals(true, result);
        System.out.println("Test in between 0 and 100 is accepted");
        limitValue = 50.0;
        result = instance.configureYellowLimit(limitValue);
        assertEquals(true, result);
        System.out.println("Test >100 not accepted");
        limitValue = 120.0;
        result = instance.configureYellowLimit(limitValue);
        assertEquals(false, result);
        System.out.println("Test <0 not accepted");
        limitValue = -20.0;
        result = instance.configureYellowLimit(limitValue);
        assertEquals(false, result);
    }

  
}
