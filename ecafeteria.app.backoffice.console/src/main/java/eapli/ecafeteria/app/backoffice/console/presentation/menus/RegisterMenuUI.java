package eapli.ecafeteria.app.backoffice.console.presentation.menus;

import eapli.ecafeteria.app.backoffice.console.presentation.meals.MealPrinter;
import eapli.ecafeteria.application.meals.ListMealService;
import eapli.ecafeteria.application.meals.RegisterMealController;
import eapli.ecafeteria.application.menus.RegisterMenuController;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.menus.Menu;
import eapli.framework.domain.Designation;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pedro Alves <1150372@isep.ipp.pt>
 */
public class RegisterMenuUI extends AbstractUI {

    private final RegisterMenuController theController = new RegisterMenuController();
    private final RegisterMealController theMealController = new RegisterMealController();

    @Override
    protected boolean doShow() {

        Scanner s;
        String dataRecebida;
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        df.setLenient(false);
        Date menuDateBeg = null;
        Date menuDateEnd = null;
        Date aux;

        do {
            s = new Scanner(System.in);
            System.out.println("Digite uma data para o início do Menu no seguinte formato --> dd/MM/yyyy: ");
            dataRecebida = s.nextLine();
            try {
                aux = df.parse(dataRecebida);
                menuDateBeg = aux;
                System.out.println("***DADO BEM INSERIDO***\n\n");
            } catch (ParseException ex) {
                System.out.println("#########   FORMATO INVÀLIDO ou DATA INVÀLIDA  #########\n#########  INSIRA NOVAMENTE  #########");
            }
        } while (menuDateBeg == null);

        do {
            s = new Scanner(System.in);
            System.out.println("Digite uma data para o fim do Menu no seguinte formato --> dd/MM/yyyy: ");
            dataRecebida = s.nextLine();
            try {
                aux = df.parse(dataRecebida);
                if (aux.after(menuDateBeg)) {
                    menuDateEnd = aux;
                    System.out.println("***DADO BEM INSERIDO***\n\n");
                } else {
                    System.out.println("#########       A data fim do MENU tem de ser após a data Início. DATA INICIO :" + menuDateEnd);
                }
            } catch (ParseException ex) {
                System.out.println("#########   FORMATO INVÀLIDO ou DATA INVÀLIDA  #########\n#########  INSIRA NOVAMENTE  #########");
            }
        } while (menuDateEnd == null);

        String name = null;
        do {
            s = new Scanner(System.in);
            System.out.println("Digite um nome para o Menu: ");
            name = s.nextLine();
        } while (name == null || "".equals(name));
        Menu menu;
        try {
            menu = this.theController.registerMenu(menuDateBeg, menuDateEnd, name);
        } catch (DataIntegrityViolationException | DataConcurrencyException ex) {
            Logger.getLogger(RegisterMenuUI.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        List<String> vetInt = new ArrayList<>();
        vetInt.add("Inserir Meals!");

        final SelectWidget<String> selector = new SelectWidget<>("Inserir Meals:", vetInt);
        selector.show();

        Iterable<Meal> mealsAvailables;
        SelectWidget<Meal> selectorMeal;
        Meal meal;

        mealsAvailables = theController.getAllMealsAvailablesToMenu(menu);

        int selecao = selector.selectedOption();
        while (selecao != 0 && mealsAvailables.iterator().hasNext()) {

            selectorMeal = new SelectWidget<>("Meals Availables ", mealsAvailables, new MealPrinter());
            selectorMeal.show();

            if (selectorMeal.selectedOption() != 0) {
                meal = selectorMeal.selectedElement();
                try {
                    theMealController.updateMeal(meal, menu);
                    mealsAvailables = theController.getAllMealsAvailablesToMenu(menu);
                    System.out.println("***DADO BEM INSERIDO***\n\n");
                } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
                    System.out.println("Erro ao inserir a Meal ao Menu!");
                }
            }
            selecao = selectorMeal.selectedOption();
        }

        Iterable<Meal> mealsOfMenu;
        mealsOfMenu = theController.getAllMealsOfMenu(menu);
        System.out.println("***     MENU BEM INSERIDO ***");
        System.out.println(menu.toString());
        System.out.println("*****       MEALS DO MENU       *****");
        System.out.println(mealsOfMenu.toString());
        return true;
    }

    @Override
    public String headline() {
        return "Register Dish";
    }

}
