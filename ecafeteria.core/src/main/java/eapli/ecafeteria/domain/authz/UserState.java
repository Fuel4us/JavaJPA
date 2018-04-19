/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.authz;

/**
 *
 * @author pedromonteiro
 */
public class UserState {

    public enum UserType {
        STAND_BY,
        ACCEPTED,
        DEACTIVATED,
        REJECTED
    }

    private UserType state;
    private Reason reason;

    public UserState() {
        this.state = UserType.STAND_BY;
    }

    public boolean accept() {
        if (this.state != UserType.REJECTED) {
            this.state = UserType.ACCEPTED;
            return true;
        }

        return false;
    }

    public boolean reject() {
        if (this.state != UserType.STAND_BY) {
            return false;
        }

        this.state = UserType.REJECTED;
        return true;
    }

    public boolean deactivate(Reason.ReasonType rt, String comment) {
        if (this.state != UserType.REJECTED) {
            this.state = UserType.DEACTIVATED;
            this.reason = new Reason(rt, comment);
            return true;
        }

        return false;
    }

    public Reason deactivatedReason() {
        return this.reason;
    }

}
