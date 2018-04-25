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
        
        String reason1_name = "REASON1";
        String reason2_name = "REASON2";
        ReasonType reason = ReasonType.valueOf(reason1_name);
        assertEquals(ReasonType.REASON1, reason);
        
        reason = ReasonType.valueOf(reason2_name);
        assertEquals(ReasonType.REASON2, reason);
    }

    
}
