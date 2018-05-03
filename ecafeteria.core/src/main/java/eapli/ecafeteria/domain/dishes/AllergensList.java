package eapli.ecafeteria.domain.dishes;

import java.util.HashSet;

import java.util.Set;

/**
 *
 * @author João Pereira <1150478@isep.ipp.pt>
 */
public class AllergensList {

    private static final Set<Allergen> allergenList = new HashSet<>();

    public static boolean addAllerg(String acronym, String description) {
        return allergenList.add(new Allergen(acronym, description));
    }

    public static boolean removeAllerg(Allergen allerg) {
        return allergenList.remove(allerg);
    }

    public static Set<Allergen> getAllergList() {
        return allergenList;
    }

}
