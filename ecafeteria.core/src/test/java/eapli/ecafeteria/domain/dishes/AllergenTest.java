package eapli.ecafeteria.domain.dishes;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author @João Pereira_1150478@isep.ipp.pt
 */
public class AllergenTest {
    
    private String acronym = "cr";
    private String description = "crustaceos";
    
    public AllergenTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAcronym method, of class Allergen.
     */
    @Test
    public void testGetAcronym() {
        System.out.println("getAcronym");
        Allergen instance = new Allergen(acronym, description);
        String expResult = "cr";
        String result = instance.getAcronym();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDescription method, of class Allergen.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        Allergen instance = new Allergen(acronym, description);
        String expResult = "crustaceos";
        String result = instance.getDescription();
        assertEquals(expResult, result);
    }

    /**
     * Test of id method, of class Allergen.
     */
    @Test
    public void testId() {
        System.out.println("id");
        Allergen instance = new Allergen();
        Long expResult = instance.id();
        Long result = instance.id();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Allergen.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = new Allergen("cr", "crustaceos");
        Allergen instance = new Allergen(acronym, description);
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Allergen.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Allergen instance = new Allergen(acronym, description);
        String expResult = "crustaceos;";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
}
