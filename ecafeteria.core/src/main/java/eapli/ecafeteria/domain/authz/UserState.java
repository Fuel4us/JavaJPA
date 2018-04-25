package eapli.ecafeteria.domain.authz;

import eapli.framework.domain.ddd.ValueObject;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * User state of System User.
 * 
 * This class associated to a System User, gives all the information about the state of the user,
 * when registered an User always have 1 of the 4 User state types (UserType). When deactivated acquires
 * a Reason (why it was deactivated), otherwise this Reason is Null and none class should access it;
 * 
 * @author pedromonteiro
 */
@Embeddable
public class UserState implements Serializable, ValueObject{

    /**
     * Enumeration of the various possible User State.
     */
    public enum UserType {
        STAND_BY,
        ACCEPTED,
        DEACTIVATED,
        REJECTED
    }

    @Enumerated(EnumType.STRING)
    private UserType type;
    private Reason reason;
    @Temporal(TemporalType.DATE)
    private Calendar deactivatedOn;

    /**
     * When Registered the first State of the User must be in StandBy.
     */
    public UserState() {
        this.type = UserType.STAND_BY;
    }

    /**
     * Method to accept an User (in StandBy).
     * @return true, if it was possible to accept the User
     */
    public boolean accept() {
        if (this.type == UserType.STAND_BY) {
            this.type = UserType.ACCEPTED;
            return true;
        }

        return false;
    }

    /**
     * Method to reject an User (in StandBy).
     * @return true, if it was possible to reject the User
     */
    public boolean reject() {
        if (this.type == UserType.STAND_BY) {
            this.type = UserType.REJECTED;
            return true;
        }

        return false;
    }

    /**
     * Method to deactivate an User (activated).
     * @param deactivatedOn date of deactiavtion
     * @param rt Reason type
     * @param comment Comment justifying the deactivation
     * @return true if it was possible to deactivate the user
     */
    public boolean deactivate(Calendar deactivatedOn, ReasonType rt, String comment) {
        if (this.type == UserType.ACCEPTED) {
            this.type = UserType.DEACTIVATED;
            this.reason = new Reason(rt, comment);
            this.deactivatedOn = deactivatedOn;
            return true;
        }

        return false;
    }

    /**
     * Method that returns the deactivated reason
     * @return the deactivated reason, if the user is not deactivated the reason is NULL
     */
    public Reason deactivatedReason() {
        return this.reason;
    }
    
    /**
     * Method that returns the deactivated date
     * @return the deactivated date, if the user is not deactivated the date is NULL
     */
    public Calendar dateOfDeactivation(){
        return this.deactivatedOn;
    }
    
    /**
     * Method that returns the state of the User.
     * @return One of the available ResonType
     */
    public UserType state(){
        return this.type;
    }

}
