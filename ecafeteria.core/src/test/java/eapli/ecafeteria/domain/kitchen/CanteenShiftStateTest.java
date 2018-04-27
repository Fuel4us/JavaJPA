package eapli.ecafeteria.domain.kitchen;

import static eapli.ecafeteria.domain.kitchen.CanteenShiftState.CLOSED;
import static eapli.ecafeteria.domain.kitchen.CanteenShiftState.OPEN;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class CanteenShiftStateTest {
    
    public CanteenShiftStateTest() {
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
     * Test of CanteenShiftState method, of class CanteenShiftState.
     */
    @Test
    public void testCanteenShiftState() {
        System.out.println("CanteenShiftState");
        CanteenShiftState[] expResult = {OPEN, CLOSED};
        CanteenShiftState[] result = CanteenShiftState.CanteenShiftState();
        assertArrayEquals(expResult, result);
    }
    
}
