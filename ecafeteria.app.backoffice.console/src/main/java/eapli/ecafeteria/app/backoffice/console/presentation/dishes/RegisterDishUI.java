package eapli.ecafeteria.app.backoffice.console.presentation.dishes;

import eapli.ecafeteria.application.dishes.RegisterDishController;
import eapli.ecafeteria.domain.dishes.Allergens;
import eapli.ecafeteria.domain.dishes.AllergensList;
import eapli.ecafeteria.domain.dishes.DishType;
import eapli.ecafeteria.domain.kitchen.Material;
import eapli.ecafeteria.persistence.AllergenRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.util.Console;
import java.util.ArrayList;
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

        Set<Material> newMaterialsList = new HashSet<>();

        /* while (!selectMaterial().equals(null)) {
            newMaterialsList.add(selectMaterial());
        }*/
        try {
            this.theController.registerDish(theDishType, name, nutricionalData.calories(), nutricionalData.salt(),
                    price, getNewAllergenList(), newMaterialsList);
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

    public List<Allergens> getNewAllergenList() {
        int i = 1, flag = 0;
        List<Allergens> newAllergList = new ArrayList<>();
        List<Allergens> listAllerg = this.theController.getAllAllergens();
        for (Allergens allerg : listAllerg) {
            System.out.println(i + " - " + allerg.getAllergen());
            i++;
        }
        System.out.println("Enter 0 to exit!");
        while (flag == 0) {
            int selectAllergen = Console.readInteger("Allergen ID:");
            if (selectAllergen != 0) {
                newAllergList.add(listAllerg.get(selectAllergen - 1));
            } else {
                flag = 1;
            }
        }
        return newAllergList;
    }

    @Override
    public String headline() {
        return "Register Dish";
    }
}
