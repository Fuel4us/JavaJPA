/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.ecafeteria.application.booking.BookingWatchDog;
import eapli.framework.presentation.console.AbstractUI;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Pedro Rodrigues - (1140572)
 */
public class BookingLimitAlertUI extends AbstractUI implements Observer {

    private final BookingWatchDog bookingWatchdog = new BookingWatchDog();
    
    @Override
    public void update(Observable bookingWatchDog, Object booking) {
        this.bookingWatchdog.display();
    }

    @Override
    public String headline() {
        return "========= ALERT =========";
    }

    @Override
    protected boolean doShow() {
        return true;
    }
    
}
