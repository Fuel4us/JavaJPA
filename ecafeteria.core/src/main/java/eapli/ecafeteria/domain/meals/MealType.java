/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.meals;

import eapli.ecafeteria.dto.MealTypeDTO;
import eapli.framework.domain.ddd.AggregateRoot;
import eapli.framework.util.Strings;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 *
 * @author Bernardo Carreira
 */
@Entity
public class MealType implements AggregateRoot<String>, Serializable { //implementa AggregateRoot?
    
    private static final long serialVersionUID = 1L;

    // ORM primary key
    @Id
    @GeneratedValue
    private Long pk;
    @Version
    private Long version;

    // business ID
    @Column(unique = true)
    private String acronym;
    private String description;
    private boolean active;

    protected MealType() {
        // for ORM
    }

    /**
     * DishType constructor.
     *
     * @param name
     *            Mandatory
     * @param description
     *            Mandatory
     */
    public MealType(String name, String description) {
        setName(name);
        setDescription(description);
        this.active = true;
    }

    /**
     * Sets and validates newDescription.
     *
     * @param newDescription
     */
    private void setDescription(String newDescription) {
        if (descriptionMeetsMinimumRequirements(newDescription)) {
            this.description = newDescription;
        } else {
            throw new IllegalArgumentException("Invalid Description");
        }
    }

    /**
     * Sets and validates newName.
     *
     * @param newName
     */
    private void setName(String newName) {
        if (nameMeetsMinimumRequirements(newName)) {
            this.acronym = newName;
        } else {
            throw new IllegalArgumentException("Invalid Name");
        }
    }

    /**
     * Ensure name is not null or empty.
     *
     * @param name
     * @return True if name meets minimum requirements. False if name does not
     *         meet minimum requirements.
     */
    private boolean nameMeetsMinimumRequirements(String name) {
        return !Strings.isNullOrEmpty(name);
    }

    /**
     * Ensure description is not null or empty.
     *
     * @param description
     * @return True if description meets minimum requirements. False if
     *         description does not meet minimum requirements.
     */
    private boolean descriptionMeetsMinimumRequirements(String description) {
        return !Strings.isNullOrEmpty(description);
    }

    public String description() {
        return this.description;
    }

    public boolean isActive() {
        return this.active;
    }

    /**
     * Toggles the state of the mealType, activating it or deactivating it
     * accordingly.
     *
     * @return whether the mealType is active or not
     */
    public boolean toogleState() {

        this.active = !this.active;
        return isActive();
    }

    /**
     * Change DishType description
     *
     * @param newDescription
     *            New description.
     */
    public void changeDescriptionTo(String newDescription) {
        if (!descriptionMeetsMinimumRequirements(newDescription)) {
            throw new IllegalArgumentException();
        }
        this.description = newDescription;
    }

    @Override
    public boolean is(String id) {
        return id.equalsIgnoreCase(this.acronym);
    }

    @Override
    public String id() {
        return this.acronym;
    }

    @Override
    public boolean sameAs(Object other) {
        final MealType mealType = (MealType) other;
        return this.equals(mealType) && description().equals(mealType.description())
                && isActive() == mealType.isActive();
    }

    @Override
    public int hashCode() {
        return this.acronym.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MealType)) {
            return false;
        }

        final MealType other = (MealType) o;
        return id().equals(other.id());
    }

    public MealTypeDTO toDTO() {
        return new MealTypeDTO(acronym, description, active);
    }
}
