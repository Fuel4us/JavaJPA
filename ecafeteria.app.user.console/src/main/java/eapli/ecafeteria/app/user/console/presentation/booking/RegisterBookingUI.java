/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.user.console.presentation.booking;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.application.booking.CheckMenuController;
import eapli.ecafeteria.application.booking.RegisterBookingController;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leandro
 */
public class RegisterBookingUI extends AbstractUI {

    private final RegisterBookingController controller = new RegisterBookingController();
    private final CheckMenuController CheckMenuConttroller = new CheckMenuController();

    Scanner input = new Scanner(System.in);

    @Override
    protected boolean doShow() {

        //Conferir utilizador
        SystemUser su = AuthorizationService.session().authenticatedUser();
        CafeteriaUser cu = controller.findCafeteriaUser(su.username());

        System.out.println("Choose the number of the meal to book:");
        //Apresenta as meals possiveis para escolha
        List<Meal> lstMeals = CheckMenuConttroller.nextWeekMenu();
        int i = 1;
        for (Meal m : lstMeals) {
            System.out.println(i + ": " + m);
            i++;
        }

        //Utilizador escolhe meal para booking
        Meal meal = lstMeals.get(input.nextInt());
        
        System.out.println("Dish nutritional info:");
        System.out.println("Salt: " + controller.getDishNutritionalInfoSalt(meal));
        System.out.println("Calories: " + controller.getDishNutritionalInfoCalories(meal));

        System.out.println("Weekly caloric intake after this meal: " + controller.getDishWeeklyCaloricIntakeAfter(cu, meal));
        
        if (controller.mealHasAllergens(meal)) {
            
            List<String> allergens = controller.getAllergens(meal);
            
            if (!allergens.isEmpty()) {
                System.out.println("\nDish allergens:");
                for (String a : allergens)
                    System.out.println(a + ";");
                
                List<String> allergensUserIsAffectedBy = controller.getAllergensUserIsAffectedBy(cu, meal);
                
                if(!allergensUserIsAffectedBy.isEmpty()){
                    System.out.println("\nYou are affected by:");
                    
                    for (String a : allergensUserIsAffectedBy)
                        System.out.println(a + ";");
                    
                    System.out.println("\nDo you want to proceed? y/n");
                    Scanner sc = new Scanner(System.in);
                    char answer = sc.next().charAt(0);
                    while(answer != 'n' && answer != 'y'){
                        System.out.println("Do you want to proceed? y/n");
                        answer = sc.next().charAt(0);
                    }
                    if(answer == 'n'){
                        System.out.println("\nBooking canceled with success!");
                        return true;
                    }else
                        return confirmBooking(cu, meal);
                }else
                    return confirmBooking(cu, meal);
            } else
                return confirmBooking(cu, meal);
        } else
            return confirmBooking(cu, meal);
    }

    private boolean confirmBooking(CafeteriaUser cu, Meal meal) {
        boolean flag = true;

        try {
            flag = controller.registerBooking(cu, meal);
        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            Logger.getLogger(RegisterBookingUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (!flag) {
            System.out.println("\nCouldn't register the booking!");
        } else {
            System.out.println("\nBooking registed with success!");
        }

        return true;
    }

    @Override
    public String headline() {
        return "Register Booking";
    }

}
