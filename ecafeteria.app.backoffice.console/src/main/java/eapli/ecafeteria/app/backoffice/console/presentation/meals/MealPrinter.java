package eapli.ecafeteria.app.backoffice.console.presentation.meals;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.visitor.Visitor;

/**
 *
 * @author Pedro Alves <1150372@isep.ipp.pt>
 */
@SuppressWarnings("squid:S106")
public class MealPrinter implements Visitor<Meal> {

    @Override
    public void visit(Meal visitee) {
        System.out.printf("%-30s%-25s%-10s%-4s",
                visitee.getDish().name(), visitee.getDish().currentPrice(),
                visitee.getDish().dishType(), visitee.getDate(), String.valueOf(visitee.isActive()));
    }
}
