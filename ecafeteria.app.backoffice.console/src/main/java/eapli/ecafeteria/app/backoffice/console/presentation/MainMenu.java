/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation;

import eapli.cafeteria.app.common.console.presentation.MyUserMenu;
import eapli.ecafeteria.Application;
import eapli.ecafeteria.app.backoffice.console.presentation.administration.ConfigureKitchenAlertLimitsAction;
import eapli.ecafeteria.app.backoffice.console.presentation.administration.SelectHeuristicAction;
import eapli.ecafeteria.app.backoffice.console.presentation.authz.AddUserUI;
import eapli.ecafeteria.app.backoffice.console.presentation.authz.DeactivateUserAction;
import eapli.ecafeteria.app.backoffice.console.presentation.authz.ListUsersAction;
import eapli.ecafeteria.app.backoffice.console.presentation.cafeteriauser.AcceptRefuseSignupRequestAction;
import eapli.ecafeteria.app.backoffice.console.presentation.dishes.ActivateDeactivateDishAction;
import eapli.ecafeteria.app.backoffice.console.presentation.dishes.ActivateDeactivateDishTypeAction;
import eapli.ecafeteria.app.backoffice.console.presentation.dishes.ChangeDishNutricionalInfoAction;
import eapli.ecafeteria.app.backoffice.console.presentation.dishes.ChangeDishPriceAction;
import eapli.ecafeteria.app.backoffice.console.presentation.dishes.ChangeDishTypeAction;
import eapli.ecafeteria.app.backoffice.console.presentation.dishes.ListDishAction;
import eapli.ecafeteria.app.backoffice.console.presentation.dishes.ListDishTypeAction;
import eapli.ecafeteria.app.backoffice.console.presentation.dishes.RegisterDishAction;
import eapli.ecafeteria.app.backoffice.console.presentation.dishes.RegisterDishTypeAction;
import eapli.ecafeteria.app.backoffice.console.presentation.dishes.reporting.ReportDishesPerCaloricCategoryAsTuplesUI;
import eapli.ecafeteria.app.backoffice.console.presentation.dishes.reporting.ReportDishesPerCaloricCategoryUI;
import eapli.ecafeteria.app.backoffice.console.presentation.dishes.reporting.ReportDishesPerDishTypeUI;
import eapli.ecafeteria.app.backoffice.console.presentation.dishes.reporting.ReportHighCaloriesDishesUI;
import eapli.ecafeteria.app.backoffice.console.presentation.dishesviadto.ListDishViaDTOUI;
import eapli.ecafeteria.app.backoffice.console.presentation.dishesviadto.RegisterDishViaDTOUI;
import eapli.ecafeteria.app.backoffice.console.presentation.kitchen.*;
import eapli.ecafeteria.app.backoffice.console.presentation.meals.CheckMealRatingsAction;
import eapli.ecafeteria.app.backoffice.console.presentation.meals.RegisterMealAction;
import eapli.ecafeteria.app.backoffice.console.presentation.meals.ReplyMealRatingCommentsAction;
import eapli.ecafeteria.app.backoffice.console.presentation.menus.EditMenuAction;
import eapli.ecafeteria.app.backoffice.console.presentation.menus.PublishMenuUI;
import eapli.ecafeteria.app.backoffice.console.presentation.menus.RegisterMenuAction;
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
import eapli.ecafeteria.app.backoffice.console.presentation.ratings.CheckRatingsAction;

/**
 * TODO split this class in more specialized classes for each menu
 *
 * @author Paulo Gandra Sousa
 */
public class MainMenu extends AbstractUI {

    private static final int EXIT_OPTION = 0;


    // USERS
    private static final int ADD_USER_OPTION = 1;
    private static final int LIST_USERS_OPTION = 2;
    private static final int DEACTIVATE_USER_OPTION = 3;
    private static final int ACCEPT_REFUSE_SIGNUP_REQUEST_OPTION = 4;

    // SETTINGS
    private static final int SET_KITCHEN_ALERT_LIMIT_OPTION = 1;
    private static final int SELECT_HEURISTIC_OPTION = 2;

    // DISH TYPES
    private static final int DISH_TYPE_REGISTER_OPTION = 1;
    private static final int DISH_TYPE_LIST_OPTION = 2;
    private static final int DISH_TYPE_CHANGE_OPTION = 3;
    private static final int DISH_TYPE_ACTIVATE_DEACTIVATE_OPTION = 4;

    // DISHES
    private static final int DISH_REGISTER_OPTION = 5;
    private static final int DISH_LIST_OPTION = 6;
    private static final int DISH_REGISTER_DTO_OPTION = 7;
    private static final int DISH_LIST_DTO_OPTION = 8;
    private static final int DISH_ACTIVATE_DEACTIVATE_OPTION = 9;
    private static final int DISH_CHANGE_OPTION = 10;

    //MENUS
    private static final int PUBLISH_MENU_OPTION = 10;
    private static final int REGISTER_MENU_OPTION = 11;
    private static final int EDIT_MENU_OPTION = 12;

    //MEALS
    private static final int REGISTER_MEAL_OPTION = 12;
    private static final int CHECK_MEAL_RATING_OPTION = 13;
    private static final int REPLY_COMMENT_OPTION = 14;

    // DISH PROPERTIES
    private static final int CHANGE_DISH_NUTRICIONAL_INFO_OPTION = 1;
    private static final int CHANGE_DISH_PRICE_OPTION = 2;

    // MATERIALS
    private static final int MATERIAL_REGISTER_OPTION = 1;
    private static final int MATERIAL_LIST_OPTION = 2;
    private static final int FIND_MEALS_BY_LOT_OPTION = 3;
    private static final int CANTEEN_SHIFT_CLOSURE_OPTION = 4;
    private static final int CREATE_MEAL_PLAN_OPTION = 5;
    private static final int REGISTER_MEALS_ACTUALLY_COOKED = 6;
    private static final int REGISTER_LOTS_USED_IN_MEAL = 7;
    private static final int LIST_LOTS_USED_IN_MEAL = 8;
    private static final int CHECK_BOOKINGS_BY_DATA = 9;
    private static final int CLOSE_MEAL_PLAN = 10;
    private static final int GENERATE_MEAL_PLAN_OPTION = 11;

    // REPORTING
    private static final int REPORTING_DISHES_PER_DISHTYPE_OPTION = 1;
    private static final int REPORTING_HIGH_CALORIES_DISHES_OPTION = 2;
    private static final int REPORTING_DISHES_PER_CALORIC_CATEGORY_OPTION = 3;

    // MAIN MENU
    private static final int MY_USER_OPTION = 1;
    private static final int USERS_OPTION = 2;
    private static final int SETTINGS_OPTION = 4;
    private static final int DISH_TYPES_OPTION = 5;
    private static final int TRACEABILITY_OPTION = 6;
    private static final int REPORTING_DISHES_OPTION = 7;
    private static final int MENUS_OPTION = 8;
    private static final int MEAL_OPTION = 9;
//    private static final int CHANGE_NUTRI_PROFILE_OPTION = 10;
    private static final int RATING_OPTION = 11;

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
        if(AuthorizationService.session().authenticatedUser().isAuthorizedTo(ActionRight.MANAGE_KITCHEN, ActionRight.MANAGE_MENUS)){
           
        }
        return renderer.show();
    }
    
    @Override
    public String headline() {
        return "eCafeteria Back Office [@" + AuthorizationService.session().authenticatedUser().id()
                + "]";
    }

    private Menu buildMainMenu() {
        final Menu mainMenu = new Menu();

        final Menu myUserMenu = new MyUserMenu();
        mainMenu.add(
                new SubMenu(MY_USER_OPTION, myUserMenu, new ShowVerticalSubMenuAction(myUserMenu)));

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.add(VerticalSeparator.separator());
        }

        if (AuthorizationService.session().authenticatedUser()
                .isAuthorizedTo(ActionRight.ADMINISTER)) {
            final Menu usersMenu = buildUsersMenu();
            mainMenu.add(
                    new SubMenu(USERS_OPTION, usersMenu, new ShowVerticalSubMenuAction(usersMenu)));
            final Menu settingsMenu = buildAdminSettingsMenu();
            mainMenu.add(new SubMenu(SETTINGS_OPTION, settingsMenu,
                    new ShowVerticalSubMenuAction(settingsMenu)));
        }
        if (AuthorizationService.session().authenticatedUser()
                .isAuthorizedTo(ActionRight.MANAGE_KITCHEN)) {
            final Menu kitchenMenu = buildKitchenMenu();
            mainMenu.add(new SubMenu(TRACEABILITY_OPTION, kitchenMenu,
                    new ShowVerticalSubMenuAction(kitchenMenu)));
        }
        if (AuthorizationService.session().authenticatedUser()
                .isAuthorizedTo(ActionRight.MANAGE_MENUS)) {
            final Menu dishTypeMenu = buildDishMenu();
            mainMenu.add(new SubMenu(DISH_TYPES_OPTION, dishTypeMenu,
                    new ShowVerticalSubMenuAction(dishTypeMenu)));
            final Menu reportingDishesMenu = buildReportingDishesMenu();
            mainMenu.add(new SubMenu(REPORTING_DISHES_OPTION, reportingDishesMenu,
                    new ShowVerticalSubMenuAction(reportingDishesMenu)));
            final Menu menuMenu = buildMenuMenu();
            mainMenu.add(new SubMenu(MENUS_OPTION, menuMenu,
                    new ShowVerticalSubMenuAction(menuMenu)));
            final Menu mealMenu = builMealMenu();
            mainMenu.add(new SubMenu(MEAL_OPTION, mealMenu,
                    new ShowVerticalSubMenuAction(mealMenu)));
            final Menu ratingMenu = builRatingMenu();
            mainMenu.add(new SubMenu(RATING_OPTION, ratingMenu,
                    new ShowVerticalSubMenuAction(ratingMenu)));
            // reporting
        }


        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.add(VerticalSeparator.separator());
        }

        mainMenu.add(new MenuItem(EXIT_OPTION, "Exit", new ExitWithMessageAction()));

        return mainMenu;
    }

    private Menu buildAdminSettingsMenu() {
        final Menu menu = new Menu("Settings >");

        menu.add(new MenuItem(SET_KITCHEN_ALERT_LIMIT_OPTION, "Set kitchen alert limit",
                new ConfigureKitchenAlertLimitsAction()));
        menu.add(new MenuItem(SELECT_HEURISTIC_OPTION, "Select Heuristic",
                new SelectHeuristicAction()));
        menu.add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));

        return menu;
    }

    private Menu buildUsersMenu() {
        final Menu menu = new Menu("Users >");

        // users menu stuff that you can do
        menu.add(new MenuItem(ADD_USER_OPTION, "Add User", () -> new AddUserUI().show()));
        menu.add(new MenuItem(LIST_USERS_OPTION, "List all Users", new ListUsersAction()));
        menu.add(new MenuItem(DEACTIVATE_USER_OPTION, "Deactivate User",
                new DeactivateUserAction()));
        menu.add(new MenuItem(ACCEPT_REFUSE_SIGNUP_REQUEST_OPTION, "Accept/Refuse Signup Request",
                new AcceptRefuseSignupRequestAction()));
        menu.add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));

        return menu;
    }

    private Menu buildDishMenu() {
        final Menu menu = new Menu("Dishes >");

        // dish types
        menu.add(new MenuItem(DISH_TYPE_REGISTER_OPTION, "Register new Dish Type",
                new RegisterDishTypeAction()));
        menu.add(new MenuItem(DISH_TYPE_LIST_OPTION, "List all Dish Type",
                new ListDishTypeAction()));
        menu.add(new MenuItem(DISH_TYPE_CHANGE_OPTION, "Change Dish Type description",
                new ChangeDishTypeAction()));
        menu.add(new MenuItem(DISH_TYPE_ACTIVATE_DEACTIVATE_OPTION, "Activate/Deactivate Dish Type",
                new ActivateDeactivateDishTypeAction()));

        // dishes
        menu.add(new MenuItem(DISH_REGISTER_OPTION, "Register new Dish", new RegisterDishAction()));
        menu.add(new MenuItem(DISH_LIST_OPTION, "List all Dish", new ListDishAction()));

        menu.add(new MenuItem(DISH_REGISTER_DTO_OPTION, "Register new Dish (via DTO)",
                () -> new RegisterDishViaDTOUI().show()));
        menu.add(new MenuItem(DISH_LIST_DTO_OPTION, "List all Dish (via DTO)",
                () -> new ListDishViaDTOUI().show()));

        menu.add(new MenuItem(DISH_ACTIVATE_DEACTIVATE_OPTION, "Activate/Deactivate Dish",
                new ActivateDeactivateDishAction()));
        final Menu changeDishMenu = buildChangeDishMenu();
        menu.add(new MenuItem(DISH_CHANGE_OPTION, "Change Dish Information",
                new ShowVerticalSubMenuAction(changeDishMenu)));

        menu.add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));

        return menu;
    }

    private Menu buildKitchenMenu() {
        final Menu menu = new Menu("Traceability >");

        menu.add(new MenuItem(MATERIAL_REGISTER_OPTION, "Register new material",
                new RegisterMaterialAction()));
        menu.add(
                new MenuItem(MATERIAL_LIST_OPTION, "List all materials", new ListMaterialAction()));
        menu.add(
                new MenuItem(FIND_MEALS_BY_LOT_OPTION, "Find Meals By Lot", new FindMealsByLotAction()));

        menu.add(
                new MenuItem(CANTEEN_SHIFT_CLOSURE_OPTION, "Canteen Shift Closure", new CanteenShiftClosureAction()));

        menu.add(new MenuItem(CREATE_MEAL_PLAN_OPTION, "Create Meal Plan", new CreateMealPlanAction()));

        menu.add(new MenuItem(REGISTER_MEALS_ACTUALLY_COOKED, "Register Meals Actually Cooked", new RegisterMealsActuallyCookedAction()));

        menu.add(new MenuItem(REGISTER_LOTS_USED_IN_MEAL, "Register Lots Used In Meal", new RegisterLotsUsedInMealAction()));

        menu.add(new MenuItem(LIST_LOTS_USED_IN_MEAL, "List Lots Used In Meal", new ListLotsUsedInMealAction()));

        menu.add(new MenuItem(CHECK_BOOKINGS_BY_DATA, "Check Bookings By Data", new CheckReservationsByDataAction()));

        menu.add(new MenuItem(CLOSE_MEAL_PLAN, "Close meal plan", new CloseMealPlanAction()));

        menu.add(new MenuItem(GENERATE_MEAL_PLAN_OPTION, "Generate Meal Plan", new GenerateMealPlanAction()));

        menu.add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));

        return menu;
    }

    private Menu buildChangeDishMenu() {
        final Menu menu = new Menu("Change Dish >");

        menu.add(new MenuItem(CHANGE_DISH_NUTRICIONAL_INFO_OPTION, "Change Nutricional Info",
                new ChangeDishNutricionalInfoAction()));
        menu.add(new MenuItem(CHANGE_DISH_PRICE_OPTION, "Change Price",
                new ChangeDishPriceAction()));
        menu.add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));

        return menu;
    }

    private Menu buildReportingDishesMenu() {
        final Menu menu = new Menu("Reporting Dishes >");

        menu.add(new MenuItem(REPORTING_DISHES_PER_DISHTYPE_OPTION, "Dishes per Dish Type",
                () -> new ReportDishesPerDishTypeUI().show()));
        menu.add(new MenuItem(REPORTING_HIGH_CALORIES_DISHES_OPTION, "High Calories Dishes",
                () -> new ReportHighCaloriesDishesUI().show()));
        menu.add(new MenuItem(REPORTING_DISHES_PER_CALORIC_CATEGORY_OPTION,
                "Dishes per Caloric Category",
                () -> new ReportDishesPerCaloricCategoryUI().show()));
        menu.add(new MenuItem(REPORTING_DISHES_PER_CALORIC_CATEGORY_OPTION + 1,
                "Dishes per Caloric Category (as tuples)",
                () -> new ReportDishesPerCaloricCategoryAsTuplesUI().show()));

        menu.add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));

        return menu;
    }

    private Menu buildMenuMenu() {
        final Menu menu = new Menu("Menus >");

        menu.add(new MenuItem(PUBLISH_MENU_OPTION, "Publish Menu",
                () -> new PublishMenuUI().show()));

        menu.add(new MenuItem(REGISTER_MENU_OPTION, "Register Menu",
                () -> new RegisterMenuAction().execute()));

        menu.add(new MenuItem(EDIT_MENU_OPTION, "Edit Menu",
                () -> new EditMenuAction().execute()));

        menu.add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));

        return menu;
    }

    private Menu builMealMenu() {
        final Menu menu = new Menu("Meals >");
        menu.add(new MenuItem(REGISTER_MEAL_OPTION, "Register Meals",
                () -> new RegisterMealAction().execute()));

        menu.add(new MenuItem(CHECK_MEAL_RATING_OPTION, "Check the Ratings of a Meal", new CheckMealRatingsAction()));

        menu.add(new MenuItem(REPLY_COMMENT_OPTION, "Reply to comment from the Ratings of a Meal", new ReplyMealRatingCommentsAction()));

        menu.add(new MenuItem(EXIT_OPTION, "Return", new ReturnAction()));

        return menu;
    }


    private Menu builRatingMenu() {
        final Menu menu = new Menu("Ratings >");
        menu.add(new MenuItem(1, "Ratings", () -> new CheckRatingsAction().execute()));

        menu.add(new MenuItem(EXIT_OPTION, "Return", new ReturnAction()));
        return menu;
    }
}
