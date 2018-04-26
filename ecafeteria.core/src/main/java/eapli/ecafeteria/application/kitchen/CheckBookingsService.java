package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.util.Console;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CheckBookingsService {

    private final BookingRepository rep = PersistenceContext.repositories().booking();

    public CheckBookingsService(){

    }

    public List<Booking>  getAllReservations(){
        Iterable<Booking> it = rep.findAll();
        List<Booking> list = new ArrayList<>();
        if(!it.iterator().hasNext()){
            System.out.println("There are no reservations");
        }else{
            for (Booking booking:
                it ) {
                list.add(booking);
            }
        }

        return list;
    }

    public void displayBookingsDate() {
        List<Booking> list = getAllReservations();
        List<Date> dateList = new ArrayList<>();

        if (list.size() > 0) {
            for (Booking booking :
                    list) {
                if (!dateList.contains(booking.day())) {
                    dateList.add(booking.day());
                }
            }
            for (int i = 0; i < dateList.size(); i++) {
                System.out.println(i + 1 + " Booking Date: " + dateList.get(i) + "\n");
            }

            int choice = Console.readInteger("Which date do you want to see the respective Bookings?\n");
            if (choice > 0 && choice < dateList.size() + 1){
                for (Booking booking :
                        list) {
                    if (booking.day().equals(dateList.get(choice - 1))) {
                        System.out.println(booking+"\n");
                    }
                }
        }else{
                System.out.println("That option doesn't exist");
                displayBookingsDate();
            }
    }
    }

    public void displayBookingsByDish(){
        List<Booking> list = getAllReservations();
        List<Dish> dishList = new ArrayList<>();

        if(list.size()>0){
            for (Booking booking:
                 list) {
                if(!dishList.contains(booking.getMeal().getDish())){
                    dishList.add(booking.getMeal().getDish());
                }
            }
        }
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
        }else{
            System.out.println("That option doesn't exist");
            displayBookingsByDish();
        }
    }

    public void displayBookingsByMeal(){
        List<Booking> list = getAllReservations();
        List<Meal> mealList = new ArrayList<>();

        if(list.size()>0){
            for (Booking booking:
                 list) {
                if (!mealList.contains(booking.getMeal())){
                    mealList.add(booking.getMeal());
                }
            }
        }
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
        }else{
            System.out.println("That option doesn't exist");
            displayBookingsByMeal();
        }
    }

    public void displayBookingsByMealType(){
        List<Booking> list = getAllReservations();
        List<MealType> mealTypeList = new ArrayList<>();
        if(list.size()>0){
            for (Booking booking:
                    list) {
                if (!mealTypeList.contains(booking.getMeal().getMealType())){
                    mealTypeList.add(booking.getMeal().getMealType());
                }
            }
        }

        for (int i = 0; i < mealTypeList.size(); i++) {
            System.out.println(i + 1 + " Booking Meal Type: " + mealTypeList.get(i) + "\n");
        }
        int choice = Console.readInteger("Which Meal Type do you want to see the respective Bookings?\n");
        if(choice>0&&choice<mealTypeList.size()+1){
            for (Booking booking:
                    list) {
                if(booking.getMeal().equals(mealTypeList.get(choice-1))){
                    System.out.println(booking+"\n");
                }
            }
        }else{
            System.out.println("That option doesn't exist");
            displayBookingsByMealType();
        }


    }
}
