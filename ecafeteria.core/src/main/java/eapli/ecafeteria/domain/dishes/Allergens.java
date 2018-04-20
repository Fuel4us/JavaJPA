package eapli.ecafeteria.domain.dishes;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Objects;
import javax.persistence.Embeddable;

/**
 *
 * @author João Pereira <1150478@isep.ipp.pt>
 */
@Embeddable
public class Allergens implements Serializable {

    private LinkedList<String> allerg;

    public Allergens(LinkedList<String> allerg) {
        if (allerg == null) {
            throw new IllegalArgumentException("Allergenics can't be null!");
        }
        this.allerg = allerg;
    }

    public Allergens() {
        allerg = new LinkedList<>();
    }

    public LinkedList<String> getAllerg() {
        return allerg;
    }

    public void setAllerg(LinkedList<String> allerg) {
        this.allerg = allerg;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.allerg);
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
        if (!Objects.equals(this.allerg, other.allerg)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Allergens{" + "allerg=" + allerg + '}';
    }

}
