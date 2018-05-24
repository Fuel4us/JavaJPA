package eapli.ecafeteria.app.backoffice.console.presentation.ratings;

import eapli.ecafeteria.app.backoffice.console.presentation.dishes.DishPrinter;
import eapli.ecafeteria.app.backoffice.console.presentation.meals.MealPrinter;
import eapli.ecafeteria.app.backoffice.console.presentation.menus.MenuPrinter;
import eapli.ecafeteria.application.ratings.CheckRatingsController;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.domain.menus.Menu;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.util.DateTime;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Pedro Alves
 */
public class CheckRatingsUI extends AbstractUI {

    private final CheckRatingsController theController = new CheckRatingsController();

    Scanner s;
    String dataRecebida;
    Date aux;
    Date aux2;

    Set<Integer> ratings = new HashSet<>();

    @Override
    protected boolean doShow() {
        List<String> vetorDecisao = new ArrayList<>();
        vetorDecisao.add("Rating By Menu!");
        vetorDecisao.add("Rating By Meal!");
        vetorDecisao.add("Rating By MealType!");
        vetorDecisao.add("Rating By Dish!");
        vetorDecisao.add("Rating By Date!");

        int decisao;

        do {

            SelectWidget<String> selectorEdit = new SelectWidget<>("\n\n\n###     Pretende alterar o que?     ###", vetorDecisao);
            selectorEdit.show();
            decisao = selectorEdit.selectedOption();

            switch (decisao) {
                case 1:
                    ratingsByMenu();
                    break;

                case 2:
                    ratingsByMeal();
                    break;

                case 3:
                    ratingsByMealType();
                    break;
                case 4:
                    ratingsByDish();
                    break;
                case 5:
                    ratingsByDate();
                    break;
                case 0:
                    System.out.println("Processo Concluido Com Sucesso.");
                    break;
                default:
                    System.out.printf("Voce digitou uma operacao invalida.");
            }
        } while (decisao != 0);

        return true;
    }

    @Override
    public String headline() {
        return "RATINGS";
    }

    private void ratingsByDate() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        df.setLenient(false);

        do {
            s = new Scanner(System.in);
            System.out.println("Digite uma data para fazermos a estatistica desse mesmo dia, no seguinte formato --> dd/MM/yyyy: ");
            dataRecebida = s.nextLine();
            try {
                aux = df.parse(dataRecebida);
                if (aux.before(new Date())) {
                    aux2 = aux;
                    System.out.println("### Rating by date : " + aux.toString());
                    theController.showRatings(theController.getRatingsByDate(aux));
                } else {
                    aux2 = null;
                    System.out.println("ATENCAO!!! A data tem de ser de pelo menos um dia anterior ao atual!");
                }
            } catch (ParseException ex) {
                aux2 = null;
                System.out.println("#########   FORMATO INVALIDO ou DATA INVALIDA  #########\n#########  INSIRA NOVAMENTE  #########");
            }
        } while (aux == null);
    }

    private void ratingsByDish() {
        final Iterable<Dish> dishes = this.theController.getAllDishes();
        Dish dish;
        if (dishes != null) {
            dish = null;
            final SelectWidget<Dish> selectorDish = new SelectWidget<>("Dishes ", dishes, new DishPrinter());
            selectorDish.show();
            do {
                if (selectorDish.selectedOption() != 0) {
                    dish = selectorDish.selectedElement();
                    theController.showRatings(theController.getRatingsByDish(dish));
                } else {
                    if (selectorDish.selectedOption() == 0) {
                        System.out.println("Saida com sucesso");
                        break;
                    } else {
                        System.out.println("\n\n#########       SELECIONE UMA OPCAO VALIDA      #########\n\n");
                        selectorDish.show();
                    }
                }
            } while (dish == null);
        }
    }

    private void ratingsByMealType() {
        final Iterable<MealType> mealTypes = this.theController.getAllMealTypes();
        MealType mealType;
        if (mealTypes != null) {
            mealType = null;
            final SelectWidget<MealType> selectorMealType = new SelectWidget<>("MealTypes ", mealTypes, new MealTypePrinter());
            selectorMealType.show();
            do {
                if (selectorMealType.selectedOption() != 0) {
                    mealType = selectorMealType.selectedElement();
                    theController.showRatings(theController.getRatingsByMealType(mealType));
                } else {
                    if (selectorMealType.selectedOption() == 0) {
                        System.out.println("Saida com sucesso");
                        break;
                    } else {
                        System.out.println("\n\n#########       SELECIONE UMA OPCAO VALIDA      #########\n\n");
                        selectorMealType.show();
                    }
                }
            } while (mealType == null);
        }
    }

    private void ratingsByMeal() {
        final Iterable<Meal> meals = this.theController.getAllMealsDelivered();
        Meal meal;
        if (meals != null) {
            meal = null;
            final SelectWidget<Meal> selectorMeal = new SelectWidget<>("Meals ", meals, new MealPrinter());
            selectorMeal.show();
            do {
                if (selectorMeal.selectedOption() != 0) {
                    meal = selectorMeal.selectedElement();
                    theController.showRatings(theController.getRatingsByMeal(meal));
                } else {
                    if (selectorMeal.selectedOption() == 0) {
                        System.out.println("Saida com sucesso");
                        break;
                    } else {
                        System.out.println("\n\n#########       SELECIONE UMA OPCAO VALIDA      #########\n\n");
                        selectorMeal.show();
                    }
                }

            } while (meal == null);
        }
    }

    private void ratingsByMenu() {
        final Iterable<Menu> menus = this.theController.getAllMenusDelivered();
        Menu menu;
        if (menus != null) {
            menu = null;
            final SelectWidget<Menu> selectorMenu = new SelectWidget<>("Menus ", menus, new MenuPrinter());
            selectorMenu.show();
            do {
                if (selectorMenu.selectedOption() != 0) {
                    menu = selectorMenu.selectedElement();
                    theController.showRatings(theController.getRatingsByMenu(menu));
                } else {
                    if (selectorMenu.selectedOption() == 0) {
                        System.out.println("Saida com sucesso");
                        break;
                    } else {
                        System.out.println("\n\n#########       SELECIONE UMA OPCAO VALIDA      #########\n\n");
                        selectorMenu.show();
                    }

                }
            } while (menu == null);
        }
    }
}
