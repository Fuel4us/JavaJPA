package eapli.ecafeteria.app.backoffice.console.presentation.meals;

import eapli.ecafeteria.domain.meals.MealType;
import eapli.framework.visitor.Visitor;

/**
 *
 * @author Pedro Alves <1150372@isep.ipp.pt>
 */
public class MealTypePrinter implements Visitor<MealType> {

    @Override
    public void visit(MealType visitee) {
        System.out.printf("%-10s%-30s%-4s", visitee.id(), visitee.description(), String.valueOf(visitee.isActive()));
    }

    @Override
    public void beforeVisiting(MealType visitee) {
        // nothing to do
    }

    @Override
    public void afterVisiting(MealType visitee) {
        // nothing to do
    }
}
