/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.cafeteriauser;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author joaotiagow
 */
public class MecanographicNumberTest {
    
    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }
    
    @Test
    public void ensureStudentMecanographicNumberIsValid(){
        new MecanographicNumber("1600001");
    }
    
    @Test
    public void ensureEmployeeMecanographicNumberIsValid(){
        new MecanographicNumber("F123456");
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void ensureStudentMecanographicNumberOutOfRangeIsNotAllowed(){
        new MecanographicNumber("2000001");
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void ensureStudentMecanographicNumberWithLessNumbersIsNotAllowed(){
        new MecanographicNumber("16001");
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void ensureEmployeeMecanographicNumberWithLessNumbersIsNotAllowed(){
        new MecanographicNumber("F1237");
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void ensureEmployeeMecanographicNumberWithDifferentLetterIsNotAllowed(){
        new MecanographicNumber("E123456");
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void ensureNullMecanographicNumberIsNotAllowed(){
        new MecanographicNumber(null);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void ensureEmptyMecanographicNumberIsNotAllowed(){
        new MecanographicNumber("");
    }
}
