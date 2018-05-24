package eapli.ecafeteria.app.backoffice.console.presentation.meals;

import eapli.ecafeteria.app.backoffice.console.presentation.dishes.DishPrinter;
import eapli.ecafeteria.application.meals.RegisterMealController;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pedro Alves 
 */
public class RegisterMealUI extends AbstractUI {

    private final RegisterMealController theController = new RegisterMealController();

    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {

        Scanner s;
        String dataRecebida;
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        df.setLenient(false);
        Date mealDate = null;
        Date aux = null;

        do {
            s = new Scanner(System.in);
            System.out.println("Digite uma data no seguinte formato --> dd/MM/yyyy: ");
            dataRecebida = s.nextLine();
            try {
                aux = df.parse(dataRecebida);
                mealDate = aux;
                System.out.println("***DADO BEM INSERIDO***\n\n");
            } catch (ParseException ex) {
                System.out.println("#########   FORMATO INVALIDO ou DATA INVALIDA  #########\n#########  INSIRA NOVAMENTE  #########");
            }
        } while (mealDate == null);

        final Iterable<MealType> mealTypes = this.theController.getMealTypes();

        MealType mealType = null;

        SelectWidget<MealType> selectorMealType = new SelectWidget<>("Meal types:", mealTypes);
        selectorMealType.show();
        do {
            if (selectorMealType.selectedOption() != 0) {
                mealType = selectorMealType.selectedElement();
                System.out.println("***DADO BEM INSERIDO***\n\n");
            } else {
                System.out.println("\n#########       SELECIONE UMA OPCAO VALIDA      #########\n");
                selectorMealType.show();
            }
        } while (mealType == null);

        final Iterable<Dish> dishes = this.theController.getAllDishesActives();
        if (dishes != null) {
            Dish dish = null;

            final SelectWidget<Dish> selectorDish = new SelectWidget<>("Dishes ", dishes, new DishPrinter());
            selectorDish.show();
            do {
                if (selectorDish.selectedOption() != 0) {
                    dish = selectorDish.selectedElement();
                    System.out.println("***DADO BEM INSERIDO***\n\n");
                } else {
                    System.out.println("\n\n#########       SELECIONE UMA OPÇÂO VALIDA      #########\n\n");
                    selectorDish.show();
                }
            } while (dish == null);

            try {
                this.theController.registerMeal(mealType, mealDate, dish);
                System.out.println("\n\n            ###     MEAL BEM INSERIDA       ###");
                System.out.println(this.theController.toString());
                return true;
            } catch (DataIntegrityViolationException | DataConcurrencyException ex) {
                Logger.getLogger(RegisterMealUI.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        System.out.println("ERRO!! Nao existem dishes para serem selecionadas. MEAL nao REGISTADA");
        return false;
    }

    @Override
    public String headline() {
        return "Register Dish";
    }

}