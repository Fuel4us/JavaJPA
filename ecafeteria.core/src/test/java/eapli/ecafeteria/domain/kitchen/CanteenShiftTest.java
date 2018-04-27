package eapli.ecafeteria.domain.kitchen;

import eapli.ecafeteria.domain.finance.WorkSession;
import static eapli.ecafeteria.domain.kitchen.CanteenShiftState.OPEN;
import static eapli.ecafeteria.domain.kitchen.CanteenShiftState.CLOSED;
import java.util.Calendar;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class CanteenShiftTest {

    private Calendar c1 = Calendar.getInstance();
    private Calendar c2 = Calendar.getInstance();

    private WorkSession ws1 = new WorkSession();
    private WorkSession ws2 = new WorkSession();

    public CanteenShiftTest() {
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
     * Test of workSession method, of class CanteenShift.
     */
    @Test
    public void testWorkSession_EQUAL() {
        System.out.println("workSession");
        CanteenShift cs1 = new CanteenShift(c1, OPEN, ws1);
        WorkSession result = cs1.workSession();
        assertEquals(ws1, result);
    }
    
    /**
     * Test of workSession method, of class CanteenShift.
     */
    @Test
    public void testWorkSession_NOT_EQUAL() {
        System.out.println("workSession");
        CanteenShift cs1 = new CanteenShift(c1, OPEN, ws1);
        WorkSession result = cs1.workSession();
        assertNotEquals(ws2, result);
    }
    
    /**
     * Test of id method, of class CanteenShift.
     */
    @Test
    public void testId_EQUAL() {
        System.out.println("id");
        c1.set(2018, 0, 1);
        CanteenShift cs1 = new CanteenShift(c1, OPEN, ws1);
        Calendar result = cs1.id();
        assertEquals(c1, result);
    }
    
    /**
     * Test of id method, of class CanteenShift.
     */
    @Test
    public void testId_NOT_EQUAL() {
        System.out.println("id");
        c1.set(2018, 0, 1);
        CanteenShift cs1 = new CanteenShift(c1, OPEN, ws1);
        Calendar result = cs1.id();
        assertNotEquals(c2, result);
    }
    
    /**
     * Test of is method, of class CanteenShift.
     */
    @Test
    public void testIs_TRUE() {
        System.out.println("is");
        c1.set(2018, 0, 1);
        CanteenShift cs1 = new CanteenShift(c1, OPEN, ws1);
        c2.set(2018, 0, 1);
        boolean expResult = true;
        boolean result = cs1.is(c2);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of is method, of class CanteenShift.
     */
    @Test
    public void testIs_FALSE() {
        System.out.println("is");
        c1.set(2018, 0, 1);
        CanteenShift cs1 = new CanteenShift(c1, OPEN, ws1);
        c2.set(2018, 0, 2);
        boolean expResult = false;
        boolean result = cs1.is(c2);
        assertEquals(expResult, result);
    }

    /**
     * Test of sameAs method, of class CanteenShift.
     */
    @Test
    public void testSameAs_TRUE() {
        System.out.println("sameAs");
        CanteenShift cs1 = new CanteenShift(c1, OPEN, ws1);
        CanteenShift cs2 = new CanteenShift(c1, CLOSED, ws2);
        boolean expResult = true;
        boolean result = cs1.sameAs(cs2);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of sameAs method, of class CanteenShift.
     */
    @Test
    public void testSameAs_FALSE() {
        System.out.println("sameAs");
        CanteenShift cs1 = new CanteenShift(c1, OPEN, ws1);
        
        c2.set(2018, 0, 2);
        CanteenShift cs2 = new CanteenShift(c2, CLOSED, ws2);
        boolean expResult = false;
        boolean result = cs1.sameAs(cs2);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class CanteenShift.
     */
    @Test
    public void testEquals_1C() {
        System.out.println("equals");
        CanteenShift cs1 = new CanteenShift(c1, OPEN, ws1);
        boolean expResult = true;
        boolean result = cs1.equals(cs1);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of equals method, of class CanteenShift.
     */
    @Test
    public void testEquals_2C() {
        System.out.println("equals");
        CanteenShift cs1 = new CanteenShift(c1, OPEN, ws1);
        boolean expResult = false;
        boolean result = cs1.equals((Object)c1);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of equals method, of class CanteenShift.
     */
    @Test
    public void testEquals_3C_TRUE() {
        System.out.println("equals");
        CanteenShift cs1 = new CanteenShift(c1, OPEN, ws1);
        CanteenShift cs2 = new CanteenShift(c1, CLOSED, ws2);
        boolean expResult = true;
        boolean result = cs1.equals(cs2);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of equals method, of class CanteenShift.
     */
    @Test
    public void testEquals_3C_FALSE() {
        System.out.println("equals");
        CanteenShift cs1 = new CanteenShift(c1, OPEN, ws1);
        
        c2.set(2018, 0, 2);
        CanteenShift cs2 = new CanteenShift(c2, CLOSED, ws2);
        boolean expResult = false;
        boolean result = cs1.equals(cs2);
        assertEquals(expResult, result);
    }

    /**
     * Test of open method, of class CanteenShift.
     */
    @Test
    public void testOpen_TRUE() {
        System.out.println("open");
        CanteenShift cs1 = new CanteenShift(c1, CLOSED, ws1);
        boolean expResult = true;
        boolean result = cs1.open();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of open method, of class CanteenShift.
     */
    @Test
    public void testOpen_TRUE_2() {
        System.out.println("open");
        CanteenShift cs1 = new CanteenShift(c1, CLOSED, ws1);
        cs1.open();
        assertEquals(OPEN, cs1.canteenShiftState());
    }
    
    /**
     * Test of open method, of class CanteenShift.
     */
    @Test
    public void testOpen_FALSE() {
        System.out.println("open");
        CanteenShift cs1 = new CanteenShift(c1, OPEN, ws1);
        boolean expResult = false;
        boolean result = cs1.open();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of open method, of class CanteenShift.
     */
    @Test
    public void testOpen_FALSE_2() {
        System.out.println("open");
        CanteenShift cs1 = new CanteenShift(c1, OPEN, ws1);
        cs1.open();
        assertEquals(OPEN, cs1.canteenShiftState());
    }
    
    /**
     * Test of open method, of class CanteenShift.
     */
    @Test
    public void testClose_TRUE() {
        System.out.println("close");
        CanteenShift cs1 = new CanteenShift(c1, OPEN, ws1);
        boolean expResult = true;
        boolean result = cs1.close();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of open method, of class CanteenShift.
     */
    @Test
    public void testClose_TRUE_2() {
        System.out.println("close");
        CanteenShift cs1 = new CanteenShift(c1, OPEN, ws1);
        cs1.close();
        assertEquals(CLOSED, cs1.canteenShiftState());
    }
    
    /**
     * Test of open method, of class CanteenShift.
     */
    @Test
    public void testClose_FALSE() {
        System.out.println("close");
        CanteenShift cs1 = new CanteenShift(c1, CLOSED, ws1);
        boolean expResult = false;
        boolean result = cs1.close();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of open method, of class CanteenShift.
     */
    @Test
    public void testClose_FALSE_2() {
        System.out.println("close");
        CanteenShift cs1 = new CanteenShift(c1, CLOSED, ws1);
        cs1.close();
        assertEquals(CLOSED, cs1.canteenShiftState());
    }

}
