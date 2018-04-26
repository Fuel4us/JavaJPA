/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.kitchen;

import eapli.ecafeteria.domain.menus.Menu;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author joaotiagow
 */
public class MealPlanTest {
    
    public MealPlanTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of changeHeuristicInUse method, of class MealPlan.
     */
    @Test
    public void testChangeHeuristicInUseToNull() {
        System.out.println("changeHeuristicInUseToNull");
        Heuristic newHeuristic = null;
        boolean expResult = false;
        boolean result = MealPlan.changeHeuristicInUse(newHeuristic);
        
        assertEquals(expResult, result);
    }
    
    @Test
    public void testChangeHeuristicInUseToHeuristicA(){
        System.out.println("changeHeuristicInUseToHeuristicA");
        Heuristic newHeuristic = new HeuristicA();
        boolean expResult = true;
        boolean result = MealPlan.changeHeuristicInUse(newHeuristic);
        
        assertEquals(expResult, result);
    }
}
