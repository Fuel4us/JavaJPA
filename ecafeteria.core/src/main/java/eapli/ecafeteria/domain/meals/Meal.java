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
     * Complete constructor for a meal.
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
        this.mealType = mealType;
        this.mealDate = mealDate;
        this.dish = dish;
        if (validaMenu(menu)) {
            this.menu = menu;
        }
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
        this.mealType = mealType;
        this.mealDate = mealDate;
        this.dish = dish;
        this.menu = null;
    }

    /**
     * Constructor that copies the content of another meal.
     *
     * Ver a melhor fomra para depois copiar meals.
     *
     * @param other
     */
    public Meal(Meal other) {
        if (other.dish == null || other.mealType == null) {
            throw new IllegalArgumentException();
        }
        this.mealDate = other.mealDate;
        this.dish = other.dish;
        this.mealType = other.mealType;
        if (validaMenu(menu)) {
            this.menu = other.menu;
        }
    }

    /**
     * Empty Constructor.
     */
    public Meal() {
        // for ORM only
    }

    /**
     * Método que retorna a data da Meal.
     *
     * @return variavel do tipo Date
     */
    public Date getMealDate() {
        return mealDate;
    }

    /**
     * Método que retorna o dish que faz parte da refeição.
     *
     * @return Dish
     */
    public Dish getDish() {
        return dish;
    }

    /**
     * Método que retorno o tipo da Meal, neste caso se é LUNCH or DINNER.
     *
     * @return MealType
     */
    public MealType getMealType() {
        return mealType;
    }

    /**
     * Método que retorna o menu a que pertence esta meal.
     *
     * @return Menu
     */
    public Menu getMenu() {
        return menu;
    }

    /**
     * Método que retorna o id que é definido na database h2 para esta Meal.
     *
     * @return Long
     */
    public Long getId() {
        return id;
    }

    /**
     * Método que aktera o prato da meal.
     *
     * @param newDish
     * @return true or false.
     */
    public boolean changeDishTo(Dish newDish) {
        return setDish(newDish);
    }

    /**
     * Método que altera o prato da meal.
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
     * Método que altera a mealType da meal.
     *
     * @param newMealType
     * @return true or false
     */
    public boolean changeMealTypeTo(MealType newMealType) {
        return setMealType(newMealType);
    }

    /**
     * Método que altera a mealType da meal.
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
     * Método que altera o mealDate da meal.
     *
     * @param date
     * @return
     */
    public boolean changeMealDate(Date date) {
        return setMealDate(date);
    }

    /**
     * Método que altera o mealDate da meal.
     *
     * @param date
     * @return
     */
    private boolean setMealDate(Date date) {
        if (menu != null) {
            if (menu.getStartDate() != null && menu.getEndDate() != null) {
                if (date.after(menu.getStartDate()) && date.before(menu.getEndDate())) {
                    this.mealDate = date;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Método que atribui um menu à Meal em questão.
     *
     * @param menu
     * @return true or false
     */
    public boolean insertMenu(Menu menu) {
        if (validaMenu(menu)) {
            this.menu = menu;
            return true;
        }
        return false;
    }

    /**
     * Método que verifica se a meal é válida para o menu em questão.
     *
     * @param menu
     * @return true or false
     */
    private boolean validaMenu(Menu menu) {
        if (menu.getEndDate() != null && menu.getStartDate() != null) {
            return menu.getStartDate().before(mealDate) && menu.getEndDate().after(mealDate);
        }
        return false;
    }

    /**
     * Método que retorna true caso a meal já tenha sido atribuída a um menu.
     *
     * @return
     */
    public boolean menuAtribuído() {
        return menu != null;
    }

    @Override
    /**
     * Método que verifica se o objeto é o mesmo.
     */
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
     * Método que verifica se os objetos possuem o mesmo MealType, o mesmo Dish
     * e a mesma MealDate.
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
        return "Meal{" + "dish=" + dish.name().toString() + ", mealType=" + mealType + ", mealDate=" + mealDate + ",menu=" + menu.getName().toString() + '}';
    }

    public String toStringDate() {
        SimpleDateFormat data = new SimpleDateFormat("dd-MM-yyyy");
        String data2 = data.format(mealDate.getTime());
        return String.format("DAY: %s /PLATE: %s /TYPE: %s /MENU: %s", data2, dish.name().toString(), mealType.toString(), menu.getName().toString());
    }

    public String toStringOnlyMeal() {
        SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
        String date = data.format(mealDate.getTime());
        return "Dish: " + dish.name().toString() + "\nMeal Type: " + mealType.toString() + "\nMeal Date: " + date;
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
