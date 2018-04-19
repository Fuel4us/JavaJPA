package eapli.ecafeteria.app.backoffice.console.presentation.meals;

import eapli.ecafeteria.domain.meals.MealType;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.visitor.Visitor;

/**
 *
 * @author Pedro Alves <1150372@isep.ipp.pt>
 */
public class ListMealTypeUI extends AbstractListUI<MealType> {

//	private final ListMealTypeController theController = new ListMealTypeController();
//
//	protected Controller controller() {
//		return this.theController;
//	}
//	@Override
//	protected Iterable<MealType> elements() {
//		return this.theController.listMealTypes();
//	}
    @Override
    protected Visitor<MealType> elementPrinter() {
        return new MealTypePrinter();
    }

    @Override
    protected String elementName() {
        return "Meal Type";
    }

    @Override
    protected String listHeader() {
        return "MEAL TYPES";
    }

    @Override
    protected Iterable<MealType> elements() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
