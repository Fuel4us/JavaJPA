/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.domain.authz.Reason;
import eapli.framework.actions.Action;

/**
 *
 * @author pedromonteiro
 */
public class ReasonBootstrapper implements Action{

    @Override
    public boolean execute() {
        
        boolean r1_return, r2_return;
        r1_return = Reason.addReason("");
        r2_return = Reason.addReason("");
        
        if(r1_return && r2_return) return true;
        
        
        return false;
    }
    
    
    
}
