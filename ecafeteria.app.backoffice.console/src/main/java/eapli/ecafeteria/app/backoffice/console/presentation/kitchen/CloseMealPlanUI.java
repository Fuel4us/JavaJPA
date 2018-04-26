package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.ecafeteria.application.kitchen.CloseMealPlanController;
import eapli.framework.util.Console;

/**
 * @author Gonçalo Fonseca <1150503@isep.ipp.pt>
 */
public class CloseMealPlanUI {

    private static CloseMealPlanController controller = new CloseMealPlanController();

    public static void mainLoop() {
        int option = 0;
        do {
            option = menu();
            switch (option) {
                case 0:
                    System.out.println("End .. .. ..");
                    break;
                case 1:
                    controller.choseMealPlanToClose();
            }
        } while(option != 0);
    }

    public static int menu(){
        int option = -1;
        System.out.println("");
        System.out.println("=========================");
        System.out.println(" Close Meal Plan Baby ");
        System.out.println("1.Choose meal plan to close \n");
        System.out.println("0.Leave\n\n");
        option = Console.readInteger("Choose an option");
        return option;
    }
}
