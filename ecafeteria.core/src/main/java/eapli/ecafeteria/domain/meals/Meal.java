/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.meals;

import eapli.framework.domain.Designation;
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
    private NutricionalInfo nutricionalInfo;
    private Money price;
    private boolean active;

    public Dish(final DishType dishType, final Designation name,
            final NutricionalInfo nutricionalInfo, Money price) {
        if (dishType == null || name == null || nutricionalInfo == null) {
            throw new IllegalArgumentException();
        }

        this.dishType = dishType;
        this.name = name;
        this.nutricionalInfo = nutricionalInfo;
        this.setPrice(price);
        this.active = true;
    }

    public Dish(final DishType dishType, final Designation name, Money price) {
        if (dishType == null || name == null || price == null) {
            throw new IllegalArgumentException();
        }

        this.dishType = dishType;
        this.name = name;
        this.nutricionalInfo = null;
        this.price = price;
        this.active = true;
    }

    protected Dish() {
        // for ORM only
    }
}
