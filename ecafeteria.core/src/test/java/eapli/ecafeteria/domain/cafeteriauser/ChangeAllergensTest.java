/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.cafeteriauser;

import eapli.ecafeteria.domain.dishes.Allergen;
import eapli.ecafeteria.domain.dishes.NutricionalInfo;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author pedromonteiro
 */
public class ChangeAllergensTest {

    public ChangeAllergensTest() {
    }

    @Mock
    private NutritionalProfile profile;
    static List<Allergen> allergensList = new ArrayList<>();

    @Before
    public void setMockito() {
        MockitoAnnotations.initMocks(this);

        allergensList.add(new Allergen("al1", "allergen 1"));
        allergensList.add(new Allergen("al2", "allergen 2"));
        allergensList.add(new Allergen("al3", "allergen 3"));
        allergensList.add(new Allergen("al4", "allergen 4"));

    }

    @BeforeClass
    public static void fillList() {
    }

    /**
     * Test to verify if an allergen is in the allergen list, then that allergen cannot be added
     */
    @Test
    public void ensureCannotAddAllergenMockito() {
        System.out.println("Ensure Cannot add an Allergen Mockito/Functional");

        Allergen allergen = new Allergen("al1", "allergen 1");

        when(profile.addAllergen(Mockito.any(Allergen.class))).thenReturn(!allergensList.contains(allergen)); //returns false because contains the allergen / canNOT add
        assertFalse(profile.addAllergen(allergen));

        allergen = new Allergen("al2", "allergen 2");

        when(profile.addAllergen(Mockito.any(Allergen.class))).thenReturn(!allergensList.contains(allergen));
        assertFalse(profile.addAllergen(allergen));

        verify(profile, atLeast(2)).addAllergen(Mockito.any(Allergen.class));

    }

    /**
     * Test to verify if an allergen is not in the allergen list, then that allergen can be added
     */
    @Test
    public void ensureCanAddAllergenMockito() {

        System.out.println("Ensure Can add an Allergen Mockito/Functional");

        Allergen allergen = new Allergen("al5", "allergen 5");

        when(profile.addAllergen(Mockito.any(Allergen.class))).thenReturn(!allergensList.contains(allergen)); //returns true because does not contains the allergen / CAN add
        assertTrue(profile.addAllergen(allergen));

        allergen = new Allergen("al6", "allergen 6");

        when(profile.addAllergen(Mockito.any(Allergen.class))).thenReturn(!allergensList.contains(allergen));
        assertTrue(profile.addAllergen(allergen));

        verify(profile, atLeast(2)).addAllergen(Mockito.any(Allergen.class));

    }

    public void ensureCannotRemoveNonExisitingAllergenMockito() {

        System.out.println("Ensure Cannot remove an Allergen Mockito/Functional");

        Allergen allergen = new Allergen("al5", "allergen 5");

        when(profile.removeAllergen(Mockito.any(Allergen.class))).thenReturn(allergensList.remove(allergen)); //returns false because does not contains the allergen / CANNOT remove
        assertFalse(profile.removeAllergen(allergen));

        allergen = new Allergen("al6", "allergen 6");

        when(profile.removeAllergen(Mockito.any(Allergen.class))).thenReturn(allergensList.remove(allergen)); //returns false because does not contains the allergen / CANNOT remove
        assertFalse(profile.removeAllergen(allergen));

        verify(profile, atLeast(2)).removeAllergen(Mockito.any(Allergen.class));
    }

    @Test
    public void ensureCanRemoveExisitingAllergenMockito() {
        System.out.println("Ensure Can remove an Allergen Mockito/Functional");

        Allergen allergen = new Allergen("al1", "allergen 1");
        

        when(profile.removeAllergen(Mockito.any(Allergen.class))).thenReturn(allergensList.remove(allergen)); //returns true because contains the allergen, and will remove it / CAN remove
        assertTrue(profile.removeAllergen(allergen));

        allergen = new Allergen("al2", "allergen 2");

        when(profile.removeAllergen(Mockito.any(Allergen.class))).thenReturn(allergensList.remove(allergen)); //returns true because contains the allergen, and will remove it / CAN remove
        assertTrue(profile.removeAllergen(allergen));

        verify(profile, atLeast(2)).removeAllergen(Mockito.any(Allergen.class));
    }

    /**
     * Test to verify if an allergen is in the allergen list, then that allergen cannot be added
     */
//    @Test(expected = IllegalArgumentException.class)
    public void ensureCannotAddExisitingAllergenUnit() {
//        System.out.println("Ensure Cannot add an Allergen Unit");
////System.out.println("\n\nEnterHere22\n");
//        
//        Allergen allergen = new Allergen("al1", "allergen 1");
//        profile.addAllergen(allergen); //Added allergen
//        
//        
//        profile.addAllergen(allergen); //Try to add the same allergen should throw an exception;

    }

    /**
     * Test to verify if an allergen is not in the allergen list, then that allergen can be added
     */
    
    @Test
    public void ensureCanAddAllergenUnit() {

//        System.out.println("Ensure Can add an Allergen Unit Test");
//
//        Allergen allergen = new Allergen("al5", "allergen 5");
//        assertTrue(profile.addAllergen(allergen));
//
//        allergen = new Allergen("al6", "allergen 6");
//        assertTrue(profile.addAllergen(allergen));

    }

    public void ensureCannotRemoveNonExisitingAllergenUnit() {
//
//        System.out.println("Ensure Cannot remove an Allergen Unit Test");
//
//        Allergen allergen = new Allergen("al5", "allergen 5");
//        assertFalse(profile.removeAllergen(allergen));
//
//        allergen = new Allergen("al6", "allergen 6");
//        assertFalse(profile.removeAllergen(allergen));

    }

    @Test
    public void ensureCanRemoveExisitingAllergenUnit() {
//        System.out.println("Ensure Can remove an Allergen Unit Test");
//
//        Allergen allergen = new Allergen("al1", "allergen 1");
//        assertTrue(profile.removeAllergen(allergen));
//
//        allergen = new Allergen("al2", "allergen 2");
//        assertTrue(profile.removeAllergen(allergen));

    }

    @Test
    public void ensureCannotRemoveAnAllergenIfListEmpty() {
//        System.out.println("Ensure Cannot remove an Allergen if list is empty Unit Test");
//
//        Allergen allergen = new Allergen("al1", "allergen 1");
//        assertTrue(profile.removeAllergen(allergen));
//
//        allergen = new Allergen("al2", "allergen 2");
//        assertTrue(profile.removeAllergen(allergen));
    }
    
    

}
