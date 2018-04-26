/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.kitchen;

import eapli.ecafeteria.domain.meals.Meal;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pedro Rodrigues (1140572)
 */
public class MealLotTest {
    
    public MealLotTest() {
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
     * Test of getMeal method, of class MealLot.
     */
    @Test
    public void testGetMeal() {
        System.out.println("getMeal");
        Meal expResult = new Meal();
        Lot lot = new Lot();
        MealLot instance = new MealLot(expResult, lot);
        Meal result = instance.getMeal();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setMeal method, of class MealLot.
     */
    @Test
    public void testSetMeal() {
        System.out.println("setMeal");
        Meal expResult = new Meal();
        MealLot instance = new MealLot();
        instance.setMeal(expResult);
        Meal result = instance.getMeal();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getLot method, of class MealLot.
     */
    @Test
    public void testGetLot() {
        System.out.println("getLot");
        Meal meal = new Meal();
        Lot expResult = new Lot();
        MealLot instance = new MealLot(meal, expResult);
        Lot result = instance.getLot();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setLot method, of class MealLot.
     */
    @Test
    public void testSetLot() {
        System.out.println("setLot");
        MealLot instance = new MealLot();
        Lot expResult = new Lot();
        instance.setLot(expResult);
        Lot result = instance.getLot();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of toString method, of class MealLot.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        MealLot instance = new MealLot();
        String expResult = "MealLot{ID=null, Meal=null, Lot=null}";
        String result = instance.toString();
        assertEquals(expResult, result);
        
    }
    
}
