/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.kitchen;

import eapli.ecafeteria.domain.menus.Menu;

import java.util.ArrayList;
import java.util.Date;
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

    private Menu menu = new Menu(new Date(), new Date());
    private List<Integer> dishes = new ArrayList<>();

    private MealPlan meal = new MealPlan(menu, dishes);
    private MealPlan meal2 = new MealPlan(menu, dishes);
    
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
        HeuristicConfiguration newHeuristic = null;
        boolean expResult = false;
        boolean result = MealPlan.changeHeuristicInUse(newHeuristic);
        
        assertEquals(expResult, result);
    }
    
    @Test
    public void testChangeHeuristicInUseToHeuristicA(){
        System.out.println("changeHeuristicInUseToHeuristicA");
        HeuristicConfiguration newHeuristic = new HeuristicConfiguration(new HeuristicA());
        boolean expResult = true;
        boolean result = MealPlan.changeHeuristicInUse(newHeuristic);
        
        assertEquals(expResult, result);
    }

    @Test
    public void changeState() {
        meal.changeState(meal);
        boolean expResult = true;
        boolean result = meal.isClosed();

        assertEquals(expResult, result);
    }
}
