/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.cafeteriauser;

import eapli.ecafeteria.domain.dishes.Allergen;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author pedromonteiro
 */
@Entity
public class NutritionalProfile implements  Serializable{

    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue
    private Long id;
    
    @Column(name = "max_salt_quantity")
    private Salt maxSaltQuantity;
    
    @Column(name = "max_calorie_quantity")
    private Calorie maxCalorieQuantity;
    
    @Column(name = "allergen_list")
    @OneToMany(cascade = CascadeType.ALL)
    private final Set<Allergen> allergenList;
    
    protected NutritionalProfile(){
        allergenList = new HashSet<>();
        maxSaltQuantity = new Salt();
        maxCalorieQuantity = new Calorie();
    }
    
    /**
     * Add an allergen to user's allergen list.
     * @param allergen allergen to add
     * @return true if added, throws an exception if cannot add it
     */
    public boolean addAllergen(Allergen allergen){
        if(!allergenList.add(allergen)){
            throw new IllegalArgumentException("The allergen already exist in user's list");
        } 
        return true;
    }
    
    /**
     * Remove an allergen from user' allergen list
     * @param allergen  allergen to remove
     * @return true if removed, false if not;
     */
    public boolean removeAllergen(Allergen allergen){
        return allergenList.remove(allergen);
    }
    
    /**
     * Returns the user's allergen list
     * @return Iterable of user's allergens
     */
    public Iterable<Allergen> userAllergens(){
        return allergenList;
    }
    
    public void changeMaxSaltQuantity(Salt maxSaltQuantity) {
        this.maxSaltQuantity = maxSaltQuantity;
    }

    public void changeMaxCalorieQuantity(Calorie maxCalorieQuantity) {
        this.maxCalorieQuantity = maxCalorieQuantity;
    }

    public Salt getMaxSaltQuantity() {
        return maxSaltQuantity;
    }

    public Calorie getMaxCalorieQuantity() {
        return maxCalorieQuantity;
    }
}
