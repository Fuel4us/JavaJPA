/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.cafeteriauser;

import eapli.ecafeteria.domain.dishes.Allergen;
import java.util.Set;

/**
 *
 * @author pedromonteiro
 */
class NutritionalProfile {
    
    private Set<Allergen> allergenList;
    
    public boolean addAllergen(Allergen allergen){
        return allergenList.add(allergen);
    }
    
    public boolean removeAllergen(Allergen allergen){
        return allergenList.add(allergen);
    }
    
}
