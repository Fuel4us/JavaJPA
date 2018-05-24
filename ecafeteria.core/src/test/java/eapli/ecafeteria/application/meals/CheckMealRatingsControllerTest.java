package eapli.ecafeteria.application.meals;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.meals.Meal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author @João Pereira_1150478@isep.ipp.pt
 */
public class CheckMealRatingsControllerTest {

    CheckMealRatingsController controller = mock(CheckMealRatingsController.class);

    @Test
    public void testGetBookingsDelivered() {
        List<Booking> list = new ArrayList<>();

        when(controller.getBookingsDelivered()).thenReturn(list);
        assertEquals(controller.getBookingsDelivered(), list);
    }

    /**
     * Test of getMealsOfBookings method, of class CheckMealRatingsController.
     */
    @Test
    public void testGetMealsOfBookings() {
        Set<Meal> list = new HashSet<>();

        Meal meal1 = new Meal();
        Meal meal2 = new Meal();
        Meal meal3 = new Meal();

        list.add(meal1);
        list.add(meal2);
        list.add(meal3);

        when(controller.getMealsOfBookings()).thenReturn(list);
        assertEquals(controller.getMealsOfBookings(), list);
    }

    /**
     * Test of getBookingsOfMeal method, of class CheckMealRatingsController.
     */
    @Test
    public void testGetBookingsOfMeal() {
        Iterable<Booking> list = new HashSet<>();

        Meal meal1 = new Meal();

        when(controller.getBookingsOfMeal(meal1)).thenReturn(list);
        assertEquals(controller.getBookingsOfMeal(meal1), list);
    }

    /**
     * Test of getNumberOfRatings method, of class CheckMealRatingsController.
     */
    @Test
    public void testGetNumberOfRatings() {
        int number = 0;

        Meal meal1 = new Meal();

        when(controller.getNumberOfRatings(meal1)).thenReturn(number);
        assertEquals(controller.getNumberOfRatings(meal1), number);
    }

    /**
     * Test of getComments method, of class CheckMealRatingsController.
     */
    @Test
    public void testGetComments() {
        String comment = " ";

        Meal meal1 = new Meal();

        when(controller.getComments(meal1)).thenReturn(comment);
        assertEquals(controller.getComments(meal1), comment);
    }

}
