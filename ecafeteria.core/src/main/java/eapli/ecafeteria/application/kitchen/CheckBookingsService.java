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


/**
 *
 * @author Diogo Monteiro
 */
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

    public List<Date> displayBookingsDate() {
        List<Booking> list = getAllReservations();
        List<Date> dateList = new ArrayList<>();

        if (list.size() > 0) {
            for (Booking booking :
                    list) {
                if (!dateList.contains(booking.day())) {
                    dateList.add(booking.day());
                }
            }
        }
        return dateList;
    }

    public List<Dish> displayBookingsByDish(){
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

        return dishList;
    }

    public List<Meal> displayBookingsByMeal(){
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

        return mealList;
    }

    public List<MealType> displayBookingsByMealType(){
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
        return mealTypeList;
    }
}
