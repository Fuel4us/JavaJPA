package eapli.ecafeteria.domain.menus;

import eapli.ecafeteria.application.meals.ListMealService;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.reporting.dishes.DishesPerCaloricCategory;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 *
 * @author Bernardo Carreira
 * @EDIT Pedro Alves <1150372@isep.ipp.pt>
 */
@Entity
public class Menu {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    //(changed by Joao Reis 1160600)
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Temporal(TemporalType.DATE)
    private Date endDate;
    private boolean published;

    public Menu(Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException();
        }

        this.startDate = startDate;
        this.endDate = endDate;
        this.published = false;
    }

    public Long id() {
        return id;
    }

    public Menu() {
        // for ORM only
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public boolean isPublished() {
        return published;
    }

    public void setStartDate(Date date) {
        startDate = date;
    }

    public void setEndDate(Date date) {
        endDate = date;
    }

    public boolean toogleState() {
        this.published = !this.published;
        return isPublished();
    }

//    public boolean addMeal(Meal meal) {
//        return mealList.add(meal);
//    }
//
//    public boolean removeMeal(Meal remMeal) {
//        return mealList.removeMeal(remMeal);
//    }
    @Override
    public String toString() {
        return "Menu{"
                + ", id=" + id
                + ", startDate=" + startDate
                + ", endDate=" + endDate
                //                + ", mealList=" + mealList.toString()
                + ", published=" + published
                + '}';
    }

    public Set<Meal> getMealList() {
        ListMealService lms = new ListMealService();

        return (Set<Meal>) lms.allMealsOfMenu(this.id);
    }

    /**
     * Gets a list of meals from a menu in a certain menu period (Joao Reis -
     * 1160600)
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

}
