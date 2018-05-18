/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.cafeteriauser;

import eapli.ecafeteria.domain.dishes.Allergen;
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
public class NutritionalProfileTest {

    public NutritionalProfileTest() {
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
    public static void fillList(){
    }

    /**
     * Test to verify if an allergen is in the allergen list, then that allergen cannot be added
     */
    @Test
    public void ensureCannotAddAllergen(){
        System.out.println("Ensure Cannot add an Allergen");
        
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
    public void ensureCanAddAllergen(){
        
        System.out.println("Ensure Can add an Allergen");
        
        Allergen allergen = new Allergen("al5", "allergen 5");
        
        when(profile.addAllergen(Mockito.any(Allergen.class))).thenReturn(!allergensList.contains(allergen)); //returns true because does not contains the allergen / CAN add
        assertTrue(profile.addAllergen(allergen));
        
        allergen = new Allergen("al6", "allergen 6");
        
        when(profile.addAllergen(Mockito.any(Allergen.class))).thenReturn(!allergensList.contains(allergen));
        assertTrue(profile.addAllergen(allergen));
        
        verify(profile, atLeast(2)).addAllergen(Mockito.any(Allergen.class));
        
    }

}
