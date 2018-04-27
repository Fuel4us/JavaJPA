package eapli.ecafeteria.dto;

import eapli.framework.dto.DTO;
import java.util.Set;

/**
 * a pure DTO for dishes
 *
 * @author SOU03408 changed by João Pereira <1150478@isep.ipp.pt>
 */
@SuppressWarnings("squid:ClassVariableVisibilityCheck")
public class DishDTO implements DTO {

    public DishDTO(String dishTypeAcronym, String dishTypeDescription, String name,
            Integer calories2, Integer salt, double amount, String currency, boolean active, Set<String> allergens) {
        this.dishTypeAcronym = dishTypeAcronym;
        this.dishTypeDescription = dishTypeDescription;
        this.name = name;
        this.calories = calories;
        this.salt = salt;
        price = amount;
        this.currency = currency;
        this.active = active;
        this.allergens = allergens;
    }

    public String dishTypeAcronym;
    public String dishTypeDescription;

    public String name;

    public int calories;
    public int salt;

    public double price;
    public String currency;

    public boolean active;

    public Set<String> allergens;
}
