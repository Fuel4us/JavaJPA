/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.cafeteriauser;

import eapli.ecafeteria.application.cafeteriauser.ChangeUserAllergensController;
import eapli.ecafeteria.domain.dishes.Allergen;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.util.Console;

/**
 *
 * @author pedromonteiro
 */
public class ChangeUserAllergensUI extends AbstractUI {

    ChangeUserAllergensController theController = new ChangeUserAllergensController();
    

    @Override
    protected boolean doShow() {
        allergensOptions();
        return false;
    }

    @Override
    public String headline() {
        return "  USER ALLERGENS MENU ";
    }

    private void allergensOptions() {
        
        System.out.println("1. Remove allergen\n2. Add allergen\n3. Show User Allergen List\n0. Exit\n");

        SelectWidget<Allergen> selectorUser = new SelectWidget<>("Choose the allergen",
                this.theController.getUserAllergen(), new AllergensPrinter());
            
        final int option = Console.readOption(1,3,0);
        switch (option) {
            case 1:
                
                selectorUser.show();
                final Allergen allergen_to_remove = selectorUser.selectedElement();
                
                if(allergen_to_remove == null) break;
                
                if(theController.removeAllergen(allergen_to_remove)){
                    System.out.printf("The allergen %s was succefully removed\n", allergen_to_remove.getAcronym());
                }else{
                  System.out.printf("Cannot remove the allergen %s\n", allergen_to_remove.getAcronym());
                }
                break;
                
            case 2:
                
                final SelectWidget<Allergen> selectorAll = new SelectWidget<>("Choose the allergen",
                this.theController.getAvailableAllergens(), new AllergensPrinter());
                selectorAll.show();
                final Allergen allergen_to_add = selectorAll.selectedElement();
                
                if(allergen_to_add == null) break;
                
                if(theController.addAllergen(allergen_to_add)){
                    System.out.printf("The allergen %s was succefully added\n", allergen_to_add.getAcronym());
                }else{
                    System.out.printf("Cannot add the allergen %s\n", allergen_to_add.getAcronym());
                }
                
                break;
            case 3:
                selectorUser.show();
                break;
                
            default:
                System.out.println("No valid option selected");
                break;
        }
        
    }

}
