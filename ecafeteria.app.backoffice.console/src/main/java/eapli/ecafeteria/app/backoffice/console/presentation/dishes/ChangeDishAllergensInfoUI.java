package eapli.ecafeteria.app.backoffice.console.presentation.dishes;

import eapli.ecafeteria.application.dishes.ChangeDishController;
import eapli.ecafeteria.domain.dishes.Allergens;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.util.Console;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author João Pereira <1150478@isep.ipp.pt>
 */
public class ChangeDishAllergensInfoUI extends AbstractUI {

    private final ChangeDishController theController = new ChangeDishController();
    private final String ans1 = "insert";
    private final String ans2 = "create";
    private int out = 1;

    protected Controller controller() {
        return this.theController;
    }

    /**
     * Changes the allergens of a certain dish.
     *
     * @return
     */
    @Override
    protected boolean doShow() {

        final Iterable<Dish> allDishes = this.theController.allDishes();
        if (!allDishes.iterator().hasNext()) {
            System.out.println("There are no registered Dishes");
        } else {
            final SelectWidget<Dish> selector = new SelectWidget<>("Dishes:", allDishes, new DishPrinter());
            selector.show();
            final Dish selectedDish = selector.selectedElement();
            System.out.println("Current allergens: " + selectedDish.allergens().getAllerg().toString());

            while (out != 0) {
                final String answer = Console.readLine("You want to insert (write 'insert') new allergens in this list, or create (write 'create') a new list of allergens?");
                if (answer.equalsIgnoreCase(ans1)) {
                    final int num = Console.readInteger("Enter the number of allergens to insert.");
                    for (int i = 0; i < num; i++) {
                        final String all = Console.readLine("Allergen:");
                        if (selectedDish.allergens().getAllerg().contains(all) == false) {
                            selectedDish.allergens().getAllerg().add(all);
                        } else {
                            System.out.println("The allergen inserted is already on the list!");
                        }
                    }
                    out = 0;
                } else if (answer.equalsIgnoreCase(ans2)) {
                    try {
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
                        Allergens all = new Allergens(newAllergenList);
                        this.theController.changeAllergensInfo(selectedDish, all);
                        out = 0;
                    } catch (DataConcurrencyException ex) {
                        System.out.println("It is not possible to change the dish state because it was changed by another user");
                    } catch (DataIntegrityViolationException ex) {
                        // should not happen!
                        Logger.getLogger(ChangeDishPriceUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    System.out.println("Write 'insert' to insert allergens, or 'create' to create a new list of allergens.");
                }
            }
        }
        return true;
    }

    @Override
    public String headline() {
        return "Change Dish Allergens Info";
    }
}
