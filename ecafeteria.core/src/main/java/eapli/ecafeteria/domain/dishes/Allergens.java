package eapli.ecafeteria.domain.dishes;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author João Pereira <1150478@isep.ipp.pt>
 */
@Entity
public class Allergens implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    private String allergen;

    public Allergens(String allergen) {
        this.allergen = allergen;
    }

    public Allergens() {
    }

    public String getAllergen() {
        return allergen;
    }

    public void setAllergen(String allergen) {
        this.allergen = allergen;
    }

    public Long id() {
        return id;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.allergen);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Allergens other = (Allergens) obj;
        if (!Objects.equals(this.allergen, other.allergen)) {
            return false;
        }
        return true;
    }

}
