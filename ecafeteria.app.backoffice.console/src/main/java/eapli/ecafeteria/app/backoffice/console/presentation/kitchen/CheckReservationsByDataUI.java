package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.ecafeteria.application.kitchen.CheckBookingsByDataController;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Diogo Monteiro
 */
public class CheckReservationsByDataUI extends AbstractUI {
    CheckBookingsByDataController controller = new CheckBookingsByDataController();
    @Override
    protected boolean doShow() {

        int choice = Console.readInteger("What information do you want to use for the search?\n1 - Day\n2 - Meal Type\n3- Dish\n4 - Meal");
        if(choice>0&&choice<5){
            if(choice==1){
                displayBookingsByDate();
            }else if(choice ==2){
                displayBookingsByMealType();
            }else if(choice ==3){
                diplayBookingsByDish();
            }else if(choice ==4){
                displayBookingsByMeal();
            }
        }
        return true;
    }

    @Override
    public String headline() {
        return "Check Reservations By Data";
    }

    public void displayBookingsByDate(){
        List<Booking> list = controller.getAllBookings();
        List<Date> dateList = controller.displayBookingsDate();
        for (int i = 0; i < dateList.size(); i++) {
            System.out.println(i + 1 + " Booking Date: " + dateList.get(i) + "\n");
        }

        int choice = Console.readInteger("Which date do you want to see the respective Bookings?\n");
        if (choice > 0 && choice < dateList.size() + 1) {
            for (Booking booking :
                    list) {
                if (booking.day().equals(dateList.get(choice - 1))) {
                    System.out.println(booking + "\n");
                }
            }
        }
    }

    public void diplayBookingsByDish(){
        List<Booking> list = controller.getAllBookings();
        List<Dish> dishList = controller.displayBookingsByDish();
        for (int i = 0; i < dishList.size(); i++) {
            System.out.println(i + 1 + " Booking Dish: " + dishList.get(i) + "\n");
        }

        int choice = Console.readInteger("Which Dish do you want to see the respective Bookings?\n");
        if(choice>0&&choice<dishList.size()+1){
            for (Booking booking:
                    list) {
                if(booking.getMeal().getDish().equals(dishList.get(choice-1))){
                    System.out.println(booking+"\n");
                }
            }
        }
    }

    public void displayBookingsByMeal(){
        List<Booking> list = controller.getAllBookings();
        List<Meal> mealList = controller.displayBookingsByMeal();
        for (int i = 0; i < mealList.size(); i++) {
            System.out.println(i + 1 + " Booking Meal: " + mealList.get(i) + "\n");
        }

        int choice = Console.readInteger("Which Meal do you want to see the respective Bookings?\n");
        if(choice>0&&choice<mealList.size()+1){
            for (Booking booking:
                    list) {
                if(booking.getMeal().equals(mealList.get(choice-1))){
                    System.out.println(booking+"\n");
                }
            }
        }
    }

    public void displayBookingsByMealType(){
        List<Booking> list = controller.getAllBookings();
        List<MealType> mealTypeList = controller.displayBookingsByMealType();
        for (int i = 0; i < mealTypeList.size(); i++) {
            System.out.println(i + 1 + " Booking Meal Type: " + mealTypeList.get(i) + "\n");
        }
        int choice = Console.readInteger("Which Meal Type do you want to see the respective Bookings?\n");
        if(choice>0&&choice<mealTypeList.size()+1){
            for (Booking booking:
                    list) {
                if(booking.getMeal().getMealType().equals(mealTypeList.get(choice-1))){
                    System.out.println(booking+"\n");
                }
            }
        }else{
            System.out.println("That option doesn't exist");
            displayBookingsByMealType();
        }
    }
}
