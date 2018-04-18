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
    
    
    public enum UserStates{
        STAND_BY,
        ACCEPTED,
        DEACTIVATED,
        REJECTED
    }
    
    private UserStates state;
    private Reason reason;

    public UserState() {
        this.state = UserStates.STAND_BY;
    }
    
    public void deactivateUser(Reason.ReasonType rt, String comment){
        this.state = UserStates.DEACTIVATED;
        this.reason = new Reason(rt, comment);
    }
    
    
    
}
