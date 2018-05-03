package eapli.ecafeteria.app.pos.console.presentation.finance;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import eapli.ecafeteria.application.finance.OpenShiftController;
import eapli.ecafeteria.domain.finance.Shift;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;
import eapli.framework.util.DateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 11607
 */
public class OpenShiftUI extends AbstractUI {

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

        MealType defaultMealType = MealType.LUNCH;
        Calendar now = DateTime.now();

        if (now.get(Calendar.HOUR_OF_DAY) > Shift.DINNER_INIT_HOUR) {
            defaultMealType = MealType.DINNER;
        } else {
            if (now.get(Calendar.HOUR_OF_DAY) == Shift.DINNER_INIT_HOUR) {
                if (now.get(Calendar.MINUTE) > 0) {
                    defaultMealType = MealType.DINNER;
                }
            }
        }

        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1;
        int day = now.get(Calendar.DAY_OF_MONTH);

        mealTypeList += "9 - default (" + year + "/" + month + "/" + day + ") Session: " + defaultMealType.toString() + "\n";

        final int mealTypeOption = Console.readInteger(mealTypeList);

        Date shiftDate;
        MealType mealType;

        if (mealTypeOption == 9) {
            mealType = defaultMealType;
            shiftDate = now.getTime();
        } else {

            mealType = MealType.values()[mealTypeOption];

            shiftDate = Console.readDate("Shift Date (\"yyyy/MM/dd\")");

            System.out.println(shiftDate.toString() + "\n");
        }
        try {
            if (!theController.verifyShift(shiftDate, mealType)) {
                System.out.println("Shift already exists for the selected day and meal!\n");
                //return false; //Dúvida - o que acontece quando este método retorna false?
            } else {
                this.theController.saveShift(shiftDate, mealType);
                System.out.println("Shift saved successfully!");
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            Logger.getLogger(OpenShiftUI.class.getName()).log(Level.SEVERE, null, e);
        }
        return true;

    }
}
