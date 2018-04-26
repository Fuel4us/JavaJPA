package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.PersistenceContext;

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

    public void displayBookingsDate(){
        List<Booking> list = getAllReservations();
        List<Date> dateList = new ArrayList<>();

        if(list.size()>0){
            for (Booking booking:
                    list) {
                if(!dateList.contains(booking.day())){
                    dateList.add(booking.day());
                }
            }
            for (int i = 0; i <dateList.size() ; i++) {
                System.out.println(i+1+" Date: "+dateList.get(i));
            }
        }
    }
}
