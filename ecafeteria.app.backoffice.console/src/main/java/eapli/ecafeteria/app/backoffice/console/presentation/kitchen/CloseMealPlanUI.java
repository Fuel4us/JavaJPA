package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.ecafeteria.application.kitchen.CloseMealPlanController;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;

/**
 * @author Gonçalo Fonseca - 1150503@isep.ipp.pt
 */
public class CloseMealPlanUI extends AbstractUI {

    private static CloseMealPlanController controller = new CloseMealPlanController();

    @Override
    protected boolean doShow() {
        int option;
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

        return true;
    }

    @Override
    public String headline() {
        return "Chose meal plan to close";
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
