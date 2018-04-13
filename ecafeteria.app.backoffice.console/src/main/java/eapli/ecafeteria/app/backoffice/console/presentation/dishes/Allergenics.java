package eapli.ecafeteria.app.backoffice.console.presentation.dishes;

import java.util.LinkedList;

/**
 *
 * @author João Pereira <1150478@isep.ipp.pt>
 */
public class Allergenics {
    
    /**
     * LinkedList that saves the allergenics.
     */
    private LinkedList<String> allergenics;

    public Allergenics(LinkedList<String> allergenics) {
        allergenics = new LinkedList<>();
       
    }
    
    /**
     * Returns the allergenics.
     * @return 
     */
    public LinkedList<String> getAllergenics() {
        return allergenics;
    }
}
