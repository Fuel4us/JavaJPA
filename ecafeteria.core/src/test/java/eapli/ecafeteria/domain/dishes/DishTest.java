//package eapli.ecafeteria.domain.dishes;
//
//import eapli.ecafeteria.domain.dishes.Allergen;
//import eapli.ecafeteria.domain.dishes.NutricionalInfo;
//import eapli.ecafeteria.domain.dishes.DishType;
//import eapli.ecafeteria.domain.dishes.Dish;
//import eapli.ecafeteria.domain.kitchen.Material;
//import eapli.framework.domain.Designation;
//import eapli.framework.domain.money.Money;
//import java.util.Set;
//import org.junit.Before;
//import org.junit.Test;
//import static org.junit.Assert.*;
//
///**
// *
// * @author AntónioRocha
// */
//public class DishTest {
//
//    private DishType peixe;
//    private NutricionalInfo aNutricionalInfo;
//    private final Designation prego = Designation.valueOf("Prego");
//    private Set<Allergen> allergList;
//    private Set<Material> ingredientsList;
//    private Allergen allerg1;
//    private Allergen allerg2;
//    private Material mat1;
//    private Material mat2;
//
//    public DishTest() {
//    }
//
//    @Before
//    public void setUp() {
//        peixe = new DishType("Peixe", "Peixe");
//        aNutricionalInfo = new NutricionalInfo(10, 11);
//        allerg1 = new Allergen("cr", "crustaceos");
//        allerg2 = new Allergen("pe", "peixes");
//        mat1 = new Material("eggs", "Chicken or Duck Eggs");
//        mat2 = new Material("oil", "Olive oil");
//        allergList.add(allerg1);
//        allergList.add(allerg2);
//        ingredientsList.add(mat1);
//        ingredientsList.add(mat2);
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void testDishTypeMustNotBeNull() {
//        System.out.println("must have an Dish type");
//        @SuppressWarnings("unused")
//        final Dish instance = new Dish(null, prego, aNutricionalInfo, Money.euros(8), allergList, ingredientsList);
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void testNameMustNotBeNull() {
//        System.out.println("must have an name");
//        @SuppressWarnings("unused")
//        final Dish instance = new Dish(peixe, null, aNutricionalInfo, Money.euros(5), allergList, ingredientsList);
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void testNutricionalInfoMustNotBeNull() {
//        System.out.println("must have an Nutricional Info");
//        @SuppressWarnings("unused")
//        final Dish instance = new Dish(peixe, prego, null, Money.euros(7), allergList, ingredientsList);
//    }
//
//    /**
//     * Test of is method, of class Dish.
//     */
//    @Test
//    public void testIs() {
//        System.out.println("Attest 'is' method - Normal Behaviour");
//        final Dish instance = new Dish(peixe, prego, aNutricionalInfo, Money.euros(5), allergList, ingredientsList);
//        final boolean expResult = true;
//        final boolean result = instance.is(prego);
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of changeNutricionalInfoTo method, of class Dish.
//     *
//     * PRP - 29.mar.2017
//     */
//    @Test(expected = IllegalArgumentException.class)
//    public void ensureCannotChangeNutricionalInfoToNull() {
//        System.out.println("ChangeNutricionalInfoTo -New nutricional info must not be null");
//
//        final Dish Dishinstance = new Dish(peixe, prego, new NutricionalInfo(1, 1), Money.euros(7), allergList, ingredientsList);
//        Dishinstance.changeNutricionalInfoTo(null);
//    }
//
//    /**
//     * Tests of changePriceTo method, of class Dish.
//     *
//     * PRP - 29.mar.2017
//     */
//    @Test(expected = IllegalArgumentException.class)
//    public void ensureCannotChangePriceToNull() {
//        System.out.println("ChangePriceTo -New price info must not be null");
//
//        final Dish Dishinstance = new Dish(peixe, prego, new NutricionalInfo(1, 1), Money.euros(7), allergList, ingredientsList);
//        Dishinstance.changePriceTo(null);
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void ensureCannotChangePriceToNegative() {
//        System.out.println("ChangePriceTo -New price can nt be negativel");
//
//        final Dish Dishinstance = new Dish(peixe, prego, new NutricionalInfo(1, 1), Money.euros(1), allergList, ingredientsList);
//        Dishinstance.changePriceTo(Money.euros(-1));
//    }
//}
