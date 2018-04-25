package eapli.ecafeteria.app.backoffice.console.presentation.dishes;

import java.util.LinkedList;

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
