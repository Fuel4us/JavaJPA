/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.cafeteriauser;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Leandro
 */
public class DefineUserBalancelimitsTest {
    
    private Profile profile;
    
    @Before
    public void setUp() {
        profile = new Profile();
    }
    
    /**
     * Test to ensure that limit cannot be negative
     */
    @Test
    public void ensureCannotAddNegativeLimitTest(){
        double newLimit = -1;
        
        boolean result = profile.addBalanceLimit(newLimit);
        boolean expected = false;
        
        assertEquals(expected,result);
    }
    
    /**
     * Teste to ensure it adds any positive quantity
     */
    @Test
    public void ensureItAddsPositiveLimitTest(){
        double newLimit = 1;
        
        boolean result = profile.addBalanceLimit(newLimit);
        boolean expected = true;
        
        assertEquals(expected,result);
    }
    
    
}
