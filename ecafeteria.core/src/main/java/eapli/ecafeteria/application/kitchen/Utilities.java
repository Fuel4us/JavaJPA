package eapli.ecafeteria.application.kitchen;

import java.util.Calendar;

public class Utilities {
    
    /**
     *
     * @return String in the format YYYY-MM-DD
     */
    public static String createCurrentDate(){
        Calendar cal = Calendar.getInstance();
        String canteenShiftDate;
        
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        
        if(month <= 9)
            canteenShiftDate = Integer.toString(year) + "-" + "0" + Integer.toString(month) + "-";
        else
            canteenShiftDate = Integer.toString(year) + "-" + Integer.toString(month) + "-";
        
        if(day <= 9)
            canteenShiftDate +=  "0" + Integer.toString(day);
        else
            canteenShiftDate += Integer.toString(day);
        
        return canteenShiftDate;
    }
}
