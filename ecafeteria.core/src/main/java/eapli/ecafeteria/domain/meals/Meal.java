/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.meals;

import eapli.framework.domain.Designation;
import java.util.Date;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

/**
 *
 * @author Bernardo Carreira
 */
@Entity
public class Meal {
    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @EmbeddedId
    private Designation name;
    /**
     * cascade = CascadeType.NONE as the dishType is part of another aggregate
     */
    @ManyToOne()
    private MealType mealType;
    private Date mealDate;
    private boolean active;

    public Meal(final MealType mealType, final Date mealDate, final Designation name) {
        if (mealType == null || name == null || mealDate == null) {
            throw new IllegalArgumentException();
        }

        this.mealType = mealType;
        this.mealDate = mealDate;
        this.name = name;
        this.active = true;
    }

    protected Meal() {
        // for ORM only
    }
}
