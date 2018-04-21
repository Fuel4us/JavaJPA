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
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
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
        final List<SystemUser> list = new ArrayList<>();
        final Iterable<SystemUser> iterable = this.theController.activeUsers();
        final List<ReasonType> reasonList = new ArrayList<>();
        
        if (!iterable.iterator().hasNext()) {
            System.out.println("There is no registered User");
        } else {
            int cont = 1;
            int reasonCounter = 1;
            System.out.println("SELECT User to deactivate\n");
            // FIXME use select widget, see, ChangeDishTypeUI
            System.out.printf("%-6s%-10s%-30s%-30s%n", "Nº:", "Username", "Firstname", "Lastname");
            for (final SystemUser user : iterable) {
                list.add(user);
                System.out.printf("%-6d%-10s%-30s%-30s%n", cont, user.username(),
                        user.name().firstName(), user.name().lastName());
                cont++;
            }
            final int option = Console.readInteger("Enter user nº to deactivate or 0 to finish ");
            if (option == 0) {
                System.out.println("No user selected");
            } 
            System.out.println("Reason list\n");
            for(final ReasonType rType: ReasonType.values()){
                reasonList.add(rType);
                System.out.println(reasonCounter+". "+rType+"\n");
            }
            final int reasonNumber = Console.readInteger("Select the reason: ");
            
            if(reasonNumber <= 0 || reasonNumber > reasonList.size()){
                System.out.println("The selected reason does not exist!\n");
            }
            
            final String reasonComent = Console.readLine("Write a comment justifying the decision:\n");
            
            if(reasonComent.isEmpty()){
                System.out.println("You must give a reason!\n");
            }
            
            try {
                    this.theController.deactivateUser(list.get(option - 1), reasonList.get(reasonNumber - 1), reasonComent);
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
