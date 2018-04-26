package eapli.ecafeteria.app.user.console.presentation.booking;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.application.booking.CheckBookingsForNextDaysController;
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
 * @author Mário Vaz
 */
public class CheckBookingsForNextDaysUI extends AbstractUI{

    private final CheckBookingsForNextDaysController controller = new CheckBookingsForNextDaysController();
    
    @Override
    protected boolean doShow() {
        //tenta ir buscar o user atual
        SystemUser systemUser = AuthorizationService.session().authenticatedUser();
        Optional<CafeteriaUser> user = null;
        try {
            user = controller.findUserByUsername(systemUser.username());
        } catch (DataConcurrencyException ex) {
            Logger.getLogger(CheckBookingsForNextDaysUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //vai buscar as bookings para os proximos dias com a data atual e da sout
        System.out.println(controller.findBookingsForNextDays(user, new Date()));
        
        return true;
    }

    @Override
    public String headline() {
        return "Check bookings for next days";
    }
}
