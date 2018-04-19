/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.dto;

import eapli.framework.dto.DTO;

/**
 *
 * @author Bernardo Carreira
 * 
 * a pure DTO for meal
 */
@SuppressWarnings("squid:ClassVariableVisibilityCheck")
public class MealDTO implements DTO{
    
    public MealDTO(String mealTypeAcronym, String mealTypeDescription, String name, String dishTypeAcronym, String dishTypeDescription,
            Integer calories2, Integer salt2, double amount, String currency2, boolean active2) {
        this.mealTypeAcronym=mealTypeAcronym;
        this.mealTypeDescription=mealTypeDescription;
        this.name = name;
        this.dishTypeAcronym = dishTypeAcronym;
        this.dishTypeDescription = dishTypeDescription;
        calories = calories2;
        salt = salt2;
        price = amount;
        this.currency = currency2;
        this.active = active2;
    }

    public String mealTypeAcronym;
    public String mealTypeDescription;
    public String dishTypeAcronym;
    public String dishTypeDescription;

    public String name;

    public int calories;
    public int salt;

    public double price;
    public String currency;

    public boolean active;
    
}
