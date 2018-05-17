package eapli.ecafeteria.domain.meals;

import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.menus.Menu;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Objects;
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

    @OneToOne
    private Menu menu;

    /**
     * Complete builder.
     *
     * @param mealType
     * @param mealDate
     * @param dish
     * @param menu
     */
    public Meal(MealType mealType, Date mealDate, Dish dish, Menu menu) {
        if (dish == null || mealDate == null || mealType == null || menu == null) {
            throw new IllegalArgumentException();
        }
        setMealType(mealType);
        setMealDate(mealDate);
        setDish(dish);
        setMenu(menu);
    }

    /**
     * Partial constructor for a meal.
     *
     * @param mealType
     * @param mealDate
     * @param dish
     */
    public Meal(MealType mealType, Date mealDate, Dish dish) {
        if (dish == null || mealDate == null || mealType == null) {
            throw new IllegalArgumentException();
        }
        setMealType(mealType);
        setMealDate(mealDate);
        setDish(dish);
        this.menu = null;
    }

    /**
     * Builder that copies the content of another meal.
     *
     * Ver a melhor fomra para depois copiar meals.
     *
     * @param other
     */
    public Meal(Meal other) {
        if (other.dish == null || other.mealDate == null || other.mealType == null || other.menu == null) {
            throw new IllegalArgumentException();
        }
        setMealType(other.mealType);
        setMealDate(other.mealDate);
        setDish(other.dish);
        setMenu(other.menu);
    }

    /**
     * Empty builder..
     */
    public Meal() {
        // for ORM only
    }

    /**
     * Return Meal Date.
     *
     * @return variavel do tipo Date
     */
    public Date getMealDate() {
        return mealDate;
    }

    /**
     * Return Dish of Meal.
     *
     * @return Dish
     */
    public Dish getDish() {
        return dish;
    }

    /**
     * Return MealType.
     *
     * @return MealType
     */
    public MealType getMealType() {
        return mealType;
    }

    /**
     * Return Menu.
     *
     * @return Menu
     */
    public Menu getMenu() {
        return menu;
    }

    /**
     * Return id of Meal.
     *
     * @return Long
     */
    public Long getId() {
        return id;
    }

    /**
     * Edit dish of Meal,
     *
     * @param newDish
     * @return true or false.
     */
    public boolean changeDishTo(Dish newDish) {
        return setDish(newDish);
    }

    /**
     * Edit dish of Meal.
     *
     * @param dish
     * @return true or false.
     */
    private boolean setDish(Dish dish) {
        if (dish != null && dish.isActive()) {
            this.dish = dish;
            return true;
        }
        return false;
    }

    /**
     * Edit MealType.
     *
     * @param newMealType
     * @return true or false
     */
    public boolean changeMealTypeTo(MealType newMealType) {
        return setMealType(newMealType);
    }

    /**
     * Edit MealType.
     *
     * @param mealType
     * @return true or false
     */
    private boolean setMealType(MealType mealType) {
        if (mealType != null) {
            this.mealType = mealType;
            return true;
        }
        return false;
    }

    /**
     * Edit MealDate.
     *
     * @param date
     * @return
     */
    public boolean changeMealDate(Date date) {
        return setMealDate(date);
    }

    /**
     * Edit MealDate.
     *
     * @param date
     * @return
     */
    private boolean setMealDate(Date date) {
        if (menu != null) {
            if (!validaMenu(menu)) {
                return false;
            }
        }
        this.mealDate = date;
        return true;
    }

    /**
     * Insert Menu
     *
     * @param menu
     * @return true or false
     */
    public boolean insertMenu(Menu menu) {
        return setMenu(menu);

    }

    /**
     * Edit Menu of Meal.
     *
     * @param menu
     * @return true or false
     */
    private boolean setMenu(Menu menu) {
        if (menu != null) {
            if (validaMenu(menu)) {
                this.menu = menu;
                return true;
            } else {
                return false;
            }
        }
        this.menu = menu;
        return false;
    }

    /**
     * Method that checks if the meal is valid for the menu in question.
     *
     * @param menu
     * @return true or false
     */
    public boolean validaMenu(Menu menu) {
        if (menu.getEndDate() != null && menu.getStartDate() != null) {
            return ((menu.getStartDate().before(mealDate) || menu.getStartDate().equals(mealDate)) && (menu.getEndDate().after(mealDate)) || menu.getEndDate().equals(mealDate));
        }
        return false;
    }

    /**
     * Method that returns true if the meal has already been assigned to a menu.
     *
     * @return
     */
    public boolean menuAtribuído() {
        return menu != null;
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
        return is(other.id);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    /**
     * Method that checks if the objects have the same MealType, the same Dish
     * and the same MealDate.
     *
     * @param other
     * @return true or false
     */
    public boolean sameAs(Object other) {
        if (!(other instanceof Meal)) {
            return false;
        }

        final Meal that = (Meal) other;
        return mealType.equals(that.mealType)
                && dish.equals(that.dish) && mealDate.equals(that.mealDate);
    }

    private boolean is(Long id) {
        return Objects.equals(this.id, id);
    }

    @Override
    public String toString() {
        SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
        String date = data.format(mealDate.getTime());
        return "\nDish: " + dish.name().toString() + " <--> Meal Type: " + mealType.toString() + " <-->Meal Date: " + date;
    }

    public String toStringAll() {
        SimpleDateFormat data = new SimpleDateFormat("dd-MM-yyyy");
        String data2 = data.format(mealDate.getTime());
        return String.format("DAY: %s /PLATE: %s /TYPE: %s /MENU: %s", data2, dish.name().toString(), mealType.toString(), menu.getName().toString());
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
                    return t.getMealDate().compareTo(t1.getMealDate());
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
