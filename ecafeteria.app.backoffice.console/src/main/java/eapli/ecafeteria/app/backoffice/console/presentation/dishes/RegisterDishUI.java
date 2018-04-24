package eapli.ecafeteria.app.backoffice.console.presentation.dishes;

import eapli.ecafeteria.application.dishes.RegisterDishController;
import eapli.ecafeteria.domain.dishes.DishType;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.util.Console;
import java.util.LinkedList;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt
 *
 * changed by João Pereira <1150478@isep.ipp.pt>
 */
public class RegisterDishUI extends AbstractUI {

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

        LinkedList<String> newAllergenList = new LinkedList<>();
        final int num = Console.readInteger("Enter the number of allergens of the new list.");
        for (int i = 0; i < num; i++) {
            final String all = Console.readLine("Allergen:");
            if (newAllergenList.contains(all) == false) {
                newAllergenList.add(all);
            } else {
                System.out.println("The allergen inserted is already on the list!");
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
