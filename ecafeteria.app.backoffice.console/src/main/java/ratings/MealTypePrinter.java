package ratings;

import eapli.ecafeteria.domain.meals.MealType;
import eapli.framework.visitor.Visitor;

/**
 *
 * @author Pedro Alves <1150372@isep.ipp.pt>
 */
@SuppressWarnings("squid:S106")
public class MealTypePrinter implements Visitor<MealType> {

    @Override
    public void visit(MealType visitee) {
        System.out.printf("%-10s",
                visitee.name());
    }
}
