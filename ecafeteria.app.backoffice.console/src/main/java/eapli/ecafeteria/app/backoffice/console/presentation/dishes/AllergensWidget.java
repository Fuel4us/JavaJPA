package eapli.ecafeteria.app.backoffice.console.presentation.dishes;

import java.util.Set;

/**
 *
 * @author João Pereira <1150478@isep.ipp.pt>
 */
public class AllergensWidget {
    
    /**
     * LinkedList that saves the allergens.
     */
    private Set<String> allergens;

    public void show() {
    }
    
    /**
     * Returns the allergens.
     * @return 
     */
    public Set<String> getAllergenics() {
        return allergens;
    }
    
    public void setAllergenicsList(Set<String> allerg) {
        allergens = allerg;
    }
}
