package eapli.ecafeteria.domain.meals;

import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.kitchen.MealLot;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Bernardo Carreira
 * @authorEDIT Pedro Alves <1150372@isep.ipp.pt>
 */
@Entity
public class Meal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Dish dish;
    
    private MealType mealType;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date mealDate;

    /**
     * Complete constructor for a meal.
     *
     * @param mealType
     * @param mealDate
     * @param dish
     * @param mealMenu
     */
    public Meal(MealType mealType, Date mealDate, Dish dish) {
        if (dish == null || mealDate == null) {
            throw new IllegalArgumentException();
        }
        this.mealType = mealType;
        this.mealDate = mealDate;
        this.dish = dish;
       }

    /**
     * Constructor that copies the content of another meal.
     *
     * @param other
     */
    public Meal(Meal other) {
        this.mealDate = other.mealDate;
        this.dish = other.dish;
        this.mealType = other.mealType;
    }

    /**
     * Empty Constructor.
     */
    public Meal() {
        // for ORM only
    }

    public Date getDate() {
        return mealDate;
    }

    public Dish getDish() {
        return dish;
    }

    public MealType getMealType() {
        return mealType;
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
        return Objects.equals(this.id, other.id);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    public boolean sameAs(Object other) {
        if (!(other instanceof Meal)) {
            return false;
        }

        final Meal that = (Meal) other;
        if (this == that) {
            return true;
        }
        return mealType.equals(that.mealType)
                && dish.equals(that.dish) && mealDate.equals(that.mealDate);
    }

    public boolean is(Long id) {
        return Objects.equals(this.id, id);
    }

    public MealType mealType() {
        return this.mealType;
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
        return "Meal{" + "dish=" + dish + ", mealType=" + mealType + ", mealDate=" + mealDate + '}';
    }

    public String toString2() {
        SimpleDateFormat data = new SimpleDateFormat("dd-MM-yyyy");
        String data2 = data.format(mealDate.getTime());
        return String.format("DAY: %s /PLATE: %s /TYPE: %s /MEAL: %s", data2, dish.id(), mealType.toString(), dish.dishType().id());
    }

    /**
     * Creates a comparator to compare Meals according to their meal dates (Joao
     * reis - 1160600)
     *
     * @return comparator object that compares meal dates
     */
    public static Comparator<Meal> compareDates() {
        return new Comparator<Meal>() {
            @Override
            public int compare(Meal t, Meal t1) {
                if (t != null && t1 != null) {
                    return t.getDate().compareTo(t1.getDate());
                }
                if (t1 == null) {
                    return 1;
                } else {
                    return -1;
                }
            }
        };
    }
}
