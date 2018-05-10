/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.cafeteriauser;

import eapli.ecafeteria.application.cafeteriauser.EditNutritionalProfileController;
import eapli.ecafeteria.domain.dishes.Allergen;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.util.Console;

/**
 *
 * @author pedromonteiro
 */
public class EditNutritionalProfileUI extends AbstractUI {

    EditNutritionalProfileController theController = new EditNutritionalProfileController();
    

    @Override
    protected boolean doShow() {
        

        System.out.println("1. Allergens");
        System.out.println("0. Exit");

        final int option = Console.readOption(1,1,0);
        switch (option) {
            case 1:
                allergensOptions();
                break;
            default:
                System.out.println("No valid option selected");
                break;
        }
        
        
        return false;
    }

    @Override
    public String headline() {
        return "";
    }

    private void allergensOptions() {
        
        System.out.println("1. Remove allergen");
        System.out.println("2. Add allergen");
        System.out.println("0. Exit");

        final int option = Console.readOption(1,2,0);
        switch (option) {
            case 1:
                final SelectWidget<Allergen> selectorUser = new SelectWidget<>("Choose the allergen",
                this.theController.getUserAllergen(), new AllergensPrinter());
                
                selectorUser.show();
                final Allergen allergen_to_remove = selectorUser.selectedElement();
                if(theController.removeAllergen(allergen_to_remove)){
                    
                }
                break;
                
            case 2:
                final SelectWidget<Allergen> selectorAll = new SelectWidget<>("Choose the allergen",
                this.theController.getUserAllergen(), new AllergensPrinter());
                
                selectorAll.show();
                final Allergen allergen_to_add = selectorAll.selectedElement();
                
                if(theController.addAllergen(allergen_to_add)){
                    
                }
                break;
            default:
                System.out.println("No valid option selected");
                break;
        }
        
    }

}
