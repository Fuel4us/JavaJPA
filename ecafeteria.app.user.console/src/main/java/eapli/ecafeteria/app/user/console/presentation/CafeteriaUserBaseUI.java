/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.app.user.console.presentation;

import eapli.ecafeteria.application.cafeteriauser.CafeteriaUserBaseController;
import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.application.booking.BookingServices;
import eapli.ecafeteria.application.booking.BookingWatchDog;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.authz.Role;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.authz.exceptions.UnauthorizedException;
import eapli.ecafeteria.domain.movement.BalanceService;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.presentation.console.AbstractUI;
import java.util.Observer;

/**
 *
 * @author mcn
 */
public abstract class CafeteriaUserBaseUI extends AbstractUI {
    private final CafeteriaUserRepository repCafeteriaUser = PersistenceContext.repositories().cafeteriaUsers();

    private BookingServices bookingServices;

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
        
        bookingServices = new BookingServices();
        final SystemUser currentUser = AuthorizationService.session().authenticatedUser();
        try {
            AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);
        } catch (UnauthorizedException ex) {

        }
        BookingWatchDog bookingWatchDog = new BookingWatchDog();
        bookingWatchDog.addObserver((Observer) currentUser);
    }
}
