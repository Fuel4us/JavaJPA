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
    //private Periode periode;                      A exclarecer
    //private NutricionalInfo nutricionalInfo;      A exclarecer
    //private Money price;                          A exclarecer
    private boolean active;

    public Meal(final MealType mealType, final Designation name/*,
            final NutricionalInfo nutricionalInfo, Money price*/) {             // A exclarecer
        if (mealType == null || name == null/* || nutricionalInfo == null*/) {  // A exclarecer
            throw new IllegalArgumentException();
        }

        this.mealType = mealType;
        this.name = name;
        //this.nutricionalInfo = nutricionalInfo;   A exclarecer
        //this.setPrice(price);                     A exclarecer
        this.active = true;
    }

    /*public Meal(final MealType mealType, final Designation name, Money price) {  // A exclarecer
        if (mealType == null || name == null || price == null) {
            throw new IllegalArgumentException();
        }

        this.mealType = mealType;
        this.name = name;
        //this.nutricionalInfo = null;
        //this.price = price;
        this.active = true;
    }*/

    protected Meal() {
        // for ORM only
    }
}
