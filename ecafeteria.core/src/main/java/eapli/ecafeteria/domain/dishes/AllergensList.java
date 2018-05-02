package eapli.ecafeteria.domain.dishes;

import java.util.HashSet;

import java.util.Set;

/**
 *
 * @author João Pereira <1150478@isep.ipp.pt>
 */
public class AllergensList {

    private static final Set<Allergens> allergenList = new HashSet<>();

    public static boolean addAllerg(String acronym, String description) {
        return allergenList.add(new Allergens(acronym, description));
    }

    public static boolean removeAllerg(Allergens allerg) {
        return allergenList.remove(allerg);
    }

    public static Set<Allergens> getAllergList() {
        return allergenList;
    }

}
