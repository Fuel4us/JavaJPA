/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.kitchen;

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
public class MealPlanItemQuantity {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private int itemQuantity;
    
    @OneToOne
    private MealPlanItem item;
    
    public MealPlanItemQuantity(int itemQuantity, MealPlanItem item){
        this.itemQuantity = itemQuantity;
        this.item = item;
    }
    
    public MealPlanItemQuantity(){}

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }
    
}
