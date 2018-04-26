package eapli.ecafeteria.application.kitchen;

import eapli.framework.application.Controller;

/**
 * Made by Diogo Monteiro 1140302
 */
public class CheckBookingsByDataController implements Controller {
    CheckBookingsService service;

    public CheckBookingsByDataController(){
        service= new CheckBookingsService();
    }

    public void run(int choice){
        if(choice==1){
            service.displayBookingsDate();
        }else if(choice ==2){
            service.displayBookingsByMealType();
        }else if(choice ==3){
            service.displayBookingsByDish();
        }else if(choice ==4){
            service.displayBookingsByMeal();
        }
    }

}
