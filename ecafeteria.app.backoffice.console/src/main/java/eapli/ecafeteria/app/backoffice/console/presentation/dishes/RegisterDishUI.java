package eapli.ecafeteria.app.backoffice.console.presentation.dishes;

import eapli.ecafeteria.application.dishes.RegisterDishController;
import eapli.ecafeteria.domain.dishes.Allergens;
import eapli.ecafeteria.domain.dishes.AllergensList;
import eapli.ecafeteria.domain.dishes.DishType;
import eapli.ecafeteria.domain.kitchen.Material;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.util.Console;
import java.util.HashSet;
import java.util.List;
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

        final double price = Console.readDouble("Price:");

        Set<Allergens> newAllergList = new HashSet<>();
        Set<Material> newMaterialsList = new HashSet<>();
        final SelectWidget<Allergens> show = new SelectWidget<>("Allergen List: (0 to exit)", AllergensList.getAllergList());
        show.show();
        int selectNum = show.selectedOption();
        Allergens selectedAllerg = null;
        if (selectNum != 0) {
            selectedAllerg = show.selectedElement();
        }

        while (selectNum != 0) {
            newAllergList.add(selectedAllerg);
            System.out.println("Próximo alergénico ?");
            selectNum = show.selectedOption();
            if (selectNum != 0) {
                selectedAllerg = show.selectedElement();
            }
        }

        while (!selectMaterial().equals(null)) {
            newMaterialsList.add(selectMaterial());
        }
        try {
            this.theController.registerDish(theDishType, name, nutricionalData.calories(), nutricionalData.salt(),
                    price, newAllergList, newMaterialsList);
        } catch (final DataIntegrityViolationException | DataConcurrencyException e) {
            System.out.println("You tried to enter a dish which already exists in the database.");
        }

        return false;
    }

    public Material selectMaterial() {
        List<Material> listIngredients = this.theController.getAllMaterials();
        for (int i = 0; i < listIngredients.size(); i++) {
            System.out.println(i + 1 + " - " + this.theController.getAllMaterials().get(i));
        }
        System.out.println("Enter 0 to exit!");
        int selectMaterial = Console.readInteger("Material ID:");
        if (selectMaterial != 0) {
            return listIngredients.get(selectMaterial - 1);
        } else {
            return null;
        }
    }

    @Override
    public String headline() {
        return "Register Dish";
    }
}
