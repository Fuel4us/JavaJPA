/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.booking;

import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;
import java.util.Date;
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
    private Date complaintDate;
    
    protected Complaint(){
        //ORM
    }
    
    public Complaint(Meal meal, CafeteriaUser cafeteriaUser, Description description){
        this.meal = meal;
        this.cafeteriaUser = cafeteriaUser;
        this.description = description;
    }
    
    public Complaint(ComplaintState state){
        this.complaintState = ComplaintState.WAITING;
    }
    
    public ComplaintState state(){
        return complaintState;
    }

    @Override
    public boolean sameAs(Object other) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long id() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
