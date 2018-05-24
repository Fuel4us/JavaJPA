//package eapli.ecafeteria.application.ratings;
//
//import eapli.ecafeteria.domain.dishes.Dish;
//import eapli.ecafeteria.domain.meals.Meal;
//import eapli.ecafeteria.domain.meals.MealType;
//import eapli.ecafeteria.domain.menus.Menu;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
///**
// *
// * @author Pedro ALves
// */
//public class CheckRatingsControllerTest {
//
//    CheckRatingsController controller = mock(CheckRatingsController.class);
//
//    public CheckRatingsControllerTest() {
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
//    @Before
//    public void setUp() {
//    }
//
//    @After
//    public void tearDown() {
//    }
//
////    /**
////     * Test of getAllMenusDelivered method, of class CheckRatingsController.
////     */
////    @Test
////    public void testGetAllMenusDelivered() {
////        System.out.println("getAllMenusDelivered");
////        List<Menu> expResult = new ArrayList<>();
////        List<Menu> result = controller.getAllMenusDelivered();
////
////        when(controller.getAllMenusDelivered());
////        assertEquals(expResult.size(), result.size());
////    }
////
////    /**
////     * Test of getAllMealsDelivered method, of class CheckRatingsController.
////     */
////    @Test
////    public void testGetAllMealsDelivered() {
////        System.out.println("getAllMealsDelivered");
////        List<Meal> expResult = new ArrayList<>();
////        List<Meal> result = controller.getAllMealsDelivered();
////
////        when(controller.getAllMealsDelivered());
////        assertEquals(expResult.size(), result.size());
////    }
//
////    /**
////     * Test of getAllMealTypes method, of class CheckRatingsController.
////     */
////    @Test
////    public void testGetAllMealTypes() {
////        System.out.println("getAllMealTypes");
////        List<MealType> expResult = new ArrayList<>();
////        Iterable<MealType> result = controller.getAllMealTypes();
////
////       when(controller.getAllMealTypes());
////        assertEquals(expResult.size(), result.size());
////    }
////    /**
////     * Test of getAllDishes method, of class CheckRatingsController.
////     */
////    @Test
////    public void testGetAllDishes() {
////        System.out.println("getAllDishes");
////        CheckRatingsController instance = new CheckRatingsController();
////        Iterable<Dish> expResult = null;
////        Iterable<Dish> result = instance.getAllDishes();
////        assertEquals(expResult, result);
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
////
////    /**
////     * Test of getRatingsByDate method, of class CheckRatingsController.
////     */
////    @Test
////    public void testGetRatingsByDate() {
////        System.out.println("getRatingsByDate");
////        Date date = null;
////        CheckRatingsController instance = new CheckRatingsController();
////        List<Integer> expResult = null;
////        List<Integer> result = instance.getRatingsByDate(date);
////        assertEquals(expResult, result);
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
////
////    /**
////     * Test of getRatingsByDish method, of class CheckRatingsController.
////     */
////    @Test
////    public void testGetRatingsByDish() {
////        System.out.println("getRatingsByDish");
////        Dish dish = null;
////        CheckRatingsController instance = new CheckRatingsController();
////        List<Integer> expResult = null;
////        List<Integer> result = instance.getRatingsByDish(dish);
////        assertEquals(expResult, result);
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
////
////    /**
////     * Test of getRatingsByMealType method, of class CheckRatingsController.
////     */
////    @Test
////    public void testGetRatingsByMealType() {
////        System.out.println("getRatingsByMealType");
////        MealType mealType = null;
////        CheckRatingsController instance = new CheckRatingsController();
////        List<Integer> expResult = null;
////        List<Integer> result = instance.getRatingsByMealType(mealType);
////        assertEquals(expResult, result);
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
////
////    /**
////     * Test of getRatingsByMenu method, of class CheckRatingsController.
////     */
////    @Test
////    public void testGetRatingsByMenu() {
////        System.out.println("getRatingsByMenu");
////        Menu menu = null;
////        CheckRatingsController instance = new CheckRatingsController();
////        List<Integer> expResult = null;
////        List<Integer> result = instance.getRatingsByMenu(menu);
////        assertEquals(expResult, result);
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
////
////    /**
////     * Test of getRatingsByMeal method, of class CheckRatingsController.
////     */
////    @Test
////    public void testGetRatingsByMeal() {
////        System.out.println("getRatingsByMeal");
////        Meal meal = null;
////        CheckRatingsController instance = new CheckRatingsController();
////        List<Integer> expResult = null;
////        List<Integer> result = instance.getRatingsByMeal(meal);
////        assertEquals(expResult, result);
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
////
////    /**
////     * Test of showRatings method, of class CheckRatingsController.
////     */
////    @Test
////    public void testShowRatings() {
////        System.out.println("showRatings");
////        List<Integer> ratingsByDate = null;
////        CheckRatingsController instance = new CheckRatingsController();
////        instance.showRatings(ratingsByDate);
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
//}
