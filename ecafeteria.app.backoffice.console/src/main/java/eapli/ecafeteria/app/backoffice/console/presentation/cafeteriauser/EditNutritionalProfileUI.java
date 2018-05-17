/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.cafeteriauser;

import eapli.ecafeteria.application.cafeteriauser.EditNutritionalProfileController;
import eapli.framework.presentation.console.AbstractUI;
import java.util.Scanner;

/**
 *
 * @author Tiago Babo (1160760)
 */
public class EditNutritionalProfileUI extends AbstractUI{
    Scanner input = new Scanner(System.in);
    EditNutritionalProfileController controller = new EditNutritionalProfileController();
    
    /**
     * 
     * @return 
     */
    @Override
    protected boolean doShow() {
        mainMenu();
        return false;
    }
    /**
     * 
     * @return 
     */
    @Override
    public String headline() {
        return "EDIT NUTRITIONAL PROFILE";
    }
    
    /**
     * 
     */
    public void mainMenu() {
        int option = 0;

        do {
            option = menuUI();

            switch (option) {
                case 0:
                    System.out.println("\n# END OF EDIT NUTRITIONAL PROFILE #");
                    break;
                case 1:
                    break;
                default:
                    System.out.println("\nINVALID OPTION!");
                    break;
            }
        } while (option != 0);
    }
    
    /**
     * 
     * @return 
     */
    public int menuUI() {
        System.out.println("");
        System.out.println("1. Edit nutritional profile");
        System.out.println("0. Leave");
        System.out.printf("OPTION: ");
        int option = input.nextInt();

        return option;
    }
}
