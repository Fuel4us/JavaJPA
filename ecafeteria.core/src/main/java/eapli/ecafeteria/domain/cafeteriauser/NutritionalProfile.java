/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.cafeteriauser;

import eapli.ecafeteria.domain.dishes.Allergen;
import eapli.framework.domain.ddd.ValueObject;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;

/**
 *
 * @author pedromonteiro
 */
@Embeddable
public class NutritionalProfile implements ValueObject, Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Column(name = "allergen_list")
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Allergen> allergenList;
    
    public boolean addAllergen(Allergen allergen){
        return allergenList.add(allergen);
    }
    
    public boolean removeAllergen(Allergen allergen){
        return allergenList.remove(allergen);
    }
    
    public Iterable<Allergen> userAllergens(){
        return allergenList;
    }
    
}
