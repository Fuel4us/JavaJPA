/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.menus;

import eapli.ecafeteria.application.menus.PublishMenuController;
import eapli.ecafeteria.domain.menus.Menu;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Berccar
 */
public class PublishMenuUI extends AbstractUI {

    private final PublishMenuController theController = new PublishMenuController();

    /*
    protected Controller controller(){
        return this.theController;
    }*/
    @Override
    protected boolean doShow() {
        final Iterable<Menu> unpublishedMenusList = theController.listUnpublishedMenus();

        if (unpublishedMenusList.iterator().hasNext()) {
            final SelectWidget<Menu> selector = new SelectWidget<>("Unpublished menus:", unpublishedMenusList);
            selector.show();
            final Menu theMenu = selector.selectedElement();
            boolean done = false;

            try {
                done = theController.publishMenu(theMenu);
            } catch (final DataIntegrityViolationException | DataConcurrencyException e) {
            }

            String message = "No Menu was published!";
            if (done) {
                message = "The Menu id " + theMenu.id() + " was published!";
            }
            System.out.println(message);

            return done;
        } else {
            System.out.println("\nThere are no Menus with unpublished state!");
            return false;
        }
    }

    @Override
    public String headline() {
        return "Publish Menu";
    }

}
