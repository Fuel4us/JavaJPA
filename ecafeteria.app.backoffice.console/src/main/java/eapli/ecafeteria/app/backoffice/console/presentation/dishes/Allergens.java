package eapli.ecafeteria.app.backoffice.console.presentation.dishes;

import java.util.LinkedList;
import eapli.framework.util.Console;

/**
 *
 * @author João Pereira <1150478@isep.ipp.pt>
 */
public class Allergens {
    
    /**
     * LinkedList that saves the allergens.
     */
    private LinkedList<String> allergens;

    public void show() {
        for (String allergen : allergens) {
            
        }
    }
    
    /**
     * Returns the allergens.
     * @return 
     */
    public LinkedList<String> getAllergenics() {
        return allergens;
    }
}
