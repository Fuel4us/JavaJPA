/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafetaria.app.pos.console.presentation.finance;

import eapli.ecafetaria.application.finance.OpenCanteenShiftController;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
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

    private final OpenCanteenShiftController theController = new OpenCanteenShiftController();

    protected Controller controller() {
        return this.theController;
    }

    @Override
    public String headline() {
        return "Open Canteen Shift";
    }

    @Override
    protected boolean doShow() {

        String mealTypeList = "Meal Types \n";

        for (MealType mt : MealType.MealTypeValues()) {
            mealTypeList += mt.ordinal() + " - " + mt.toString() + "\n";
        }

        final int mealTypeOption = Console.readInteger(mealTypeList);

        final Date shiftDate = Console.readDate("Shift Day");
        
        try {
            this.theController.setUpShiftInformation(shiftDate, MealType.values()[mealTypeOption]);
            if(!theController.verifyShift()){
                System.out.println("Shift already exists!\n");
                return false; //Dúvida - o que acontece quando este método retorna false?
            }else{
                theController.saveShift();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            Logger.getLogger(OpenCanteenShiftUI.class.getName()).log(Level.SEVERE, null, e);
        }
        return true;

    }
}
