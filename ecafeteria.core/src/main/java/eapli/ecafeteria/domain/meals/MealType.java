/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.meals;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Bernardo Carreira
 * @EDIT Pedro Alves
 */
public enum MealType {

    LUNCH, DINNER;

    private static Set<MealType> mealsType = new HashSet<>();

    /**
     * get available Meal Types
     *
     * @return all MealTypes
     */
    public static Set<MealType> MealTypeValues() {
        mealsType.add(LUNCH);
        mealsType.add(DINNER);
        return mealsType;
    }

}
