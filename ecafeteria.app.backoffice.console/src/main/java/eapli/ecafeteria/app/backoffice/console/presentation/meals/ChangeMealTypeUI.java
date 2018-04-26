/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.meals;

import eapli.ecafeteria.domain.meals.MealType;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.util.Console;

/**
 *
 * @author pedro
 */
public class ChangeMealTypeUI extends AbstractUI {

//    private final ChangeMealTypeController theController = new ChangeMealTypeController();
//
//    protected Controller controller() {
//        return this.theController;
//    }

    @Override
    protected boolean doShow() {
//        final Iterable<MealType> dishTypes = this.theController.listMealTypes();
//        final SelectWidget<MealType> selector = new SelectWidget<>("Meal types:", MealTypes, new MealTypePrinter());
//        selector.show();
//        final MealType theMealType = selector.selectedElement();
//        if (theMealType != null) {
//            final String newDescription = Console
//                    .readLine("Enter new description for " + theMealType.description() + ": ");
//            try {
//                this.theController.changeDishType(theMealType, newDescription);
//            } catch (DataConcurrencyException ex) {
//                System.out.println("That entity has already been changed or deleted since you last read it");
//                //Logger.getLogger(ChangeDishTypeUI.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (DataIntegrityViolationException ex) {
//                System.out.println("That entity ID is already in use");
//                //Logger.getLogger(ChangeDishTypeUI.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
        return false;
    }

    @Override
    public String headline() {
        return "Change Dish Type description";
    }
}
