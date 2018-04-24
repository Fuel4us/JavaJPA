package eapli.ecafeteria.app.backoffice.console.presentation.dishes;

import eapli.ecafeteria.domain.dishes.Allergens;
import java.util.LinkedList;
import eapli.framework.util.Console;

/**
 *
 * @author João Pereira <1150478@isep.ipp.pt>
 */
public class AllergensWidget {
    
    /**
     * LinkedList that saves the allergens.
     */
    private LinkedList<String> allergens;

    public void show() {
    }
    
    /**
     * Returns the allergens.
     * @return 
     */
    public LinkedList<String> getAllergenics() {
        return allergens;
    }
    
    public void setAllergenicsList(LinkedList<String> allerg) {
        allergens = allerg;
    }
}
