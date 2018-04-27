package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.menus.RegisterMenuController;
import eapli.ecafeteria.domain.menus.Menu;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pedro Alves <1150372@isep.ipp.pt>
 */
public class MenuBootstrapper implements Action {

    @Override
    public boolean execute() {

        Date date1 = new Date(0000, 03, 27);
        Date date2 = new Date(0000, 03, 29);
        Date date3 = new Date(0000, 12, 30);

        registerMenu(date1, date2);
        registerMenu(date1, date2);
        registerMenu(date1, date3);

        return true;
    }

    private void registerMenu(Date begDate, Date endDate) {
        final RegisterMenuController controller = new RegisterMenuController();
        Menu menu = new Menu();
        try {
            menu = controller.registerMenu(begDate, endDate);
        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            Logger.getLogger(MealBootstrapper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
