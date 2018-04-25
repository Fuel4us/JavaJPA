package eapli.ecafeteria.domain.authz;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for ReasonType enumeration.
 * @author pedromonteiro
 */
public class ReasonTypeTest {
    
    /**
     * Test of values method, of class ReasonType.
     */
    @Test
    public void testValues() {
        System.out.println("Test Values ----------------\n");
        
        ReasonType[] expResult = {ReasonType.REASON1, ReasonType.REASON2};
        ReasonType[] result = ReasonType.values();
        
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of valueOf method, of class ReasonType.
     */
    @Test
    public void testValueOf() {
        System.out.println("Test Value Of ----------------\n");
        
        String name = ReasonType.REASON1.toString();
        ReasonType reason = ReasonType.valueOf(name);
        assertEquals(reason, ReasonType.REASON1);
        assertNotEquals(reason, ReasonType.REASON2);
        
        name = ReasonType.REASON2.toString();
        reason = ReasonType.valueOf(name);
        assertNotEquals(reason, ReasonType.REASON1);
        assertEquals(reason, ReasonType.REASON2);
    }

    
}
