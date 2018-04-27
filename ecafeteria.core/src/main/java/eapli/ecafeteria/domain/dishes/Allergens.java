package eapli.ecafeteria.domain.dishes;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 *
 * @author João Pereira <1150478@isep.ipp.pt>
 */
@Entity
public class Allergens implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @Version
    private Long version;

    /**
     * List that saves the allergens of an certain dish.
     */
    private Set<String> allerg;

    /**
     * Constructor of the class
     *
     * @param allerg
     */
    public Allergens(Set<String> allerg) {
        if (allerg == null) {
            throw new IllegalArgumentException("Allergenics can't be null!");
        }
        this.allerg = allerg;
    }

    /**
     * Empty constructor of the class dish.
     */
    public Allergens() {
        allerg = new HashSet<>();
    }

    public Set<String> getAllerg() {
        return allerg;
    }

    public void setAllerg(Set<String> allerg) {
        this.allerg = allerg;
    }

    public Long id() {
        return id;
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
        return "The allergens are: " + allerg.toString();
    }

}
