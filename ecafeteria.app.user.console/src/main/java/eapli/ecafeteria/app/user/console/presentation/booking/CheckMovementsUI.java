/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.user.console.presentation.booking;

import eapli.ecafeteria.application.booking.CheckMovementsController;
import eapli.ecafeteria.domain.movement.Movement;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;
import java.util.Calendar;
import javax.persistence.NoResultException;

/**
 *
 * @author jreis22
 */
public class CheckMovementsUI extends AbstractUI {

    private CheckMovementsController controller = new CheckMovementsController();
    private static String DATE_FORMAT = "dd-MM-yyyy";

    @Override
    protected boolean doShow() {
        Calendar beginning = Console.readCalendar("Insert date of day from which you wish to check movements (" + DATE_FORMAT + ")", DATE_FORMAT);
        try {
            Iterable<Movement> movList = controller.showMovements(beginning);
            for(Movement m: movList) {
                System.out.println(m);
            }
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        } catch (NoResultException e) {
            System.out.println("User isn't registered as a cafeteria user");   
        }
        
        return true;
    }

    @Override
    public String headline() {
        return "Check movements from the last days";
    }

}
