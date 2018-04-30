/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;


import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import eapli.ecafeteria.domain.delivery.Delievery;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.DelieveryRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Calendar;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ana Mafalda Silva
 */
public class DeliverBookingController implements Controller {
   
    private final BookingRepository repositoryBooking = PersistenceContext.repositories().booking();
    final DelieveryRepository deliveryRepo = PersistenceContext.repositories().delieveries();
    private final CafeteriaUserRepository repositoryUser = PersistenceContext.repositories().cafeteriaUsers();
    MecanographicNumber mecaNumber;
    Booking booking;
    CafeteriaUser user;
    
    

    public DeliverBookingController() {
    }
    
    public CafeteriaUser cafetariaUserToSee(String m){
        MecanographicNumber mec = new MecanographicNumber(m);
        Optional<CafeteriaUser> cUser = PersistenceContext.repositories().cafeteriaUsers().findByMecanographicNumber(mec);
        
        if (cUser != null){
            this.user = cUser.get();
            System.out.println("CafeteriaUser: " + this.user.mecanographicNumber().toString() + "\n");
            return cUser.get();
        }
        System.out.println("Not Found");
        return null;
    }
    
    
    public boolean showBookedBookings(){
        if(repositoryBooking.listBookedMealsByCUser(this.user) != null){
            Iterable <Booking> bookings = repositoryBooking.listBookedMealsByCUser(this.user);
            System.out.println("Your Bookings: \n");
            for(Booking booking : bookings){
                System.out.println(booking.toString() + "/n");
            }
            return true;
        } else {
            System.out.println("\n You currently have no booked bookings");
        }
        return false;
    }
    
    
    public boolean choice(Long choice){
        int flag = 0;
        Iterable<Booking> bookings = repositoryBooking.listBookedMealsByCUser(this.user);
        for(Booking booking : bookings){
            if(booking.id().equals(choice)){
                flag = 1;
                System.out.println("\n" + booking.toString() + "\n");
                this.booking = booking;
                return true;
            }
        }
        if(flag == 1){
           System.out.println("\n Choice not found!"); 
        }
        return false;
    }
    
    /*public boolean registerDelivery() throws DataConcurrencyException {

        Delievery d = new Delievery(Calendar.getInstance(), user, bookingMeal);
        try {
            bookingMeal.updateBookingMealStatus(BookingMealStatus.DELIVERED);
            deliveryRepo.save(d);
        } catch (DataIntegrityViolationException ex) {
            System.out.println("CANNOT REGISTER DELIVERY!!!");
            Logger.getLogger(RegisterMealDelieveryController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;
    }

    public void setBooking(BookingMeal b) {
        this.bookingMeal = b;
    }
*/
    public boolean registerDelivery() throws DataConcurrencyException, DataIntegrityViolationException {
        
        Delievery d = new Delievery(Calendar.getInstance(), user, booking);
        try {
            booking.changeState(BookingState.DELIVERED);
            deliveryRepo.save(d);      
        } catch (DataIntegrityViolationException ex){
            System.out.println("CANNOT REGISTER DELIVERY!!!");
            Logger.getLogger(DeliverBookingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return true;
    }
    
    public void setBooking(Booking b) {
        this.booking = b;
    }

    
}
