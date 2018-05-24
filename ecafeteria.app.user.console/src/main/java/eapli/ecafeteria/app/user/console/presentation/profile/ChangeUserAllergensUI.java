/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.user.console.presentation.profile;

import eapli.ecafeteria.application.cafeteriauser.ChangeUserAllergensController;
import eapli.ecafeteria.domain.dishes.Allergen;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.util.Collections;
import eapli.framework.util.Console;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Change User Allergens UI
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

        Iterable<Allergen> allergens_iterable = this.theController.getUserAllergen();
        List<Allergen> allergens_list = Collections.iteratorToList(allergens_iterable.iterator());

        SelectWidget<Allergen> selectorUser = new SelectWidget<>("Choose the allergen",
                this.theController.getUserAllergen(), new AllergensPrinter());

        final int option = Console.readOption(1, 3, 0);
        switch (option) {
            case 1:
                if (!allergensEmpty(allergens_list)) {
                    removeAllergen(selectorUser);
                }
                break;

            case 2:
                addAllergen();
                break;
            case 3:
                if (!allergensEmpty(allergens_list)) {
                    selectorUser.show();
                }
                break;

            default:
                System.out.println("No valid option selected");
                break;
        }

    }

    private void addAllergen() {

        Iterable<Allergen> all_allergen;
        
        try{
        all_allergen = this.theController.getAllergens();
        }catch(NoSuchElementException ex){
            System.out.println("\n\n"+ex.getMessage()+"\n\n");
            return;
        }
        
        final SelectWidget<Allergen> selectorAll = new SelectWidget<>("Choose the allergen",
                all_allergen, new AllergensPrinter());
        selectorAll.show();
        
        final Allergen allergen_to_add = selectorAll.selectedElement();

        if (allergen_to_add == null) {
            return;
        }

        if (theController.addAllergen(allergen_to_add)) {
            System.out.printf("The allergen %s was succefully added\n", allergen_to_add.getAcronym());
        } else {
            System.out.printf("Cannot add the allergen %s\n", allergen_to_add.getAcronym());
        }

    }

    private void removeAllergen(SelectWidget<Allergen> selectorUser) {
        selectorUser.show();
        final Allergen allergen_to_remove = selectorUser.selectedElement();

        if (allergen_to_remove == null) {
            return;
        }

        if (theController.removeAllergen(allergen_to_remove)) {
            System.out.printf("The allergen %s was succefully removed\n", allergen_to_remove.getAcronym());
        } else {
            System.out.printf("Cannot remove the allergen %s\n", allergen_to_remove.getAcronym());
        }
    }

    private boolean allergensEmpty(List<Allergen> allergens_list) {
        if (allergens_list.isEmpty()) {
            System.out.println("\n\nThe user didn't add allergens yet!\n\n");
            return true;
        }
        return false;
    }
    

}
