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
    
    
    @Column(name = "allergen_list")
    @OneToMany(cascade = CascadeType.ALL)
    private final Set<Allergen> allergenList;
    
    protected NutritionalProfile(){
        allergenList = new HashSet<>();
    }
    
    public boolean addAllergen(Allergen allergen){
        if(!allergenList.add(allergen)){
            throw new IllegalArgumentException("The allergen already exist in user's list");
        } 
        return true;
    }
    
    public boolean removeAllergen(Allergen allergen){
        return allergenList.remove(allergen);
    }
    
    public Iterable<Allergen> userAllergens(){
        return allergenList;
    }

    
}
