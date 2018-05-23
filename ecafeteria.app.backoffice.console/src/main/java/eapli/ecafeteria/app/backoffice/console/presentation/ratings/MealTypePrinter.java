package eapli.ecafeteria.app.backoffice.console.presentation.ratings;

import eapli.ecafeteria.domain.meals.MealType;
import eapli.framework.visitor.Visitor;

/**
 *
 * @author Pedro Alves 
 */
@SuppressWarnings("squid:S106")
public class MealTypePrinter implements Visitor<MealType> {

    @Override
    public void visit(MealType visitee) {
        System.out.printf("%-10s",
                visitee.name());
    }
}
