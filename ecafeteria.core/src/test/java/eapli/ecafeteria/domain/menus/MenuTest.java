package eapli.ecafeteria.domain.menus;

import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.dishes.DishType;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.framework.domain.Designation;
import eapli.framework.domain.money.Money;
import eapli.framework.util.DateTime;
import java.util.Date;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pedro Alves <1150372@isep.ipp.pt>
 */
public class MenuTest {

    private DishType peixe;
    private Meal meal;
    private Dish dish;
    private Designation name;
    Date date1;
    Date date2;
    Date date3;

    public MenuTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        peixe = new DishType("Peixe", "Peixe");
        name = Designation.valueOf("TESTE");
        dish = new Dish(peixe, name, Money.euros(5));
        meal = new Meal(MealType.DINNER, DateTime.newCalendar(DateTime.currentYear(), DateTime.currentMonth() + 1, 5).getTime(), dish);
        date1 = DateTime.newCalendar(DateTime.currentYear(), DateTime.currentMonth() + 1, 1).getTime();
        date2 = DateTime.newCalendar(DateTime.currentYear(), DateTime.currentMonth() + 3, 1).getTime();
        date3 = DateTime.newCalendar(DateTime.currentYear(), DateTime.currentMonth() + 6, 1).getTime();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getStartDate method, of class Menu.
     */
    @Test
    public void testGetStartDate() {
        System.out.println("getStartDate");
        Menu instance = new Menu(date1, date2, name);
        Date expResult = date1;
        Date result = instance.getStartDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEndDate method, of class Menu.
     */
    @Test
    public void testGetEndDate() {
        System.out.println("getEndDate");
        Menu instance = new Menu(date2, date3, name);
        Date expResult = date3;
        Date result = instance.getEndDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of isPublished method, of class Menu.
     */
    @Test
    public void testIsPublished() {
        System.out.println("isPublished");
        Menu instance = new Menu(date1, date2, name);
        boolean expResult = false;
        boolean result = instance.isPublished();
        assertEquals(expResult, result);
    }

    /**
     * Test of isPublished method, of class Menu.
     */
    @Test
    public void testIsPublished2() {
        System.out.println("isPublished2");
        Menu instance = new Menu(date2, date3, name);
        boolean expResult = true;
        instance.publishMenu();
        boolean result = instance.isPublished();
        assertEquals(expResult, result);
    }

    /**
     * Test of getName method, of class Menu.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Menu instance = new Menu(date2, date3, name);
        Designation expResult = name;
        Designation result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class Menu.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        Designation name2 = Designation.valueOf("Alterado Test");
        Menu instance = new Menu(date2, date3, name);
        boolean expResult = true;
        boolean result = instance.setName(name2);
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class Menu.
     */
    @Test
    public void testSetName2() {
        System.out.println("setName");
        Designation name2 = null;
        Menu instance = new Menu(date2, date3, name);
        boolean expResult = false;
        boolean result = instance.setName(name2);
        assertEquals(expResult, result);
    }

    /**
     * Test of setStartDate method, of class Menu.
     */
    @Test
    public void testSetStartDate() {
        System.out.println("setStartDate");
        Date date = DateTime.newCalendar(DateTime.currentYear(), DateTime.currentMonth() + 1, 5).getTime();
        Menu instance = new Menu(date1, date3, name);
        boolean expResult = true;
        boolean result = instance.setStartDate(date);
        assertEquals(expResult, result);
    }

    /**
     * Test of setEndDate method, of class Menu.
     */
    @Test
    public void testSetEndDate() {
        System.out.println("setEndDate");
        Date date = date3;
        Menu instance = new Menu(date1, date2, name);
        boolean expResult = true;
        boolean result = instance.setEndDate(date);
        assertEquals(expResult, result);
    }

    /**
     * Test of setEndDate method, of class Menu.
     */
    @Test
    public void testSetEndDate2() {
        System.out.println("setEndDate");
        Date date = date1;
        Menu instance = new Menu(date2, date3, name);
        boolean expResult = false;
        boolean result = instance.setEndDate(date);
        assertEquals(expResult, result);
    }

    /**
     * Test of toogleState method, of class Menu.
     */
    @Test
    public void testToogleState() {
        System.out.println("toogleState");
        Menu instance = new Menu(date1, date2, name);
        boolean expResult = true;
        boolean result = instance.toogleState();
        assertEquals(expResult, result);
    }

}
