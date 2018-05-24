package eapli.ecafeteria.domain.dishes;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author @João Pereira_1150478@isep.ipp.pt
 */
@Entity
public class Allergen implements Serializable {

    /**
     * Instance variable that defines the serial version.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Instance variable that defines the id of the allergen.
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * Instance variables that define the characteristics of the allergen.
     */
    private String acronym;
    private String description;

    /**
     * Complete constructor of the class.
     *
     * @param acronym
     * @param description
     */
    public Allergen(String acronym, String description) {
        this.acronym = acronym;
        this.description = description;
    }

    /**
     * Empty constructor of the class.
     */
    public Allergen() {
    }

    /**
     * Returns the acronym of the allergen.
     *
     * @return
     */
    public String getAcronym() {
        return acronym;
    }

    /**
     * Returns the description of the allergen.
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the id.
     *
     * @return
     */
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

    /**
     * Equals method.
     *
     * @param obj
     * @return
     */
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

    /**
     * To String method.
     *
     * @return
     */
    @Override
    public String toString() {
        return description + ";";
    }

}
