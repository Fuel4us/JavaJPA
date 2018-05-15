package eapli.ecafeteria.domain.meals;

import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.dishes.DishType;
import eapli.ecafeteria.domain.menus.Menu;
import eapli.ecafeteria.persistence.DishTypeRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.domain.Designation;
import eapli.framework.domain.money.Money;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jreis22
 * @EDIT Pedro Alves <1150372@isep.ipp.pt>
 */
public class MealTest {

    private DishType peixe;

    public MealTest() {
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
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testChangeDishTo() {
        System.out.println("changeDishTo");
        final Designation name = Designation.valueOf("Test Change");
        Dish dish = new Dish(peixe, name, Money.euros(5));

        Meal meal = new Meal(MealType.DINNER, new Date(), dish);
        Dish d = new Dish(new DishType("Carne", "n/a"), Designation.valueOf("Bacalhau"), new Money(2.14, Currency.getInstance(Locale.GERMANY)));

        boolean aux = meal.changeDishTo(d);

        assertEquals(aux, true);
    }

    @Test
    public void testChangeMealTypeTo() {
        System.out.println("changeMealTypeTo");
        final Designation name = Designation.valueOf("Test Change");
        Dish dish = new Dish(peixe, name, Money.euros(5));

        Meal meal = new Meal(MealType.DINNER, new Date(), dish);
        Dish d = new Dish(new DishType("Carne", "n/a"), Designation.valueOf("Bacalhau"), new Money(2.14, Currency.getInstance(Locale.GERMANY)));

        boolean aux = meal.changeMealTypeTo(MealType.LUNCH);

        assertEquals(aux, true);
        assertEquals(meal.getMealType(), MealType.LUNCH);
    }

    @Test
    public void testChangeMealDateTo() {
        System.out.println("changeMealDateTo");
        final Designation name = Designation.valueOf("Test Change");
        Dish dish = new Dish(peixe, name, Money.euros(5));

        Meal meal = new Meal(MealType.DINNER, new Date(), dish);
        Dish d = new Dish(new DishType("Carne", "n/a"), Designation.valueOf("Bacalhau"), new Money(2.14, Currency.getInstance(Locale.GERMANY)));

        boolean aux = meal.changeMealDate(new Date(12, 12, 2018));

        assertEquals(aux, true);
    }

    @Test
    public void testInsertMenu() {
        System.out.println("insertMenu");
        final Designation name = Designation.valueOf("Test Change");
        Dish dish = new Dish(peixe, name, Money.euros(5));

        Menu menu = new Menu(new Date(11, 12, 2018), new Date(12, 12, 2018));

        Meal meal = new Meal(MealType.DINNER, new Date(), dish);
        Dish d = new Dish(new DishType("Carne", "n/a"), Designation.valueOf("Bacalhau"), new Money(2.14, Currency.getInstance(Locale.GERMANY)));

        boolean aux = meal.insertMenu(menu);

        assertEquals(aux, false);
    }

    @Test
    public void testInsertMenu2() {
        System.out.println("insertMenu");
        final Designation name = Designation.valueOf("Test Change");
        Dish dish = new Dish(peixe, name, Money.euros(5));

        Menu menu = new Menu(new Date(11, 12, 2018), new Date(13, 12, 2018));

        Meal meal = new Meal(MealType.DINNER, new Date(12, 12, 2018), dish);
        Dish d = new Dish(new DishType("Carne", "n/a"), Designation.valueOf("Bacalhau"), new Money(2.14, Currency.getInstance(Locale.GERMANY)));

        boolean aux = meal.insertMenu(menu);

        assertEquals(aux, true);
    }

    @Test
    public void testMenuAtribuido() {
        System.out.println("MenuAtribuido");
        final Designation name = Designation.valueOf("Test Change");
        Dish dish = new Dish(peixe, name, Money.euros(5));

        Menu menu = new Menu(new Date(11, 12, 2018), new Date(12, 12, 2018));

        Meal meal = new Meal(MealType.DINNER, new Date(), dish);
        Dish d = new Dish(new DishType("Carne", "n/a"), Designation.valueOf("Bacalhau"), new Money(2.14, Currency.getInstance(Locale.GERMANY)));

        meal.insertMenu(menu);

        boolean aux = meal.menuAtribuído();

        assertEquals(aux, false);
    }

    @Test
    public void testMenuAtribuido2() {
        System.out.println("MenuAtribuido");
        final Designation name = Designation.valueOf("Test Change");
        Dish dish = new Dish(peixe, name, Money.euros(5));

        Menu menu = new Menu(new Date(11, 12, 2018), new Date(13, 12, 2018));

        Meal meal = new Meal(MealType.DINNER, new Date(11, 12, 2018), dish);
        Dish d = new Dish(new DishType("Carne", "n/a"), Designation.valueOf("Bacalhau"), new Money(2.14, Currency.getInstance(Locale.GERMANY)));

        meal.insertMenu(menu);

        boolean aux = meal.menuAtribuído();

        assertEquals(aux, true);
    }

    /**
     * Test of getMealDate method, of class Meal.
     */
    @Test
    public void testGetDate() {
        System.out.println("getDate");
        Date expResult = new Date(2018, 4, 24);
        Dish d = new Dish(new DishType("Carne", "n/a"), Designation.valueOf("Bacalhau"), new Money(2.14, Currency.getInstance(Locale.GERMANY)));
        Meal instance = new Meal(MealType.LUNCH, expResult, d);

        Date result = instance.getMealDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of compareDates method, of class Meal.
     */
    @Test
    public void testCompareDates() {
        System.out.println("compareDates");
        Date date1 = new Date(2018, 4, 24);
        Date date2 = new Date(2017, 4, 24);
        Date date3 = new Date(2019, 4, 24);
        Date date4 = new Date(2014, 4, 24);
        Date date5 = new Date(2018, 3, 24);
        Date date6 = new Date(2018, 5, 24);
        Date date7 = new Date(2018, 4, 23);
        Date date8 = new Date(2018, 4, 25);

        Dish d = new Dish(new DishType("Carne", "n/a"), Designation.valueOf("Bacalhau"), new Money(2.14, Currency.getInstance(Locale.GERMANY)));
        Meal meal1 = new Meal(MealType.LUNCH, date1, d);
        Meal meal2 = new Meal(MealType.LUNCH, date2, d);
        Meal meal3 = new Meal(MealType.LUNCH, date3, d);
        Meal meal4 = new Meal(MealType.LUNCH, date4, d);
        Meal meal5 = new Meal(MealType.LUNCH, date5, d);
        Meal meal6 = new Meal(MealType.LUNCH, date6, d);
        Meal meal7 = new Meal(MealType.LUNCH, date7, d);
        Meal meal8 = new Meal(MealType.LUNCH, date8, d);

        List<Meal> result = new ArrayList<>();
        result.add(meal1);
        result.add(meal2);
        result.add(meal3);
        result.add(meal4);
        result.add(meal5);
        result.add(meal6);
        result.add(meal7);
        result.add(meal8);

        Collections.sort(result, Meal.compareDates());
        List<Meal> expResult = new ArrayList<>();
        expResult.add(meal4);
        expResult.add(meal2);
        expResult.add(meal5);
        expResult.add(meal7);
        expResult.add(meal1);
        expResult.add(meal8);
        expResult.add(meal6);
        expResult.add(meal3);

        for (int i = 0; i < result.size(); i++) {
            assertEquals(expResult.get(i), result.get(i));
        }
    }

}
