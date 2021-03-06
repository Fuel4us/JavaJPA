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
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bernardo Carreira 1150990
 */
public class ReplyMealRatingCommentsUI extends AbstractUI {

    private final ReplyMealRatingCommentsController controller = new ReplyMealRatingCommentsController();

    @Override
    protected boolean doShow() {

        if (controller.getMealsOfBookings().isEmpty()) {
            System.out.println("There are no bookings or meals.");
        }

        final SelectWidget<Meal> selector = new SelectWidget<>("Select one meal to see the rating:", controller.getMealsOfBookings());
        selector.show();
        final Meal meal = selector.selectedElement();

        Iterable<Booking> bookingList = controller.getBookingsOfMeal(meal);
        List<Booking> commentedBookingList = new ArrayList<>();

        for (Booking booking : bookingList) {
            if (booking.getRating(0) != null && booking.getRating(0).getComment() != null) {
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
                System.out.println("\n##" + b.getRating(0).getComment());
                System.out.println("\n##");
                System.out.println("\n## REPLY: ");
                reply = scanner.nextLine();
                System.out.println("\n");
                b.getRating(0).getComment().changeAnswer(reply);

                try {
                    controller.saveBooking(b);
                } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
                    Logger.getLogger(ReplyMealRatingCommentsUI.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            return true;
        } else {
            System.out.println("\nThere is no rating for that meal yet!");
            return false;
        }
    }

    @Override
    public String headline() {
        return "Reply to Comments";
    }

}
