/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.kitchen;

import eapli.ecafeteria.domain.meals.Meal;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Tiago Babo 1160760
 */
@Entity
public class MealPlanItem {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @OneToOne
    private Meal mealItem;
    
    @OneToOne(cascade = CascadeType.ALL)
    private MealPlan mealPlan;
    
    public MealPlanItem(Meal mealItem, MealPlan mealPlan){
        this.mealItem = mealItem;
        this.mealPlan = mealPlan;
    }
    
    public MealPlanItem(){}
}
