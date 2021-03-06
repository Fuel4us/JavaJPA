/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.app.user.console.presentation;

import eapli.cafeteria.app.common.console.presentation.MyUserMenu;
import eapli.ecafeteria.app.user.console.presentation.booking.BookingRatingAction;
import eapli.ecafeteria.app.user.console.presentation.booking.CancelBookingUI;
import eapli.ecafeteria.app.user.console.presentation.booking.CheckBookingsForNextDaysUI;
import eapli.ecafeteria.app.user.console.presentation.booking.CheckMenuUI;
import eapli.ecafeteria.app.user.console.presentation.booking.CheckMovementsUI;
import eapli.ecafeteria.app.user.console.presentation.booking.CheckNextBookingUI;
import eapli.ecafeteria.app.user.console.presentation.booking.ConsultRatingsUI;
import eapli.ecafeteria.app.user.console.presentation.booking.DefineUserBalancelimitsUI;
import eapli.ecafeteria.app.user.console.presentation.booking.RegisterBookingUI;
import eapli.ecafeteria.app.user.console.presentation.profile.ChangeUserAllergensAction;
import eapli.ecafeteria.app.user.console.presentation.profile.EditNutritionalProfileAction;
import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.application.cafeteriauser.CafeteriaUserBaseController;
import eapli.ecafeteria.application.cafeteriauser.EditNutritionalProfileController;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.framework.actions.ReturnAction;
import eapli.framework.presentation.console.ExitWithMessageAction;
import eapli.framework.presentation.console.Menu;
import eapli.framework.presentation.console.MenuItem;
import eapli.framework.presentation.console.MenuRenderer;
import eapli.framework.presentation.console.ShowMessageAction;
import eapli.framework.presentation.console.ShowVerticalSubMenuAction;
import eapli.framework.presentation.console.SubMenu;
import eapli.framework.presentation.console.VerticalMenuRenderer;
import eapli.framework.presentation.console.VerticalSeparator;

/**
 * @author Paulo Gandra Sousa
 */
class MainMenu extends CafeteriaUserBaseUI {

    private static final int EXIT_OPTION = 0;

    // MAIN MENU
    private static final int MY_USER_OPTION = 1;
    private static final int BOOKINGS_OPTION = 2;
    private static final int ACCOUNT_OPTION = 3;
    private static final int SETTINGS_OPTION = 4;
    private static final int NUTRITIONAL_OPTION = 5;

    // BOOKINGS MENU
    private static final int LIST_MENUS_OPTION = 1;
    private static final int BOOK_A_MEAL_OPTION = 2;
    private static final int RATE_A_BOOKING_OPTION = 3;
    private static final int CHECK_BOOKINGS_FOR_NEXT_DAYS = 4;
    private static final int CHECK_NEXT_BOOKING = 5;
    private static final int CHECK_MENU = 6;
    private static final int CONSULT_RATINGS = 7;
    private static final int CANCEL_BOOKING = 8;
    private static final int CHECK_MOVEMENTS = 9;

    // ACCOUNT MENU
    private static final int LIST_MOVEMENTS_OPTION = 1;

    // SETTINGS
    private static final int SET_USER_ALERT_LIMIT_OPTION = 1;
    
    //NUTRITIONAL PROFILE
    private static final int SALT_CALORIES = 1;
    private static final int ALLERGENS = 2;

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
        final MenuRenderer renderer = new VerticalMenuRenderer(menu);
        //this.initializateObservers();
        return renderer.show();
    }

    private Menu buildMainMenu() {
        final Menu mainMenu = new Menu();

        final Menu myUserMenu = new MyUserMenu();
        mainMenu.add(new SubMenu(MY_USER_OPTION, myUserMenu, new ShowVerticalSubMenuAction(myUserMenu)));

        mainMenu.add(VerticalSeparator.separator());

        final Menu bookingsMenu = buildBookingsMenu();
        mainMenu.add(new SubMenu(BOOKINGS_OPTION, bookingsMenu, new ShowVerticalSubMenuAction(bookingsMenu)));

        
        mainMenu.add(VerticalSeparator.separator());

        final Menu accountMenu = buildAccountMenu();
        mainMenu.add(new SubMenu(ACCOUNT_OPTION, accountMenu, new ShowVerticalSubMenuAction(accountMenu)));

        mainMenu.add(VerticalSeparator.separator());

        final Menu settingsMenu = buildAdminSettingsMenu();
        mainMenu.add(new SubMenu(SETTINGS_OPTION, settingsMenu, new ShowVerticalSubMenuAction(settingsMenu)));
        
        mainMenu.add(VerticalSeparator.separator());
        
        final Menu nutritionalProfileMenu = buildNutritionalProfileMenu();
        mainMenu.add(new SubMenu(NUTRITIONAL_OPTION, nutritionalProfileMenu, new ShowVerticalSubMenuAction(nutritionalProfileMenu)));

        mainMenu.add(VerticalSeparator.separator());

        mainMenu.add(new MenuItem(EXIT_OPTION, "Exit", new ExitWithMessageAction()));
        
        

        return mainMenu;
    }

    private Menu buildAccountMenu() {
        final Menu menu = new Menu("Account");
        menu.add(new MenuItem(LIST_MOVEMENTS_OPTION, "List movements", new ShowMessageAction("Not implemented yet")));
        menu.add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));
        return menu;
    }

    private Menu buildBookingsMenu() {
        final Menu menu = new Menu("Bookings");
        menu.add(new MenuItem(LIST_MENUS_OPTION, "List menus", new ShowMessageAction("Not implemented yet")));
        menu.add(new MenuItem(BOOK_A_MEAL_OPTION, "Book a meal", () -> new RegisterBookingUI().show()));
        menu.add(new MenuItem(RATE_A_BOOKING_OPTION, "Rate a booking", new BookingRatingAction()));
        menu.add(new MenuItem(CHECK_BOOKINGS_FOR_NEXT_DAYS, "Check bookings for next days", () -> new CheckBookingsForNextDaysUI().show()));
        menu.add(new MenuItem(CHECK_NEXT_BOOKING, "Check nextBooking", () -> new CheckNextBookingUI().show()));
        menu.add(new MenuItem(CHECK_MENU, "Check Menu for this week (or for next week)", () -> new CheckMenuUI().show()));
        menu.add(new MenuItem(CONSULT_RATINGS, "Consult ratings", () -> new ConsultRatingsUI().show()));
        menu.add(new MenuItem(CANCEL_BOOKING, "Cancel Booking", () -> new CancelBookingUI().show()));
        menu.add(new MenuItem(CHECK_MOVEMENTS, "Check movements from the last days", () -> new CheckMovementsUI().show()));
        menu.add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));
        return menu;
    }

    private Menu buildAdminSettingsMenu() {
        final Menu menu = new Menu("Settings >");

        menu.add(new MenuItem(SET_USER_ALERT_LIMIT_OPTION, "Set users' alert limit", () -> new DefineUserBalancelimitsUI().show()));
        menu.add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));

        return menu;
    }

    @Override
    protected CafeteriaUserBaseController controller() {
        return new CafeteriaUserBaseController();
    }

    private Menu buildNutritionalProfileMenu() {
        final Menu menu = new Menu(" Nutritional Profile >");
        menu.add(new MenuItem(SALT_CALORIES, "Change Max Salt and Calories", new EditNutritionalProfileAction()));
        menu.add(new MenuItem(ALLERGENS, "Edit Allergens", new ChangeUserAllergensAction()));
        
        return menu;
    }
}
