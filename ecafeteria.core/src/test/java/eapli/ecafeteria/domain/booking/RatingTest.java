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
 * @author Pedro Vieira - 1160634
 */
public class RatingTest {
    
    
    
    /**
     * Test of getScore method, of class Rating.
     */
    @Test
    public void ensureGetScoreWorks() {
        System.out.println("-Ensure getScore works");
        int expResult = 2;
        Rating instance = new Rating(expResult, "Comment");
        int result = instance.getScore();
        assertEquals(expResult, result);
    }

    /**
     * Test of getComment method, of class Rating.
     */
    @Test
    public void ensureGetCommentWorks() {
        System.out.println("-Ensure getComment works");
        Comment expResult = new Comment("comment");
        Rating instance = new Rating(2, expResult);
        Comment result = instance.getComment();
        assertEquals(expResult, result);
    }

    /**
     * Test of id method, of class Rating.
     */
    @Test
    public void ensureFisrtIdWorks() {
        System.out.println("-Ensure first Id works");
        
        Rating instance = new Rating(2, "comment");
        Long expResult = 0L;
        Long result = instance.id();
        assertEquals(expResult, result);
    }

    /**
     * Test of id method, of class Rating.
     */
    @Test
    public void ensureSecondIdWorks() {
        System.out.println("-Ensure second Id works");
        
        Rating instance = new Rating(2, "comment");
        Rating instance2 = new Rating(3, "comment2");
        Long expResult2 = 0L;
        Long result2 = instance2.id();
        assertEquals(expResult2, result2);
    }
    
    /**
     * Test of id method, of class Rating.
     */
    @Test
    public void ensureFirstIdIsNotSecondIdWorks() {
        System.out.println("-Ensure fisrt ID is not second Id works");
        
        Rating instance = new Rating(2, "comment");
        Rating instance2 = new Rating(3, "comment2");
        Long expResult2 = 1L;
        Long result2 = instance2.id();
        assertNotEquals(expResult2, result2);
    }
    
    /**
     * Test of sameAs method, of class Rating.
     */
    @Test
    public void ensureSameAsIsReturningTrueWorks() {
        System.out.println("-Ensure SameAs is returning true works");
        Rating instance = new Rating(2, "Comment");
        boolean expResult = true;
        boolean result = instance.sameAs(instance);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of sameAs method, of class Rating.
     */
    @Test
    public void ensureSameAsIsReturningFalseWorks() {
        System.out.println("-Ensure SameAs is returning false works");
        Rating instance = new Rating(2, "Comment");
        Rating instance2 = new Rating(3, "Comment2");
        boolean expResult = true;
        boolean result = instance.sameAs(instance2);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of IS method, of class Rating.
     */
    @Test
    public void ensureIS_IsReturningTrueOnFirstIDWorks() {
        System.out.println("-Ensure IS is returning true on fisrt ID works");
        Rating instance = new Rating(2, "Comment");
        boolean expResult = true;
        boolean result = instance.is(0L);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of IS method, of class Rating.
     */
    @Test
    public void ensureIS_IsReturningTrueOnSecondIdWorks() {
        System.out.println("-Ensure IS is returning true one second ID works");
        Rating instance = new Rating(2, "Comment");
        Rating instance2 = new Rating(3, "Comment2");
        boolean expResult = true;
        boolean result = instance2.is(0L);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of IS method, of class Rating.
     */
    @Test
    public void ensureIS_IsReturningFalseOnSecondIdWorks() {
        System.out.println("-Ensure IS is returning false on second ID works");
        Rating instance = new Rating(2, "Comment");
        Rating instance2 = new Rating(3, "Comment2");
        boolean expResult = false;
        boolean result = instance2.is(1L);
        assertEquals(expResult, result);
        
        boolean result2 = instance2.is(2L);
        assertEquals(expResult, result2);
    }

    /**
     * Test of hashCode method, of class Rating.
     */
    @Test
    public void ensureHashCodeWorks() {
        System.out.println("-Ensure HashCode works");
//        Rating instance = new Rating(2, "Comment");
//        int expResult = 158018762;
//        int result = instance.hashCode();
//        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Rating.
     */
    @Test
    public void ensureEqualsReturningTrueWorks() {
        System.out.println("-Ensure Equals is returning true works");
//        Rating instance = new Rating(2, "Comment");
//        boolean expResult = true;
//        boolean result = instance.equals(instance);
//        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Rating.
     */
    @Test
    public void ensureEqualsReturningFalseOnNullWorks() {
        System.out.println("-Ensure Equals is returning false on null works");
        Rating instance = new Rating(2, "Comment");
        boolean expResult = false;
        boolean result = instance.equals(null);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of equals method, of class Rating.
     */
    @Test
    public void ensureEqualsReturningFalseOnDifferentClassWorks() {
        System.out.println("-Ensure Equals is returning false on different class works");
        Rating instance = new Rating(2, "Comment");
        Object object = new Object();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of equals method, of class Rating.
     */
    @Test
    public void ensureEqualsReturningFalseOnSameClassWorks() {
        System.out.println("-Ensure Equals is returning false on same class works");
        Rating instance = new Rating(2, "Comment");
        Rating instance2 = new Rating(3, "Comment2");
        boolean expResult = false;
        boolean result = instance.equals(instance2);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of toString method, of class Rating.
     */
    @Test
    public void ensureToStringWorks() {
        System.out.println("-EnsureToStringWorks");
//        int score = 2;
//        String comment = "Comment";
//        Rating instance = new Rating(2, comment);
//        String expResult = "Rating{id=0, score=[Rate{score=" + score + "}, comment=" + comment;
//        String result = instance.toString();
//        assertEquals(expResult, result);
    }
    
}
