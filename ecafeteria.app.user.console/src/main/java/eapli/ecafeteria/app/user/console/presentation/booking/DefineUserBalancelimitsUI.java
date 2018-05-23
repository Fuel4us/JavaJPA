/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.user.console.presentation.booking;

import eapli.ecafeteria.application.cafeteriauser.DefineUserBalancelimitsController;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;

/**
 *
 * @author Leandro
 */
public class DefineUserBalancelimitsUI extends AbstractUI{

    private final DefineUserBalancelimitsController controller = new DefineUserBalancelimitsController();
    
    @Override
    protected boolean doShow() {
        boolean repeat, saved;
        double limit;
        
        do{
            limit = Console.readDouble("Type a user balance limit!");
            
            repeat = controller.setUserBalanceLimit(limit);
            
            if(repeat == true){
                System.out.println("Invalid Limit!\n");
            }else{
                System.out.println("Valid Limit!\nSaving the new Limit");
                saved = controller.saveUserBalanceLimit();
                
                if(saved == true){
                    System.out.println("The new Limit is saved!");
                }else{
                    System.out.println("An error occured, the new limit was not saved!");
                    repeat = true;
                }
            }
            
        }while(repeat);
            
        return true;
    }

    @Override
    public String headline() {
        return "Define User Balance limits";
    }
    
}
