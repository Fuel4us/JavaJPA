/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.delivery;


import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Ana Mafalda Silva
 */
@Entity
public class Delievery implements Serializable{
 
     @Id
    @GeneratedValue
    private long id;

    @Temporal(TemporalType.DATE)
    private Calendar DeliveryDate;
    @ManyToOne
    private CafeteriaUser user;
    @OneToOne(cascade = CascadeType.ALL)
    private Booking booking;
    
    

    public Delievery() {
        //for JPA
    }

    public Delievery(Calendar DeliveryDate, CafeteriaUser user, Booking b) {
        this.DeliveryDate = DeliveryDate;
        this.user = user;
        this.booking= b;

    }

    public long getId() {
        return this.id;
    }


    public CafeteriaUser getUser() {
        return this.user;
    }

    public void setDeliveryDate(Calendar DeliveryDate) {
        this.DeliveryDate = DeliveryDate;
    }

  
    public void setId(long id) {
        this.id = id;
    }

}
