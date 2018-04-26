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
 * @author Pedro Rodrigues (1140572)
 */
public class LotTest {
    
    public LotTest() {
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
     * Test of getQuantity method, of class Lot.
     */
    @Test
    public void testGetQuantity() {
        System.out.println("getQuantity");
        Material mat = new Material("Teste", "Ovos");
        Lot instance = new Lot(1,mat,5);
        int expResult = 5;
        int result = instance.getQuantity();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of changeQuantity method, of class Lot.
     */
    @Test
    public void testChangeQuantity() {
        System.out.println("changeQuantity");
        Material mat = new Material("Teste", "Ovos");
        int expResult = 2;
        Lot instance = new Lot(1,mat,1);
        instance.changeQuantity(expResult);
        int result = instance.getQuantity();
        assertEquals(expResult, result);
    }

    /**
     * Test of id method, of class Lot.
     */
    @Test
    public void testId() {
        System.out.println("id");
        Material mat = new Material("Teste", "Ovos");
        Lot instance = new Lot(1,mat,1);
        Integer expResult = 1;
        Integer result = instance.id();
        assertEquals(expResult, result);
    }

    /**
     * Test of is method, of class Lot.
     */
    @Test
    public void testIs() {
        System.out.println("is");
        Integer otherId = 1;
        Material mat = new Material("Teste", "Ovos");
        Lot instance = new Lot(1,mat,1);
        Lot instance2 = new Lot(2,mat,1);
        boolean expResult = true;
        boolean result = instance.is(otherId);
        assertEquals(expResult, result);
        
        expResult = false;
        result = instance2.is(otherId);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of sameAs method, of class Lot.
     */
    @Test
    public void testSameAs() {
        System.out.println("sameAs");
        Object other = null;
        Lot instance = new Lot();
        boolean expResult = false;
        boolean result = instance.sameAs(other);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of equals method, of class Lot.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Material mat = new Material("Teste", "Ovos");
        Material mat2 = new Material("Teste", "Ovos");
        Object o = new Lot(1,mat,1);
        Object o2 = new Lot(2,mat,1);
        Lot instance = new Lot(1,mat2,1);
        boolean expResult = true;
        boolean result = instance.equals(o);
        assertEquals(expResult, result);
        
        expResult = false;
        result = instance.equals(o2);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of hashCode method, of class Lot.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Lot instance = new Lot();
        int expResult = 371;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of toString method, of class Lot.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Lot instance = new Lot();
        String expResult = "MealLot{ID: null, Código do lote: 0, Material= null, Quantidade=0}";
        String result = instance.toString();
        assertEquals(expResult, result);
        
    }
    
}
