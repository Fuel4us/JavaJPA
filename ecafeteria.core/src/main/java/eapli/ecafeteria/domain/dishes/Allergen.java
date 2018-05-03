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
public class Allergen implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    private String acronym;
    private String description;

    public Allergen(String acronym, String description) {
        this.acronym = acronym;
        this.description = description;
    }

    public Allergen() {
    }

    public String getAcronym() {
        return acronym;
    }

    public String getDescription() {
        return description;
    }
    
    public Long id() {
        return id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.acronym);
        hash = 47 * hash + Objects.hashCode(this.description);
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
        final Allergen other = (Allergen) obj;
        if (!Objects.equals(this.acronym, other.acronym)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return description + ";";
    }

}
