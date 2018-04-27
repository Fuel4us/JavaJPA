package eapli.ecafeteria.app.backoffice.console.presentation.meals;

import eapli.ecafeteria.app.backoffice.console.presentation.dishes.DishPrinter;
import eapli.ecafeteria.application.dishes.ListDishService;
import eapli.ecafeteria.application.meals.RegisterMealController;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pedro Alves <1150372@isep.ipp.pt>
 */
public class RegisterMealUI extends AbstractUI {

    private final RegisterMealController theController = new RegisterMealController();

    private final ListDishService theDishes = new ListDishService();

    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {
        final Iterable<MealType> mealTypes = this.theController.getMealTypes();

        final SelectWidget<MealType> selectorMealType = new SelectWidget<>("Meal types:", mealTypes);
        selectorMealType.show();
        final MealType mealType = selectorMealType.selectedElement();

//        Date mealDateBegMenu;
//
//        mealDateBegMenu = Console.readDate("Insira a Data de inicio(aaaa-mm-dd)", "aaaa-mm-dd");
        this.theController.validateDate(mealType, new Date());

        final Iterable<Dish> dishes = this.theDishes.allDishes();
        final SelectWidget<Dish> selectorDish = new SelectWidget<>("Dishes ", dishes, new DishPrinter());
        selectorDish.show();
        final Dish dish = selectorDish.selectedElement();

        try {
            this.theController.registerMeal(mealType, new Date(), dish);
            return true;
        } catch (DataIntegrityViolationException | DataConcurrencyException ex) {
            Logger.getLogger(RegisterMealUI.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public String headline() {
        return "Register Dish";
    }

}
