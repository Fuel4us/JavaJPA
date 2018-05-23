package eapli.ecafeteria.domain.booking;

import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Mário Vaz
 * changed by João Pereira <1150478@isep.ipp.pt>
 */
@Entity
public class Booking implements AggregateRoot<String>, Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingID;
    
    private String id;
    @OneToOne
    private CafeteriaUser user;
    @OneToOne
    private Meal meal;
    private BookingState bookingState;
    @OneToOne
    private Rating rating;
    @OneToOne
    private Complaint complaint;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date bookingDate;

    public Booking() {
    }
    
    public Booking(CafeteriaUser user, Meal meal){
        this.id = user.id() + meal.toString();
        this.user = user;
        this.meal = meal;
        this.bookingState = BookingState.DELIVERED;
        this.bookingDate = new Date();
        this.complaint = new Complaint(ComplaintState.WAITING);
    }
    
    public Date day() {
        return this.bookingDate;
    }
    
    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    public String id() {
        return this.id;
    }
    
    public String bookingId() {
        return Long.toString(bookingID);
    }

    public Meal getMeal(){ return this.meal;}

    public BookingState getBookingState(){
        return this.bookingState;
    }
    
    public void changeState(BookingState newState) {
        this.bookingState = newState;
    }
    
    public String sumaryList() {
        return String.format("%s", meal.toString());
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.bookingID);
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.user);
        hash = 97 * hash + Objects.hashCode(this.meal);
        hash = 97 * hash + Objects.hashCode(this.bookingDate);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Booking other = (Booking) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        //Testing dates
//        if (!Objects.equals(this.bookingDate, other.bookingDate)) {
//            return false;
//        }
        if (this.bookingState != other.bookingState) {
            return false;
        }
        return Objects.equals(this.meal, other.meal);
    }
    
    
    
    public boolean isReserved() {
        return bookingState.equals(BookingState.RESERVED);
    }
    
    public boolean isCancelled() {
        return bookingState.equals(BookingState.CANCELED);
    }
    
    public boolean isDelivered() {
        return bookingState.equals(BookingState.DELIVERED);
    }
    
    public void rating(Rating rating) {
        this.rating = rating;
    }
    
    public Rating getRating() {
        return this.rating;
    }
    
    public boolean createComplaint(Complaint complaint) {
        if(complaint.state().equals(ComplaintState.WAITING)){
            this.complaint = complaint;
            return true;    
        }
        return false;
    }
    
    public Complaint Complaint() {
        return this.complaint;
    }
    
    @Override
    public String toString() {
        return "Booking{" + "bookingID=" + bookingID + ", id=" + id + ", user=" + user + ", meal=" + meal + ", bookingState=" + bookingState + ", rating=" + rating + '}';
    }
}
