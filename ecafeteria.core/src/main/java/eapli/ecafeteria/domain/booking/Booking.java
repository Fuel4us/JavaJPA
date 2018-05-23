package eapli.ecafeteria.domain.booking;

import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Mário Vaz changed by João Pereira <1150478@isep.ipp.pt>
 */
@Entity
public class Booking implements AggregateRoot<String>, Observable,Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingID;

    /**
     * Variable that defines the id of the booking.
     */
    private String id;

    /**
     * Instance variable that defines the cafeteria user.
     */
    @OneToOne(cascade = CascadeType.ALL)
    private CafeteriaUser user;

    /**
     * Instance variable that defines the meal.
     */
    @OneToOne(cascade = CascadeType.ALL)
    private Meal meal;

    /**
     * Instance variable that defines the booking state.
     */
    private BookingState bookingState;

    /**
     * Instance variable that defines the rating.
     */
    @OneToMany(cascade = CascadeType.ALL)
    private List<Rating> listRatings;

    /**
     * Instance variable that defines the complaint.
     */
    @OneToOne
    private Complaint complaint;

    /**
     * Instance variable that defines the date of the booking.
     */
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date bookingDate;

    /**
     * Empty constructor of the class for the ORM.
     */
    public Booking() {
    }

    /**
     * Complete constructor of the class.
     *
     * @param user
     * @param meal
     */
    public Booking(CafeteriaUser user, Meal meal) {
        this.id = user.id() + meal.toString();
        this.user = user;
        this.meal = meal;
        this.bookingState = BookingState.DELIVERED;
        this.bookingDate = new Date();
    }

    public Booking(CafeteriaUser user, Meal meal, Date time) {
        this.id = user.id() + meal.toString();
        this.user = user;
        this.meal = meal;
        this.bookingState = BookingState.RESERVED;
        this.bookingDate = time;
    }

    /**
     * Returns the day.
     *
     * @return
     */
    public Date day() {
        return this.bookingDate;
    }

    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public String id() {
        return this.id;
    }

    /**
     * Returns the id of the booking.
     *
     * @return
     */
    public String bookingId() {
        return Long.toString(bookingID);
    }

    /**
     * Returns the meal of the booking.
     *
     * @return
     */
    public Meal getMeal() {
        return this.meal;
    }

    /**
     * Returns the state of the booking.
     *
     * @return
     */
    public BookingState getBookingState() {
        return this.bookingState;
    }

    /**
     * Changes the current state of the booking.
     *
     * @param newState
     */
    public void changeState(BookingState newState) {
        this.bookingState = newState;
    }

    /**
     *
     * @return one String containing information regarding the meal.
     */
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

    /**
     *
     * @return true if state of the booking is reverved.
     */
    public boolean isReserved() {
        return bookingState.equals(BookingState.RESERVED);
    }

    /**
     *
     * @return true if state of the booking is cancelled.
     */
    public boolean isCancelled() {
        return bookingState.equals(BookingState.CANCELED);
    }

    /**
     *
     * @return true if state of the booking is delivered.
     */
    public boolean isDelivered() {
        return bookingState.equals(BookingState.DELIVERED);
    }

    /**
     * Adds the rating to a booking.
     *
     * @param rating
     */
    public void addRating(Rating rating) {
        this.listRatings.add(rating);
    }

    /**
     * Gets the n rating
     *
     * @param number
     * @return
     */
    public Rating getRating(int number) {
        if(listRatings.isEmpty() || number < 0)
            return null;
        return this.listRatings.get(number);
    }
    
    /**
     * Gets all ratings from this booking.
     *
     * @return
     */
    public List<Rating> getAllRatings() {
        List<Rating> listRatingAux = new ArrayList();
        
        if(listRatings.isEmpty())
            return listRatingAux;
        
        for(Rating r : listRatings)
            if(r != null)
                listRatingAux.add(r);
        
        return listRatingAux;
    }
    
    /**
     * Makes a complaint.
     *
     * @param complaint
     */
    public void createComplaint(Complaint complaint) {
        this.complaint = complaint;
    }

    /**
     * Gets the complaint.
     *
     * @return
     */
    public Complaint Complaint() {
        return this.complaint;
    }

    /**
     *
     * @return String with all parameters of the booking
     */
    @Override
    public String toString() {
        return "Booking{" + "bookingID=" + bookingID + ", id=" + id + ", user=" + user + ", meal=" + meal + ", bookingState=" + bookingState + ", rating=" + listRatings + '}';
    }

    @Override
    public void addListener(InvalidationListener listener) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
}
