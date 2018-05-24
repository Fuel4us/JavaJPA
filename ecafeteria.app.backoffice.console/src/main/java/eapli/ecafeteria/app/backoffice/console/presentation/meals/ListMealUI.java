package eapli.ecafeteria.app.backoffice.console.presentation.meals;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.visitor.Visitor;

/**
 *
 * @author Pedro Alves 1150372@isep.ipp.pt
 */
public class ListMealUI extends AbstractListUI<Meal> {

//	private final ListMealController theController = new ListMealController();
//
//	protected Controller controller() {
//		return this.theController;
//	}
//	@Override
//	protected Iterable<Meal> elements() {
//		return this.theController.allMeals();
//	}
    @Override
    protected Visitor<Meal> elementPrinter() {
        return new MealPrinter();
    }

    @Override
    protected String elementName() {
        return "Meal";
    }

    @Override
    protected String listHeader() {
        return "MEALS";
    }

    @Override
    protected Iterable<Meal> elements() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
