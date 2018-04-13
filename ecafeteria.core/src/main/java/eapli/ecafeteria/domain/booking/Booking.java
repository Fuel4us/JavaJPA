package eapli.ecafeteria.domain.booking;

import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Mário Vaz
 */

public class Booking implements AggregateRoot<String>, Serializable{

    @Id
    @GeneratedValue
    private int bookingID;
    
    private String id;
    private CafeteriaUser user;
    private Meal meal;
    
    public Booking(CafeteriaUser user, Meal meal){
        this.id = user.id() + meal.toString();
        this.user = user;
        this.meal = meal;
    }
    /*
    public Date day() {
        return meal.getDate();
    }*/
    
    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public String id() {
        return this.id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.bookingID;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.user);
        hash = 97 * hash + Objects.hashCode(this.meal);
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
        if (this.bookingID != other.bookingID) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.meal, other.meal)) {
            return false;
        }
        return true;
    }
}
