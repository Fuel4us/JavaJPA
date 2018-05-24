package eapli.ecafeteria.app.backoffice.console.presentation.dishes;

import eapli.ecafeteria.application.dishes.RegisterDishController;
import eapli.ecafeteria.domain.dishes.Allergen;
import eapli.ecafeteria.domain.dishes.DishType;
import eapli.ecafeteria.domain.kitchen.Material;
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
 * changed by @João Pereira_1150478@isep.ipp.pt
 */
public class RegisterDishUI extends AbstractUI {

    private final RegisterDishController theController = new RegisterDishController();

    protected Controller controller() {
        return this.theController;
    }

    /**
     * Do show.
     *
     * @return
     */
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

        List<Material> listIngredients = this.theController.getAllMaterials();
        Set<Material> newMaterialsList = new HashSet<>();
        Material ingredient = selectMaterial(listIngredients);

        while (!ingredient.id().equals(" ")) {
            newMaterialsList.add(ingredient);
            listIngredients.remove(ingredient);
            ingredient = selectMaterial(listIngredients);
        }

        Set<Allergen> allergList = getNewAllergenList();

        try {
            this.theController.registerDish(theDishType, name, nutricionalData.calories(), nutricionalData.salt(),
                    price, allergList, newMaterialsList);
        } catch (final DataIntegrityViolationException | DataConcurrencyException e) {
            System.out.println("You tried to enter a dish which already exists in the database.");
        }

        return false;
    }

    /**
     * Method that returns the selected material by the user.
     *
     * @param listIngredients
     * @return
     */
    public Material selectMaterial(List<Material> listIngredients) {
        for (int i = 0; i < listIngredients.size(); i++) {
            System.out.println(i + 1 + " - " + listIngredients.get(i).description());
        }
        System.out.println("Enter 0 to exit!");
        int selectMaterial = Console.readInteger("Material ID:");
        while (selectMaterial < 0 || selectMaterial > listIngredients.size()) {
            System.out.println("Incorrect Ingredient ID!!! Enter again:\n");
            selectMaterial = Console.readInteger("Material ID:");
        }
        if (selectMaterial != 0 && !listIngredients.isEmpty()) {
            return listIngredients.get(selectMaterial - 1);
        } else {
            return new Material(" ", " ");
        }
    }

    /**
     * Method that returns a set of the allergens selected by the user.
     *
     * @return
     */
    public Set<Allergen> getNewAllergenList() {
        int i = 1, flag = 0;
        Set<Allergen> newAllergList = new HashSet<>();
        List<Allergen> listAllerg = this.theController.getAllAllergens();
        System.out.println("\n ALLERGEN LIST:");
        for (Allergen allerg : listAllerg) {
            System.out.println(" -> " + allerg.getDescription() + "(ID: " + i + ")");
            i++;
        }
        System.out.println("Enter 0 to exit!");
        while (flag == 0) {
            int selectAllergen = Console.readInteger("Allergen ID:");
            if (selectAllergen != 0) {
                if (newAllergList.contains(listAllerg.get(selectAllergen - 1)) == false) {
                    newAllergList.add(listAllerg.get(selectAllergen - 1));
                } else {
                    System.out.println("This allergen is already on the list!");
                }
            } else {
                if (newAllergList.isEmpty()) {
                    System.out.println("Select at least one allergen!");
                } else {
                    flag = 1;
                }
            }
        }
        return newAllergList;
    }

    /**
     * HeadLine.
     *
     * @return
     */
    @Override
    public String headline() {
        return "Register Dish";
    }
}
