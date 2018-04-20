/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.meals;

import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.dto.MealDTO;
import eapli.framework.domain.Designation;
import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;
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
public class Meal implements AggregateRoot<Designation>, Serializable { //implementa aggregatedroot?

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @EmbeddedId
    private Designation name;
    /**
     * cascade = CascadeType.NONE as the dishType is part of another aggregate
     */
    private MealType mealType;
    private Date mealDate;
    private Dish dish;
    private boolean active;
    

    public Meal(final MealType mealType, final Date mealDate, final Dish dish, final Designation name) {
        if (mealType == null || name == null || dish == null || mealDate == null) {
            throw new IllegalArgumentException();
        }

        this.mealType = mealType;
        this.mealDate = mealDate;
        this.dish = dish;
        this.name = name;
        this.active = true;
    }

    protected Meal() {
        // for ORM only
    }

    public Date getDate() {
        return mealDate;
    }

    public Dish getDish() {
        return dish;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Meal)) {
            return false;
        }

        final Meal other = (Meal) o;
        return id().equals(other.id());
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof Meal)) {
            return false;
        }

        final Meal that = (Meal) other;
        if (this == that) {
            return true;
        }

        return id().equals(that.id()) && mealType.equals(that.mealType)
                && dish.equals(that.dish) && mealDate.equals(that.mealDate)
                && active == that.active;
    }

    @Override
    public boolean is(Designation id) {
        return id().equals(id);
    }

    public MealType mealType() {
        return this.mealType;
    }

    @Override
    public Designation id() {
        return this.name;
    }

    public Designation name() {
        return this.name;
    }

    /**
     *
     * @return true or false whether is or not active
     */
    public boolean isActive() {
        return this.active;
    }

    /**
     * toggles the state of the dish, activating it or deactivating it
     * accordingly.
     *
     * @return whether the dish is active or not
     */
    public boolean toogleState() {
        this.active = !this.active;
        return isActive();
    }

    public MealDTO toDTO() {
        return new MealDTO(mealType.toString(), name.toString(),
                dish.dishType().id(), dish.dishType().description(), dish.nutricionalInfo().calories(),
                dish.nutricionalInfo().salt(), dish.currentPrice().amount(),
                dish.currentPrice().currency().getCurrencyCode(), active);
    }

    public void changeDishTo(Dish newDish) {
        setDish(newDish);
    }

    private void setDish(Dish dish) {
        if (dish == null || !dish.isActive()) {
            throw new IllegalArgumentException();
        }
        this.dish = dish;
    }

    public void changeMealTypeTo(MealType newMealType) {
        setMealType(newMealType);
    }

    private void setMealType(MealType mealType) {
        if (mealType == null) {
            throw new IllegalArgumentException();
        }
        this.mealType = mealType;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "version=" + version +
                ", name=" + name +
                ", mealType=" + mealType +
                ", mealDate=" + mealDate +
                ", dish=" + dish +
                ", active=" + active +
                '}';
    }
}
