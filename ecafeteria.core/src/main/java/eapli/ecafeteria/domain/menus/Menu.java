package eapli.ecafeteria.domain.menus;

import eapli.ecafeteria.application.meals.ListMealService;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.domain.Designation;
import java.io.Serializable;
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
     * Construtor Completo.
     *
     * @param startDate
     * @param endDate
     * @param name
     */
    public Menu(Date startDate, Date endDate, Designation name) {
        if (startDate == null || endDate == null || name == null) {
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.published = false;
    }

    /**
     * Construtor Parcial.
     *
     * @param startDate
     * @param endDate
     */
    public Menu(Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException();
        }
        this.name = Designation.valueOf("Nome não inserido!!!");
        this.startDate = startDate;
        this.endDate = endDate;
        this.published = false;
    }

    /**
     * Construtor vazio.
     */
    public Menu() {
        // for ORM only
    }

    /**
     * Retorna o id do menu.
     *
     * @return
     */
    public Long id() {
        return id;
    }

    /**
     * Retorna a data inicio do Menu.
     *
     * @return
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Retorna a data fim do Menu.
     *
     * @return
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Retorna true caso tenha sido publicado e false caso não tenha.
     *
     * @return
     */
    public boolean isPublished() {
        return published;
    }

    /**
     * Método que retorna o name do Menu.
     *
     * @return
     */
    public Designation getName() {
        return name;
    }

    /**
     * Método que altera o name do Menu.
     *
     * @param name
     * @return
     */
    public boolean setName(Designation name) {
        return changeName(name);
    }

    /**
     * Método que altera o name do Menu.
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
     * Altera a data inicio do Menu.
     *
     * @param date
     * @return
     */
    public boolean setStartDate(Date date) {
        return changeStartDate(date);
    }

    /**
     * Altera a data inicio do Menu.
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
     * Altera a data fim do Menu.
     *
     * @param date
     * @return
     */
    public boolean setEndDate(Date date) {
        return changeEndDate(date);
    }

    /**
     * Altera a data fim do Menu.
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
     * Altera o estado de publição do menu.
     *
     * @return
     */
    public boolean toogleState() {
        this.published = !this.published;
        return isPublished();
    }

    @Override
    public String toString() {
        return "Menu{"
                + ", Name=" + name
                + ", startDate=" + startDate
                + ", endDate=" + endDate
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
