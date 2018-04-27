package eapli.ecafeteria.app.backoffice.console.presentation.dishes;

import eapli.ecafeteria.application.dishes.RegisterDishController;
import eapli.ecafeteria.domain.dishes.DishType;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.util.Console;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt
 *
 * changed by João Pereira <1150478@isep.ipp.pt>
 */
public class RegisterDishUI extends AbstractUI {

    private int flag = 0;

    private final RegisterDishController theController = new RegisterDishController();

    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {
        final Iterable<DishType> dishTypes = this.theController.dishTypes();

        final SelectWidget<DishType> selector = new SelectWidget<>("Dish types:", dishTypes, new DishTypePrinter());
        selector.show();
        final DishType theDishType = selector.selectedElement();

        final String name = Console.readLine("Name:");

        final NutricionalInfoDataWidget nutricionalData = new NutricionalInfoDataWidget();
        nutricionalData.show();

        final AllergensWidget allerg = new AllergensWidget();

        final double price = Console.readDouble("Price:");

        Set<String> newAllergenList = new HashSet<>();

        System.out.println("Insert the allergens of the dish:");
        while (flag == 0) {
            final String all = Console.readLine("Allergen: (type 'exit' to finish)");
            if (!all.equals("exit")) {
                newAllergenList.add(all);
            } else {
                flag+=1;
            }
        }
        allerg.setAllergenicsList(newAllergenList);

        try {
            this.theController.registerDish(theDishType, name, nutricionalData.calories(), nutricionalData.salt(),
                    price, allerg.getAllergenics());
        } catch (final DataIntegrityViolationException | DataConcurrencyException e) {
            System.out.println("You tried to enter a dish which already exists in the database.");
        }

        return false;
    }

    @Override
    public String headline() {
        return "Register Dish";
    }
}
