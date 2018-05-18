/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.meals;

import eapli.ecafeteria.application.meals.ReplyMealRatingCommentsController;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.Comment;
import eapli.ecafeteria.domain.booking.Rating;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.SelectWidget;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Berccar
 */
public class ReplyMealRatingCommentsUI {

    private final ReplyMealRatingCommentsController controller = new ReplyMealRatingCommentsController();

    protected boolean doShow() throws DataConcurrencyException, DataIntegrityViolationException {

        if (controller.getMealsOfBookings().isEmpty()) {
            System.out.println("There are no bookings or meals.");
        }

        final SelectWidget<Meal> selector = new SelectWidget<>("Select one meal to see the rating:", controller.getMealsOfBookings());
        selector.show();
        final Meal meal = selector.selectedElement();

        Iterable<Booking> bookingList = controller.getBookingsOfMeal(meal);
        List<Booking> commentedBookingList = new ArrayList<>();

        for (Booking booking : bookingList) {
            if (booking.getRating() != null && booking.getRating().getComment() != null) {
                commentedBookingList.add(booking);
            }
        }
        if (!commentedBookingList.isEmpty()) {

            Scanner scanner = new Scanner(System.in);
            String reply;

            for (Booking b : commentedBookingList) {
                System.out.println("\n## COMMENT of the Rating regarding the Meal with the ID  " + meal.getId() + ":\n");
                System.out.println("\n##");
                System.out.println("\n##" + b.getMeal().toString());
                System.out.println("\n##");
                System.out.println("\n##" + b.getRating().getComment());
                System.out.println("\n##");
                System.out.println("\n## REPLY: ");
                reply = scanner.nextLine();
                System.out.println("\n");
                b.getRating().getComment().changeAnswer(reply);
                controller.saveBooking(b);
            }
            return true;
        } else {
            return false;
        }
    }

}
