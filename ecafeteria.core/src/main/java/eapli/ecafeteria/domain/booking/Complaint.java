/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.booking;

import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.domain.ddd.AggregateRoot;
import eapli.framework.util.DateTime;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Hernani Gil
 */

@Entity
public class Complaint implements AggregateRoot<Long>, Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @OneToOne
    private Meal meal;
    
    @OneToOne
    private CafeteriaUser cafeteriaUser;
    @Embedded
    private Description description;
    
    
    private ComplaintState complaintState;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar complaintDate;
    
    protected Complaint(){
        //ORM
    }
    
    public Complaint(Meal meal, CafeteriaUser cafeteriaUser, Description description, ComplaintState state){
        this.meal = meal;
        this.cafeteriaUser = cafeteriaUser;
        this.description = description;
        this.complaintState = state;
        this.complaintDate = DateTime.now();
    }
    
    public ComplaintState state(){
        return complaintState;
    }
    
    public Meal meal(){
        return this.meal;
    }

    @Override
    public Long id() {
        return id;
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof Complaint)) {
            return false;
        }

        final Complaint that = (Complaint) other;
        return meal.equals(that.meal)
                && cafeteriaUser.equals(that.cafeteriaUser) && description.equals(that.description);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Complaint other = (Complaint) obj;
        if (!Objects.equals(this.meal, other.meal)) {
            return false;
        }
        if (!Objects.equals(this.cafeteriaUser, other.cafeteriaUser)) {
            return false;
        }
  
        return true;
    }

    @Override
    public String toString() {
        String cafeteriaUserString;
        if(!complaintState.equals(complaintState.ANONYMOUS)){     
            cafeteriaUserString = "Anomymous";
        }else{
            cafeteriaUserString = cafeteriaUser.mecanographicNumber().toString();
        }
        
        return "Complaint{" + "id=" + id + ", meal=" + meal + ", cafeteriaUser=" + cafeteriaUserString + ", description=" + description + '}';
    }
    
    
}
