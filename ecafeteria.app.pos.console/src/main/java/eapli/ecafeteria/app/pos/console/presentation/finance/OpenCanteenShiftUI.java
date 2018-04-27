/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.pos.console.presentation.finance;

import eapli.ecafeteria.application.finance.OpenShiftController;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 11607
 */
public class OpenCanteenShiftUI extends AbstractUI {

    private final OpenShiftController theController = new OpenShiftController();

    protected Controller controller() {
        return this.theController;
    }

    @Override
    public String headline() {
        return "Open Shift";
    }

    @Override
    protected boolean doShow() {

        String mealTypeList = "Meal Types \n\n";

        for (MealType mt : MealType.MealTypeValues()) {
            mealTypeList += mt.ordinal() + " - " + mt.toString() + "\n";
        }

        final int mealTypeOption = Console.readInteger(mealTypeList);

        final Date shiftDate = Console.readDate("Shift Day");
        
        try {
            if(!theController.verifyShift(shiftDate, MealType.values()[mealTypeOption])){
                System.out.println("Shift already exists for the selected day and meal!\n");
                return false; //Dúvida - o que acontece quando este método retorna false?
            }else{
                this.theController.saveShift(shiftDate, MealType.values()[mealTypeOption]);
            }
            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            Logger.getLogger(OpenCanteenShiftUI.class.getName()).log(Level.SEVERE, null, e);
        }
        return true;

    }
}
