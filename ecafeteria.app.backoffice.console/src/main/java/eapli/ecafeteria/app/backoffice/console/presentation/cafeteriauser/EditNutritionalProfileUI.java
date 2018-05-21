/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.cafeteriauser;

import eapli.ecafeteria.application.cafeteriauser.EditNutritionalProfileController;
import eapli.ecafeteria.domain.cafeteriauser.Calorie;
import eapli.ecafeteria.domain.cafeteriauser.NutritionalProfile;
import eapli.ecafeteria.domain.cafeteriauser.Salt;
import eapli.framework.presentation.console.AbstractUI;
import java.util.Scanner;

/**
 * UI for editing nutritional profile
 * @author Tiago Babo (1160760)
 */
public class EditNutritionalProfileUI extends AbstractUI{
    Scanner input = new Scanner(System.in);
    
    /**
     * Instance of EditNutritionalProfileController
     */
    EditNutritionalProfileController controller = new EditNutritionalProfileController();
    
    /**
     * Show UI
     * @return false;
     */
    @Override
    protected boolean doShow() {
        mainMenu();
        return false;
    }
    /**
     * Headline
     * @return headline
     */
    @Override
    public String headline() {
        return "EDIT NUTRITIONAL PROFILE";
    }
    
    /**
     * Menu options
     * @return option
     */
    public int menuUI() {
        System.out.println("");
        System.out.println("1. Change salt max quantity");
        System.out.println("2. Change salt calorie quantity");
        System.out.println("0. Leave");
        System.out.printf("OPTION: ");
        int option = input.nextInt();

        return option;
    }
    
    /**
     * Base menu for option choosing
     */
    public void mainMenu() {
        int option = 0;

        do {
            option = menuUI();
            switch (option) {
                case 0:
                    System.out.println("");
                    break;
                case 1:
                    if(changeSalt()){
                        System.out.println("\nMax salt quantity changed successfully!");
                    }else{
                        System.out.println("\nNot possible to change max salt quantity!");
                    }
                    break;
                case 2:
                    if(changeCalorie()){
                        System.out.println("\nMax salt quantity changed successfully!");
                    }else{
                        System.out.println("\nNot possible to change max salt quantity!");
                    }
                    break;
                default:
                    System.out.println("\nINVALID OPTION!");
                    break;
            }
        } while (option != 0);
    }
    
    /**
     * UI for changing salt
     * @return 
     */
    private boolean changeSalt(){
        NutritionalProfile nutriProfile = controller.getUserNutritionalProfile();
        
        System.out.printf("\nEntry the new max salt quantity: ");
        int maxSalt = input.nextInt();
        
        controller.setMaxSaltQuantity(maxSalt);
        
        controller.saveMaxSaltQuantity(maxSalt);
        
        return true;
    }
    
    /**
     * UI for changing calories
     * @return true
     */
    private boolean changeCalorie(){
        NutritionalProfile nutriProfile = controller.getUserNutritionalProfile();
        
        System.out.printf("\nEntry the new max calorie quantity: ");
        int maxCalorie = input.nextInt();
        
        controller.setMaxCalorieQuantity(maxCalorie);
        
        controller.saveMaxCalorieQuantity(maxCalorie);
        
        return true;
    }
}
