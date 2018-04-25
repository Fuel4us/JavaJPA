/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.authz;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test for Class Reason.
 * @author pedromonteiro
 */
public class ReasonTest {
    

    
    /**
     * Test of constrcutor, of class Reason.
     */
    @Test
    public void testConstructor() {
        System.out.println("Test Constrcutor ----------------\n");
        
        
    }
    /**
     * Test of sameAs method, of class Reason.
     */
    @Test
    public void testSameAs() {
        System.out.println("Test Same As ----------------\n");
        
        String comment = "reason comment";
        Reason instance = new Reason(ReasonType.REASON1, comment);
        
        Object other = null;
        assertFalse(instance.sameAs(other));
        
        Reason toCompare = new Reason(ReasonType.REASON2, comment);
        assertFalse(instance.sameAs(toCompare));
        
        toCompare = new Reason(ReasonType.REASON1, comment);
        assertTrue(instance.sameAs(toCompare));
        
    }
    
    
    /**
     * Test of comment method, of class Reason.
     */
    @Test
    public void testComment() {
    System.out.println("Test Comment ----------------\n");
    
    String comment = "Reson Comment";
            
    Reason instance = new Reason(ReasonType.REASON1, comment);
    assertEquals(comment, instance.comment());
            
    
    }
    
    /**
     * Test of reasonType method, of class Reason.
     */
    @Test
    public void testReasonType() {
    System.out.println("Test Reason Type ----------------\n");
    
    String comment = "Reson Comment";
            
    Reason instance = new Reason(ReasonType.REASON1, comment);
    assertEquals(ReasonType.REASON1, instance.reasonType());
    
    instance = new Reason(ReasonType.REASON2, comment);
    assertEquals(ReasonType.REASON2, instance.reasonType());
    
    }

    
}
