/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.menus;

import eapli.ecafeteria.domain.meals.Meal;
import java.util.Date;
import java.util.List;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

/**
 *
 * @author Bernardo Carreira
 */
@Entity
public class Menu {
    
    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    @GeneratedValue
    private int id;
    /**
     * cascade = CascadeType.NONE as the dishType is part of another aggregate
     */
    @ManyToOne()
    private Date startDate;
    private Date endDate;
    private List<Meal> mealList;
    private boolean published;

    public Menu(final Date startDate, final Date endDate) {
        if (startDate == null || endDate == null || startDate.after(endDate)) {
            throw new IllegalArgumentException();
        }

        this.startDate = startDate;
        this.endDate = endDate;
        this.published = false;
    }

    public int id() {
        return id;
    }

    protected Menu() {
        // for ORM only
    }
    
    public Date getStartDate(){
        return startDate;
    }
    
    public Date getEndDate(){
        return endDate;
    }
    
    public boolean isPublished(){
        return published;
    }
    
    public void setStartDate(Date date){
        startDate = date;
    }
    
    public void setEndDate(Date date){
        endDate = date;
    }
    
    public boolean toogleState() {
        this.published = !this.published;
        return isPublished();
    }
    
    public boolean addMeal(Meal meal){
        //if(){}
        return mealList.add(meal);
    }

    @Override
    public String toString() {
        return "Menu{" +
                "version=" + version +
                ", id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", mealList=" + mealList +
                ", published=" + published +
                '}';
    }

    public List<Meal> getMealList() {
        return mealList;
    }
    
}
