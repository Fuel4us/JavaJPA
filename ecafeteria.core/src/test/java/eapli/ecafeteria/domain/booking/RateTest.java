/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.booking;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pedro Vieira 1160634
 */
public class RateTest {
    
    //same values than in Rate
    private static final int MIN_VAL = 1;
    private static final int MAX_VAL = 5;

    /**
     * Test of Rate can not be below minimum value constant, of class Rate.
     */
    @Test
    public void ensureCannotRateBelowMinimumValue() {
        System.out.println("-Ensure cannot rate below minimum value");
        
        try{
            Rate r = new Rate(MIN_VAL - 1);
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Test of Rate can not be above maximum value constant, of class Rate.
     */
    @Test
    public void ensureCannotRateAboveMaximumValue() {
        System.out.println("-Ensure cannot rate above maximum value");
        
        try{
            Rate r = new Rate(MAX_VAL + 1);
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Test of getScore method, of class Rate.
     */
    @Test
    public void ensureGetScoreWorks() {
        System.out.println("-Ensure getScore works");
        int expResult = 2;
        Rate r = new Rate(expResult);
        int result = r.getScore();
        assertEquals(result, expResult);
    }
    
    /**
     * Test of toString method, of class Rate.
     */
    @Test
    public void ensureToStringWorks() {
        System.out.println("-Ensure toString Works");
        int score = 1;
        String expResult = "Rate{" + "score=" + score + '}';
        Rate r = new Rate(score);
        String result = r.toString();
        assertEquals(result, expResult);
    }
    
}
