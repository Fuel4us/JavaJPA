package eapli.ecafeteria.app.backoffice.console.presentation.menus;

import eapli.ecafeteria.application.meals.ListMealService;
import eapli.ecafeteria.application.meals.RegisterMealController;
import eapli.ecafeteria.application.menus.RegisterMenuController;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pedro Alves <1150372@isep.ipp.pt>
 */
public class RegisterMenuUI extends AbstractUI {

    private final RegisterMenuController theController = new RegisterMenuController();
    private final RegisterMealController theMealController = new RegisterMealController();

    private final ListMealService theMeals = new ListMealService();

    @Override
    protected boolean doShow() {

//        Date mealDateBegMenu;
//
//        mealDateBegMenu = Console.readDate("Insira a Data de inicio(aaaa-mm-dd)", "aaaa-mm-dd");
//
//        Date mealDateEndMenu;
//        mealDateEndMenu = Console.readDate("Insira a Data de fim(aaaa-mm-dd):", "aaaa-mm-dd");
        try {
            this.theController.registerMenu(new Date(), new Date());
            return true;
        } catch (DataIntegrityViolationException | DataConcurrencyException ex) {
            Logger.getLogger(RegisterMenuUI.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public String headline() {
        return "Register Dish";
    }

}
