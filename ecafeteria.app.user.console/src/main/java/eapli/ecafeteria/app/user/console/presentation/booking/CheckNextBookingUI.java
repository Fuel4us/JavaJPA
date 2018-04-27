/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.user.console.presentation.booking;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.application.booking.CheckNextBookingController;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.presentation.console.AbstractUI;
import java.util.Date;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tiago Correia
 */
public class CheckNextBookingUI extends AbstractUI {

    private final CheckNextBookingController controller = new CheckNextBookingController();
    
    @Override
    protected boolean doShow() {
        SystemUser systemUser = AuthorizationService.session().authenticatedUser();
        Optional<CafeteriaUser> user = null;
        try {
            user = controller.findUserByUsername(systemUser.username());
        } catch (DataConcurrencyException ex) {
            Logger.getLogger(CheckBookingsForNextDaysUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        System.out.println(controller.getNextBooking(user, new Date()));
        
        return true;
    }

    @Override
    public String headline() {
        return "Check next Booking \n";
    }
    
}
