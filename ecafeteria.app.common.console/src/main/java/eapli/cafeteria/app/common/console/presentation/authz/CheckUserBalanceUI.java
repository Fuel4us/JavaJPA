package eapli.cafeteria.app.common.console.presentation.authz;

import eapli.ecafeteria.application.authz.CheckUsersBalanceController;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;

/**
 * @author Gonçalo Fonseca - 1150503@isep.ipp.pt
 */
public class CheckUserBalanceUI extends AbstractUI {

    private final CheckUsersBalanceController controller = new CheckUsersBalanceController();

    @Override
    protected boolean doShow() {
        int option;
        do {
            option = menu();
            switch (option) {
                case 0:
                    System.out.println("Bye have a great time!");
                    break;
                case 1:
                    controller.getUserBalance();
                    break;
            }
        } while (option != 0);

        return true;
    }

    @Override
    public String headline() {
        return "Check your balance";
    }

    public static int menu(){
        int option = -1;
        System.out.println("");
        System.out.println("=========================");
        System.out.println("Get your Balance boooooooy\n");
        System.out.println("1. Get your balance");
        System.out.println("0. Leave\n");
        option = Console.readInteger("Choose an option");
        return option;
    }
}
