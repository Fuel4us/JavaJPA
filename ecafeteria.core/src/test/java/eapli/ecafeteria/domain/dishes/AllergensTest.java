//package eapli.ecafeteria.domain.dishes;
//
//import java.util.HashSet;
//import java.util.Set;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
//
///**
// *
// * @author João Pereira <1150478@isep.ipp.pt>
// */
//public class AllergensTest {
//
//    private Set<String> allerg;
//
//    @Before
//    public void setUp() {
//        allerg.add("ovos");
//        allerg.add("mexilhões");
//    }
//
//    public AllergensTest() {
//    }
//
//    @BeforeClass
//    public static void setUpClass() {
//    }
//
//    @AfterClass
//    public static void tearDownClass() {
//    }
//
//    @After
//    public void tearDown() {
//    }
//
//    /**
//     * Test of getAllerg method, of class Allergens.
//     */
//    @Test
//    public void testGetAllerg() {
//        System.out.println("getAllerg");
//        Allergens instance = new Allergens(allerg);
//        Set<String> expResult = allerg;
//        Set<String> result = instance.getAllerg();
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of setAllerg method, of class Allergens.
//     */
//    @Test
//    public void testSetAllerg() {
//        System.out.println("setAllerg");
//        Set<String> allerg2 = new HashSet<>();
//        Allergens instance = new Allergens(allerg);
//        instance.setAllerg(allerg2);
//    }
//
//    /**
//     * @Test public void testId() { System.out.println("id"); Allergens instance
//     * = new Allergens(); Long expResult = null; Long result = instance.id();
//     * assertEquals(expResult, result); }
//     */
//    
//    /**
//     * Test of equals method, of class Allergens.
//     */
//    @Test
//    public void testEquals() {
//        System.out.println("equals");
//        Object obj = new Allergens(allerg);
//        Allergens instance = new Allergens(allerg);
//        boolean expResult = true;
//        boolean result = instance.equals(obj);
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of toString method, of class Allergens.
//     */
//    @Test
//    public void testToString() {
//        System.out.println("toString");
//        Allergens instance = new Allergens(allerg);
//        String expResult = "The allergens are: ovos, mexilhões";
//        String result = instance.toString();
//        assertEquals(expResult, result);
//    }
//
//}
