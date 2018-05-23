package eapli.cafeteria.app.common.console.presentation.authz;

import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;

/**
 * @author Gonçalo Fonseca - 1150503@isep.ipp.pt
 */
public class CheckUserBalanceUI extends AbstractUI {
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
                    System.out.println("Por implementar calma babes");
                    break;
            }
        } while (option != 0);

        return true;
    }

    @Override
    public String headline() {
        return "Choose user to check balance";
    }

    public static int menu(){
        int option = -1;
        System.out.println("");
        System.out.println("=========================");
        System.out.println(" Get user Balance boooooooy ");
        System.out.println("1.Choose user to check balance\n");
        System.out.println("0.Leave\n\n");
        option = Console.readInteger("Choose an option");
        return option;
    }
}
