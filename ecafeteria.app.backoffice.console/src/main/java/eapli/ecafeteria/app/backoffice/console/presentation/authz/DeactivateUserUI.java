/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.authz;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import eapli.ecafeteria.application.authz.DeactivateUserController;
import eapli.ecafeteria.domain.authz.ReasonType;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.util.Console;

/**
 *
 * @author Fernando
 */
@SuppressWarnings("squid:S106")
public class DeactivateUserUI extends AbstractUI {

    private final DeactivateUserController theController = new DeactivateUserController();

    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {
        final Iterable<CafeteriaUser> iterable = this.theController.activeUsers();
        final List<ReasonType> reasonList = new ArrayList<>();
        
        if (!iterable.iterator().hasNext()) {
            System.out.println("There is no registered User");
        } else {
            int reasonCounter = 1;
            final SelectWidget<CafeteriaUser> selector = new SelectWidget<>("Active Cafetaria Users:", iterable, new CafetariaUserPrinter());
            selector.show();
            final CafeteriaUser selectedUser = selector.selectedElement();
            
            if(selectedUser == null){
                System.out.println("The selected reason does not exist!\n");
                return false;
            }
            
            System.out.println("Reason list\n");
            for(final ReasonType rType: this.theController.reasons()){
                reasonList.add(rType);
                System.out.println(reasonCounter+". "+rType+"\n");
                reasonCounter++;
            }
            final int reasonNumber = Console.readInteger("Select the reason: ");
            
            if(reasonNumber <= 0 || reasonNumber > reasonList.size()){
                System.out.println("The selected reason does not exist!\n");
                return false;
            }
            
            final String reasonComent = Console.readLine("Write a comment justifying the decision:\n");
            
            if(reasonComent.isEmpty()){
                System.out.println("You must give a reason!\n");
            }
            
            try {
                    this.theController.deactivateUser(selectedUser, reasonList.get(reasonNumber - 1), reasonComent);
                } catch (DataIntegrityViolationException | DataConcurrencyException ex) {
                    Logger.getLogger(DeactivateUserUI.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        return true;
    }

    @Override
    public String headline() {
        return "Deactivate User";
    }
}
