package eapli.ecafeteria.app.user.console.presentation.booking;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.application.booking.CheckRatingController;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.presentation.console.AbstractUI;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CheckRatingUI extends AbstractUI {

    private final CheckRatingController controller = new CheckRatingController();
    
    @Override
    protected boolean doShow() {
        //try to fetch the current user
        SystemUser systemUser = AuthorizationService.session().authenticatedUser();
        Optional<CafeteriaUser> user = null;
        try {
            user = controller.findUserByUsername(systemUser.username());
        } catch (DataConcurrencyException ex) {
            Logger.getLogger(CheckBookingsForNextDaysUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(!user.isPresent())
            System.out.println("User not found");
        else
            System.out.println(controller.getRatingsFromUser(user.get()));
        
        return true;
    }

    @Override
    public String headline() {
        return "Check ratings";
    }
}
