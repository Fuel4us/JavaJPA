/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.booking;

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
public class DescriptionTest {
    
    public DescriptionTest() {
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
     * Test of equals method, of class Description.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Description instance = new Description("Eu nao gostei das espinhas no bacalhau");
        Description instance2 = new Description("Eu nao gostei das espinhas na picanha");
        Description instance3 = new Description("Eu nao gostei das espinhas na picanha");
        
        boolean expResult = false;
        boolean result = instance.equals(instance2);
        assertEquals(expResult, result);
        
        boolean expResult2 = true;
        boolean result2 = instance2.equals(instance3);
        assertEquals(expResult2, result2);
    }
    
}
