/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.authz;

import java.util.Calendar;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author pedromonteiro
 */
@Entity
public class UserState {

    public enum UserType {
        STAND_BY,
        ACCEPTED,
        DEACTIVATED,
        REJECTED
    }

    private UserType state;
    private Reason reason;
    @Temporal(TemporalType.DATE)
    private Calendar deactivatedOn;

    public UserState() {
        this.state = UserType.STAND_BY;
    }

    public boolean accept() {
        if (this.state == UserType.STAND_BY) {
            this.state = UserType.ACCEPTED;
            return true;
        }

        return false;
    }

    public boolean reject() {
        if (this.state == UserType.STAND_BY) {
            this.state = UserType.REJECTED;
            return true;
        }

        return false;
    }

    public boolean deactivate(Calendar deactivatedOn, ReasonType rt, String comment) {
        if (this.state == UserType.ACCEPTED) {
            this.state = UserType.DEACTIVATED;
            this.reason = new Reason(rt, comment);
            this.deactivatedOn = deactivatedOn;
            return true;
        }

        return false;
    }

    public Reason deactivatedReason() {
        return this.reason;
    }
    
    public UserType state(){
        return this.state;
    }

}
