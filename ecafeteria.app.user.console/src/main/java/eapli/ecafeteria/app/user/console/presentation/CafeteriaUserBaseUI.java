/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.app.user.console.presentation;

import eapli.ecafeteria.application.cafeteriauser.CafeteriaUserBaseController;
import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.application.booking.BookingWatchDog;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.movement.BalanceService;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.presentation.console.AbstractUI;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author mcn
 */
public abstract class CafeteriaUserBaseUI extends AbstractUI implements Observer {

    private final CafeteriaUserRepository repCafeteriaUser = PersistenceContext.repositories().cafeteriaUsers();

    protected abstract CafeteriaUserBaseController controller();

    public String showBalance() {
        return "CURRENT BALANCE OF YOUR USERCARD: " + BalanceService.balance(repCafeteriaUser.findBySystemUser(AuthorizationService.session().authenticatedUser()).get().mecanographicNumber());
    }

    @Override
    public String headline() {
        return "eCAFETERIA [@" + AuthorizationService.session().authenticatedUser().id() + "]   " + showBalance();
    }

    @Override
    protected void drawFormTitle(String title) {
        // drawFormBorder();
        final String titleBorder = BORDER.substring(0, 2) + " " + title;
        System.out.println(titleBorder);
        drawFormBorder();
    }

    public void initializateObservers() {

        BookingWatchDog bookingWatchdog = new BookingWatchDog();
        Booking booking = new Booking();
        Observable obsBooking = booking;
        obsBooking.addObserver(this); //Add an observer to the observable booking
        obsBooking.addObserver(bookingWatchdog); //Add an observer to the observable booking
        booking.changeState(BookingState.RESERVED);
        
        
    }

    @Override
    public void update(Observable o, Object o1) {
        System.out.println("\n========== ALERT ==========\n");
    }
}
