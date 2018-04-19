/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.meals;

/**
 *
 * @author Bernardo Carreira
 */
public enum MealType {

    LUNCH, DINNER;

    /**
     * get available Meal Types
     *
     * @return all MealTypes
     */
    public static MealType[] MealTypeValues() {
        return new MealType[]{LUNCH, DINNER};
    }

}
