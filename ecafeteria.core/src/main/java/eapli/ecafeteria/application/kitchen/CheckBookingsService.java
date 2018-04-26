package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.dishes.Dish;
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
                        System.out.println(booking);
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
            System.out.printf(i+1+" Booking Dish: "+dishList.get(i)+"\n");
        }

        int choice = Console.readInteger("Which date do you want to see the respective Bookings?\n");
        if(choice>0&&choice<dishList.size()+1){
            for (Booking booking:
                 list) {
                if(booking.getMeal().getDish().equals(dishList.get(choice-1))){
                    System.out.println(booking);
                }
            }
        }else{
            System.out.println("That option doesn't exist");
            displayBookingsByDish();
        }
    }
}
