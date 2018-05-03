/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.pos.console.presentation.finance;

import eapli.ecafeteria.application.finance.ChargeCardController;
import eapli.ecafeteria.domain.authz.Username;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hernani Gil
 */
public class ChargeCardUI extends AbstractUI {

    private final ChargeCardController chargeCardController = new ChargeCardController();

    @Override
    protected boolean doShow() {
//        final List<CafeteriaUser> list = new ArrayList<>();
        //final Iterable<CafeteriaUser> iterableCafeteriaUsers = this.chargeCardController.activeUsers();

//        if (!iterableCafeteriaUsers.iterator().hasNext()) {
//            System.out.println("There is no registered CafeteriaUser");
//        }else{        
//            int count = 1;
//            System.out.printf("%-6s%-10s%-30s%-30s\n", "Nº:", "Username", "Firstname", "Lastname");
//            for (final CafeteriaUser cafeteriaUser : iterableCafeteriaUsers) {
//                list.add(cafeteriaUser);
//                System.out.printf("Option: " + count +" Nº: " + cafeteriaUser.mecanographicNumber().number() + " Nome: " +  cafeteriaUser.user().name().firstName() + " " + cafeteriaUser.user().name().lastName());
//                count++;
//            }
//            
//            final int option = Console.readInteger("Enter the number corresponding to the user that you wish to add credits to or press 0 to deactivate.");
//            if (option == 0) {
//                System.out.println("No user selected");
//            } else {
//                CafeteriaUser selectedUser = list.get(option - 1);
        boolean flag = false;
        final String input = Console.readLine("ReadCard simulation: insert Cafeteria Username");

        Username username = new Username(input);
        flag = chargeCardController.selectUser(username);

        if (flag) {
            double moneyAmount = Console.readDouble("Enter the amount of money that you wish to add to to user " + input);
            try {
                double amount = chargeCardController.ChargeCard(moneyAmount);
                System.out.println("CafeteriaUser: " + username + " Balance: " + amount);
            } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
                Logger.getLogger(ChargeCardUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            return false;
        }
        return true;
    }

    @Override
        public String headline() {
        return "Charge Card";
    }
}
