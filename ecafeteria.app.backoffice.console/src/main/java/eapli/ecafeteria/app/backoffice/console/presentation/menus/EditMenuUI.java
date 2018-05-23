package eapli.ecafeteria.app.backoffice.console.presentation.menus;

import eapli.ecafeteria.app.backoffice.console.presentation.meals.MealPrinter;
import eapli.ecafeteria.application.meals.RegisterMealController;
import eapli.ecafeteria.application.menus.EditMenuController;
import eapli.ecafeteria.application.menus.PublishMenuController;
import eapli.ecafeteria.application.menus.RegisterMenuController;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.menus.Menu;
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

/**
 *
 * @author Pedro Alves 
 */
public class EditMenuUI extends AbstractUI {

    private final EditMenuController theController = new EditMenuController();
    private final RegisterMenuController theMenuController = new RegisterMenuController();
    private final PublishMenuController thePublishController = new PublishMenuController();
    private final RegisterMealController theMealController = new RegisterMealController();
    private Menu menu;

    Scanner s;
    String auxiliar;
    String dataRecebida;
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Date menuDateBeg = null;
    Date menuDateEnd = null;
    Date aux;

    @Override
    protected boolean doShow() {
        df.setLenient(false);

        Iterable<Menu> menusAvailables = thePublishController.listUnpublishedMenus();
        SelectWidget<Menu> selectorMenu = new SelectWidget<>("Menus Unpublished ", menusAvailables, new MenuPrinter());
        selectorMenu.show();
        if (selectorMenu.selectedOption() == 0) {
            System.out.println("Saida com sucesso! \n\n");
            return true;
        }
        menu = selectorMenu.selectedElement();
        theMenuController.printerMenuInformations(menu);

        List<String> vetorDecisao = new ArrayList<>();
        vetorDecisao.add("Alterar Nome!");
        vetorDecisao.add("Alterar Data!");
        vetorDecisao.add("Alterar Meals!");

        List<String> vetorDatas = new ArrayList<>();
        vetorDatas.add("Alterar Data Início Apenas!");
        vetorDatas.add("Alterar Data Fim Apenas!");
        vetorDatas.add("Alterar Ambas As Datas!");

        List<String> vetorMeals = new ArrayList<>();
        vetorMeals.add("Adicionar Meals ao Menu!!");
        vetorMeals.add("Eliminar Meals do Menu!");

        int decisao;
        int decisaoData;
        int decisaoMeal;

        do {
            SelectWidget<String> selectorEdit = new SelectWidget<>("\n\n\n###     Pretende alterar o quê?     ###", vetorDecisao);
            selectorEdit.show();
            decisao = selectorEdit.selectedOption();

            switch (decisao) {
                case 1:
                    s = new Scanner(System.in);
                    System.out.println("Que nome deseja inserir?");
                    auxiliar = s.nextLine();
                    if (auxiliar != null) {
                        try {
                            if (theController.alterarNome(menu, auxiliar)) {
                                System.out.println("NOME ALTERADO COM SUCESSO");
                                break;
                            }
                        } catch (DataIntegrityViolationException | DataConcurrencyException ex) {
                            System.out.println("Erro no Acesso à dataBase.");
                        }
                    }
                    System.out.println("NOME INVÁLIDO");
                    break;

                case 2:
                    SelectWidget<String> selectorEditData = new SelectWidget<>("\n\n###     Pretende alterar o quê?     ###", vetorDatas);
                    selectorEditData.show();
                    decisaoData = selectorEditData.selectedOption();
                    switch (decisaoData) {
                        case 1: {
                            try {
                                alterarDataInicio(decisaoData);
                            } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
                                System.out.println("Erro no Acesso à dataBase.");
                            }
                        }
                        break;

                        case 2: {
                            try {
                                alterarDataFim();
                            } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
                                System.out.println("Erro no Acesso à dataBase.");
                            }
                        }
                        break;

                        case 3: {
                            try {
                                alterarDataInicio(decisaoData);
                                alterarDataFim();
                            } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
                                System.out.println("Erro no Acesso à dataBase.");
                            }
                        }

                        break;
                        case 0:
                            System.out.println("Processo Concluído Com Sucesso.");
                        default:
                            System.out.println("Você digitou uma operação inválida.");
                    }
                    break;

                case 3:
                    SelectWidget<String> selectorEditMeals = new SelectWidget<>("\n\n###     Pretende fazer o quê?     ###", vetorMeals);
                    selectorEditMeals.show();
                    decisaoMeal = selectorEditMeals.selectedOption();
                    switch (decisaoMeal) {
                        case 1:
                            inserirMeals();
                            break;
                        case 2:
                            eliminarMealsOfMenu();
                            break;
                        case 0:
                            System.out.println("Processo Concluído Com Sucesso.");
                        default:
                            System.out.println("Você digitou uma operação inválida.");
                    }
                    break;
                case 0:
                    System.out.println("Processo Concluído Com Sucesso.");
                    break;
                default:
                    System.out.printf("Você digitou uma operação inválida.");
            }
            System.out.println("PROCESSO GUARDADO COM SUCESSO!!!");
        } while (decisao != 0);

        System.out.println("Saída com sucesso! \n\n");
        theMenuController.printerMenuInformations(menu);
        return true;
    }

    @Override
    public String headline() {
        return "Edit Meal";
    }

    /**
     * Method to edit the start date of the menu.
     */
    private void alterarDataInicio(int i) throws DataConcurrencyException, DataIntegrityViolationException {
        menuDateBeg = null;
        do {
            s = new Scanner(System.in);
            System.out.println("Digite uma data para o início do Menu no seguinte formato --> dd/MM/yyyy: ");
            dataRecebida = s.nextLine();
            try {
                aux = df.parse(dataRecebida);
                if (i == 3) {
                    newStartDate();
                } else {
                    if (theController.alterarDataBeg(menu, aux)) {
                        menuDateBeg = aux;
                        System.out.println("***DADO BEM INSERIDO***\n\n");
                    } else {
                        System.out.println("ATENÇÃO!!! A data inicio do menu tem de ser antes da data fim do menu.");
                    }
                }

            } catch (ParseException ex) {
                System.out.println("#########   FORMATO INVÀLIDO ou DATA INVÀLIDA  #########\n#########  INSIRA NOVAMENTE  #########");
            }
        } while (menuDateBeg == null);
    }

    /**
     * Method to edit the end date of the menu.
     */
    private void alterarDataFim() throws DataConcurrencyException, DataIntegrityViolationException {
        menuDateEnd = null;
        do {
            s = new Scanner(System.in);
            System.out.println("Digite uma data para o fim do Menu no seguinte formato --> dd/MM/yyyy: ");
            dataRecebida = s.nextLine();
            try {
                aux = df.parse(dataRecebida);
                if (theController.alterarDataEnd(menu, aux)) {
                    menuDateEnd = aux;
                    System.out.println("***DADO BEM INSERIDO***\n\n");
                } else {
                    System.out.println("ATENÇÃO!!! A data inicio do menu tem de ser antes da data fim do menu.");
                }
            } catch (ParseException ex) {
                System.out.println("#########   FORMATO INVÀLIDO ou DATA INVÀLIDA  #########\n#########  INSIRA NOVAMENTE  #########");
            }
        } while (menuDateEnd == null);
    }

    /**
     * Method to insert meals to the menu.
     */
    private void inserirMeals() {
        System.out.println("\n###       INSERIR MEALS       ###\n");

        Iterable<Meal> mealsAvailables;
        SelectWidget<Meal> selectorMeal;
        Meal meal;

        mealsAvailables = theMenuController.getAllMealsAvailablesToMenu(menu);

        int selecao = 1;

        if (!mealsAvailables.iterator().hasNext()) {
            System.out.println("###ATENÇÃO --->  Não possui meals registadas no período de tempo do Menu em questão!");
            selecao = 0;
        }

        while (selecao != 0 && mealsAvailables.iterator().hasNext()) {
            selectorMeal = new SelectWidget<>("Meals Availables ", mealsAvailables, new MealPrinter());
            selectorMeal.show();

            if (selectorMeal.selectedOption() != 0) {
                meal = selectorMeal.selectedElement();
                try {
                    theMealController.updateMeal(meal, menu);
                    mealsAvailables = theMenuController.getAllMealsAvailablesToMenu(menu);
                    System.out.println("***DADO BEM INSERIDO***\n\n");
                } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
                    System.out.println("Erro no Acesso aos Dados!");
                }
            }
            selecao = selectorMeal.selectedOption();
        }

        if (selecao == 0) {
            System.out.println("Saída com sucesso!");
        } else {
            System.out.println("Não possui mais meals registadas no período de tempo do Menu em questão!");
        }
    }

    /**
     * Method to delete meals of the menu.
     */
    private void eliminarMealsOfMenu() {
        System.out.println("\n###       ELIMINAR MEALS       ###\n");

        Iterable<Meal> mealsOfMenu;
        SelectWidget<Meal> selectorMeal;
        Meal meal;

        mealsOfMenu = theMenuController.getAllMealsOfMenu(menu);

        int selecao = 1;

        if (!mealsOfMenu.iterator().hasNext()) {
            System.out.println("###ATENÇÃO --->  Não possui meals no Menu!");
            selecao = 0;
        }

        while (selecao != 0 && mealsOfMenu.iterator().hasNext()) {

            selectorMeal = new SelectWidget<>("Meals Of Menu ", mealsOfMenu, new MealPrinter());
            selectorMeal.show();

            if (selectorMeal.selectedOption() != 0) {
                meal = selectorMeal.selectedElement();
                try {
                    theMealController.updateMeal(meal, null);
                    mealsOfMenu = theMenuController.getAllMealsOfMenu(menu);
                    System.out.println("***DADO BEM ELIMINADO***\n\n");
                } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
                    System.out.println("Erro no Acesso aos Dados!");
                }
            }
            selecao = selectorMeal.selectedOption();
        }

        if (selecao == 0) {
            System.out.println("Saída com sucesso!");
        } else {
            System.out.println("Não possui mais meals registadas no menu para eliminar!");
        }
    }

    /**
     * Method that create a new Date.
     */
    private void newStartDate() {
        theController.newStartDate(menu, aux);
        menuDateBeg = aux;
        System.out.println("***DADO BEM INSERIDO***\n\n");
    }
}
