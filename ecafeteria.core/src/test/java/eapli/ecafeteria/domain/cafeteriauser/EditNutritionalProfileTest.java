/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.cafeteriauser;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Tiago Babo (1160760)
 */
public class EditNutritionalProfileTest {
    
    NutritionalProfile profile;
    
    public EditNutritionalProfileTest(){
        profile = new NutritionalProfile();
        
        Salt salt = new Salt(200);
        profile.changeMaxSaltQuantity(salt);
        
        Calorie cal = new Calorie(1000);
        profile.changeMaxCalorieQuantity(cal);
    }
    
    @Test
    public void ensureSaltQuantityIsChanged(){
        int expected = 120;
        
        profile.changeMaxSaltQuantity(new Salt(120));
        
        assertEquals(expected, profile.getMaxSaltQuantity().saltQuantity());
    }
    
    @Test
    public void ensureCalorieQuantityIsChange(){
        int expected = 4000;
        
        profile.changeMaxCalorieQuantity(new Calorie(4000));
        
        assertEquals(expected, profile.getMaxCalorieQuantity().calorieQuatity());
    }
}
