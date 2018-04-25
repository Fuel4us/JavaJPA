package eapli.ecafeteria.domain.meals;

import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.dto.MealDTO;
import eapli.framework.domain.Designation;
import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.Version;

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
    private Long pk;

    @Version
    private Long version;

    /**
     * Class Changed by: Pedro Alves
     */
    @ManyToOne()
    private MealType mealType;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date mealDate;
    @ManyToOne
    private Dish dish;
    private boolean active;

    public Meal(final MealType mealType, final Date mealDate, final Dish dish) {
        if (mealType == null || dish == null || mealDate == null) {
            throw new IllegalArgumentException();
        }
        this.mealType = mealType;
        this.mealDate = mealDate;
        this.dish = dish;
        this.active = true;
    }

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
        return Objects.equals(this.pk, other.pk);
    }

    public boolean sameAs(Object other) {
        if (!(other instanceof Meal)) {
            return false;
        }

        final Meal that = (Meal) other;
        if (this == that) {
            return true;
        }

        return  mealType.equals(that.mealType)
                && dish.equals(that.dish) && mealDate.equals(that.mealDate)
                && active == that.active;
    }

    public boolean is(Long pk) {
        return Objects.equals(this.pk, pk);
    }

    public MealType mealType() {
        return this.mealType;
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
        return new MealDTO(mealType.toString(),
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
        return "Meal{"
                + "version=" + version
                + ", mealType=" + mealType
                + ", mealDate=" + mealDate
                + ", dish=" + dish
                + ", active=" + active
                + '}';
    }

    public String toString2() {
        SimpleDateFormat data = new SimpleDateFormat("dd-MM-yyyy");
        String data2 = data.format(mealDate.getTime());
        return String.format("DAY: %s /PLATE: %s /TYPE: %s /MEAL: %s", data2, dish.id(), mealType.toString(), dish.dishType().id());
    }
    
    /**
     * Creates a comparator to compare Meals according to their meal dates (joao
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
