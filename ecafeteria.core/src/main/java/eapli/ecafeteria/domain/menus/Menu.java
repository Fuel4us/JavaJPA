package eapli.ecafeteria.domain.menus;

import eapli.ecafeteria.application.menus.RegisterMenuController;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.domain.Designation;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Bernardo Carreira
 * @EDIT Pedro Alves <1150372@isep.ipp.pt>
 */
@Entity
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    private Designation name;

    //(changed by Joao Reis 1160600)
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Temporal(TemporalType.DATE)
    private Date endDate;
    private boolean published;

    /**
     * Complete builder.
     *
     * @param startDate
     * @param endDate
     * @param name
     */
    public Menu(Date startDate, Date endDate, Designation name) {
        if (startDate == null || endDate == null || name == null) {
            throw new IllegalArgumentException();
        }
        changeName(name);
        changeStartDate(startDate);
        changeEndDate(endDate);
        this.published = false;
    }

    /**
     * Partial builder.
     *
     * @param startDate
     * @param endDate
     */
    public Menu(Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException();
        }
        this.name = Designation.valueOf("Nome não inserido!!!");
        changeStartDate(startDate);
        changeEndDate(endDate);
        this.published = false;
    }

    /**
     * Empty builder.
     */
    public Menu() {
        // for ORM only
    }

    /**
     * Return id of menu.
     *
     * @return
     */
    public Long id() {
        return id;
    }

    /**
     * Return start date of Menu.
     *
     * @return
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Return end date of Menu.
     *
     * @return
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Return true or false if it has been published and false if it has not.
     *
     * @return
     */
    public boolean isPublished() {
        return published;
    }

    /**
     * Return name of menu.
     *
     * @return
     */
    public Designation getName() {
        return name;
    }

    /**
     * Edit name of Menu.
     *
     * @param name
     * @return
     */
    public boolean setName(Designation name) {
        return changeName(name);
    }

    /**
     * Edit name.
     *
     * @param name
     * @return
     */
    private boolean changeName(Designation name) {
        if (name != null) {
            this.name = name;
            return true;
        }
        return false;
    }

    /**
     * Edit start date.
     *
     * @param date
     * @return
     */
    public boolean setStartDate(Date date) {
        return changeStartDate(date);
    }

    public void newStartDate(Date date) {
        this.startDate = date;
    }

    /**
     * Edit start date.
     *
     * @param date
     * @return
     */
    private boolean changeStartDate(Date date) {
        if (date != null) {
            if (endDate != null) {
                if (date.before(endDate)) {
                    startDate = date;
                    return true;
                }
            } else {
                startDate = date;
                return true;
            }
        }
        return false;
    }

    /**
     * Edit end date.
     *
     * @param date
     * @return
     */
    public boolean setEndDate(Date date) {
        return changeEndDate(date);
    }

    /**
     * Edit end date.
     *
     * @param date
     * @return
     */
    private boolean changeEndDate(Date date) {
        if (date != null) {
            if (startDate != null) {
                if (date.after(startDate)) {
                    endDate = date;
                    return true;
                }
            } else {
                endDate = date;
                return true;
            }
        }
        return false;
    }

    /**
     * Edit state.
     *
     * @return
     */
    public boolean toogleState() {
        this.published = !this.published;
        return isPublished();
    }

    @Override
    public String toString() {
        SimpleDateFormat data = new SimpleDateFormat("dd-MM-yyyy");
        String data1 = data.format(startDate.getTime());
        String data2 = data.format(endDate.getTime());
        return "Menu --> "
                + " Name: " + name
                + ", startDate: " + data1
                + ", endDate: " + data2;
    }

    /**
     * Return listMeals of that Menu.
     *
     * @return
     */
    public Set<Meal> getMealList() {
        RegisterMenuController rmc = new RegisterMenuController();
        return (Set<Meal>) rmc.getAllMealsOfMenu(this);
    }

    /**
     * Gets a list of meals from a menu in a certain menu period (Joao Reis -
     * 1160600) modificado por outro aluno
     *
     * @param periodStart beginning of established time period
     * @param periodEnd end of established time period
     * @return meals in a menu that belong to a certain time period
     */
    public Set<Meal> mealsInPeriod(Date periodStart, Date periodEnd) {
        Set<Meal> list = new HashSet<>();
        Set<Meal> listMeal = getMealList();
        for (Meal m : listMeal) {
            if (m.getMealDate().after(periodStart) && m.getMealDate().before(periodEnd)) {
                list.add(m);
            }
        }
        return list;
    }

    public boolean publishMenu() {
        if (published) {
            return false;
        } else {
            toogleState();
            return true;
        }
    }

    public void valideMealsOfMenu() throws DataConcurrencyException, DataConcurrencyException, DataIntegrityViolationException {
        final MealRepository mealRepository = PersistenceContext.repositories().meals();
        Set<Meal> mealsOfMenu = getMealList();
        Set<Meal> mealsOfPeriod = mealsInPeriod(startDate, endDate);

        if (mealsOfMenu != null && mealsOfMenu != null) {

            for (Meal meal : mealsOfMenu) {
                if (!mealsOfPeriod.contains(meal)) {
                    meal.insertMenu(null);
                    mealRepository.save(meal);
                }
            }

        }

    }
}
