/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.cafeteriauser;

import eapli.ecafeteria.domain.dishes.Allergen;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author pedromonteiro
 */
public class ChangeAllergensTest {

    @Mock
    private NutritionalProfile profile_mock;
    
    private List<Allergen> allergensList;
    private Set<Allergen> allergensSet;
    private NutritionalProfile profile_unit;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        allergensList = new ArrayList<>();
        
        allergensList.add(new Allergen("al1", "allergen 1"));
        allergensList.add(new Allergen("al2", "allergen 2"));
        allergensList.add(new Allergen("al3", "allergen 3"));
        allergensList.add(new Allergen("al4", "allergen 4"));
        
        profile_unit = new NutritionalProfile();
        allergensSet = profile_unit.iterator_allergens();

    }

    /**
     * Test to verify if an allergen is in the allergen list, then that allergen cannot be added
     */
    @Test
    public void ensureCannotAddAllergenMockito() {
        System.out.println("Ensure Cannot add an Allergen Mockito/Functional");

        Allergen allergen = new Allergen("al1", "allergen 1");

        when(profile_mock.addAllergen(Mockito.any(Allergen.class))).thenReturn(!allergensList.contains(allergen)); //returns false because contains the allergen / canNOT add
        assertFalse(profile_mock.addAllergen(allergen));

        allergen = new Allergen("al2", "allergen 2");

        when(profile_mock.addAllergen(Mockito.any(Allergen.class))).thenReturn(!allergensList.contains(allergen));
        assertFalse(profile_mock.addAllergen(allergen));

        verify(profile_mock, atLeast(2)).addAllergen(Mockito.any(Allergen.class));

    }

    /**
     * Test to verify if an allergen is not in the allergen list, then that allergen can be added
     */
    @Test
    public void ensureCanAddAllergenMockito() {

        System.out.println("Ensure Can add an Allergen Mockito/Functional");

        Allergen allergen = new Allergen("al5", "allergen 5");

        when(profile_mock.addAllergen(Mockito.any(Allergen.class))).thenReturn(!allergensList.contains(allergen)); //returns true because does not contains the allergen / CAN add
        assertTrue(profile_mock.addAllergen(allergen));

        allergen = new Allergen("al6", "allergen 6");

        when(profile_mock.addAllergen(Mockito.any(Allergen.class))).thenReturn(!allergensList.contains(allergen));
        assertTrue(profile_mock.addAllergen(allergen));

        verify(profile_mock, atLeast(2)).addAllergen(Mockito.any(Allergen.class));

    }

    public void ensureCannotRemoveNonExisitingAllergenMockito() {

        System.out.println("Ensure Cannot remove an Allergen Mockito/Functional");

        Allergen allergen = new Allergen("al5", "allergen 5");

        when(profile_mock.removeAllergen(Mockito.any(Allergen.class))).thenReturn(allergensList.remove(allergen)); //returns false because does not contains the allergen / CANNOT remove
        assertFalse(profile_mock.removeAllergen(allergen));

        allergen = new Allergen("al6", "allergen 6");

        when(profile_mock.removeAllergen(Mockito.any(Allergen.class))).thenReturn(allergensList.remove(allergen)); //returns false because does not contains the allergen / CANNOT remove
        assertFalse(profile_mock.removeAllergen(allergen));

        verify(profile_mock, atLeast(2)).removeAllergen(Mockito.any(Allergen.class));
    }

    @Test
    public void ensureCanRemoveExisitingAllergenMockito() {
        System.out.println("Ensure Can remove an Allergen Mockito/Functional");

        Allergen allergen = new Allergen("al1", "allergen 1");

        when(profile_mock.removeAllergen(Mockito.any(Allergen.class))).thenReturn(allergensList.remove(allergen)); //returns true because contains the allergen, and will remove it / CAN remove
        assertTrue(profile_mock.removeAllergen(allergen));

        allergen = new Allergen("al2", "allergen 2");

        when(profile_mock.removeAllergen(Mockito.any(Allergen.class))).thenReturn(allergensList.remove(allergen)); //returns true because contains the allergen, and will remove it / CAN remove
        assertTrue(profile_mock.removeAllergen(allergen));

        verify(profile_mock, atLeast(2)).removeAllergen(Mockito.any(Allergen.class));
    }

    /**
     * Test to verify if an allergen is in the allergen list, then that allergen cannot be added
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureCannotAddExisitingAllergenUnit() {
        System.out.println("Ensure Cannot add an Allergen Unit");

        Allergen allergen = new Allergen("al1", "allergen 1");
        
        allergensSet = profile_unit.iterator_allergens();

        profile_unit.addAllergen(allergen);

        profile_unit.addAllergen(allergen);

        Allergen result = (Allergen) allergensSet.toArray()[0];

        assertTrue(result.equals(allergen) && allergensSet.size() == 1);

    }

    /**
     * Test to verify if an allergen is not in the allergen list, then that allergen can be added
     */
    @Test
    public void ensureCanAddAllergenUnit() {

        System.out.println("Ensure Can add an Allergen Unit Test");

        Allergen allergen = new Allergen("al5", "allergen 5");

        assertTrue(profile_unit.addAllergen(allergen));
    }

    @Test
    public void ensureCannotRemoveNonExisitingAllergenUnit() {

        System.out.println("Ensure Cannot remove an Allergen Unit Test");

        Allergen allergen = new Allergen("al5", "allergen 5");

        assertFalse(profile_unit.removeAllergen(allergen));

    }

    @Test
    public void ensureCanRemoveExisitingAllergenUnit() {
        System.out.println("Ensure Can remove an Allergen Unit Test");

        Allergen allergen = new Allergen("al1", "allergen 1");

        profile_unit.addAllergen(allergen);


        assertTrue(profile_unit.removeAllergen(allergen));

    }

    @Test
    public void ensureCannotRemoveAnAllergenIfListEmpty() {
        System.out.println("Ensure Cannot remove an Allergen if list is empty Unit Test");

        Allergen allergen = new Allergen("al1", "allergen 1");

        assertTrue(allergensSet.isEmpty() && !profile_unit.removeAllergen(allergen));
    }

}
