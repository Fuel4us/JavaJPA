/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.app.pos.console.presentation;

import eapli.cafeteria.app.common.console.presentation.MyUserMenu;
import eapli.ecafetaria.app.pos.console.presentation.finance.ChargeCardAction;
import eapli.ecafeteria.Application;
import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.framework.actions.ReturnAction;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ExitWithMessageAction;
import eapli.framework.presentation.console.HorizontalMenuRenderer;
import eapli.framework.presentation.console.Menu;
import eapli.framework.presentation.console.MenuItem;
import eapli.framework.presentation.console.MenuRenderer;
import eapli.framework.presentation.console.ShowVerticalSubMenuAction;
import eapli.framework.presentation.console.SubMenu;
import eapli.framework.presentation.console.VerticalMenuRenderer;
import eapli.framework.presentation.console.VerticalSeparator;
import eapli.ecafeteria.app.pos.console.presentation.booking.DeliverBookingAction;

/**
 * TODO split this class in more specialized classes for each menu
 *
 * @author Paulo Gandra Sousa
 */
public class MainMenu extends AbstractUI {

    private static final int EXIT_OPTION = 0;

    // MAIN MENU
    private static final int MY_USER_OPTION = 1;
    private static final int POS_MENU = 2;
    
    //POS MENU
    private static final int CHARGE_CARD_OPTION = 1;
    private static final int SHOW_AVAILABLE_MEALS = 2;
    private static final int DELIVER_BOOKING = 3;

    @Override
    public boolean show() {
        drawFormTitle();
        return doShow();
    }

    /**
     * @return true if the user selected the exit option
     */
    @Override
    public boolean doShow() {
        final Menu menu = buildMainMenu();
        final MenuRenderer renderer;
        if (Application.settings().isMenuLayoutHorizontal()) {
            renderer = new HorizontalMenuRenderer(menu);
        } else {
            renderer = new VerticalMenuRenderer(menu);
        }
        return renderer.show();
    }

    @Override
    public String headline() {
        return "eCafeteria POS [@" + AuthorizationService.session().authenticatedUser().id() + "]";
    }

    private Menu buildMainMenu() {
        final Menu mainMenu = new Menu();

        final Menu myUserMenu = new MyUserMenu();
        mainMenu.add(new SubMenu(MY_USER_OPTION, myUserMenu, new ShowVerticalSubMenuAction(myUserMenu)));

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.add(VerticalSeparator.separator());
        }

        if (AuthorizationService.session().authenticatedUser().isAuthorizedTo(ActionRight.SALE)) {
            final Menu myPOSMenu = buildPOSMenu();
            mainMenu.add(new SubMenu(POS_MENU, myPOSMenu, new ShowVerticalSubMenuAction(myPOSMenu)));
        }

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.add(VerticalSeparator.separator());
        }

        mainMenu.add(new MenuItem(EXIT_OPTION, "Exit", new ExitWithMessageAction()));

        return mainMenu;
    }
    
    private Menu buildPOSMenu() {
        final Menu menu = new Menu("POS MENU >");

        menu.add(new MenuItem(CHARGE_CARD_OPTION, "Charge Card",
                new ChargeCardAction()));
        /*menu.add(new MenuItem(SHOW_AVAILABLE_MEALS, "Show available meals",
                new ShowAvailableMealsAction())); */
        menu.add(new MenuItem(DELIVER_BOOKING, "Deliver booking",
                new DeliverBookingAction()));
        menu.add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));
        return menu;

    }
}
