/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.kitchen.Material;
import eapli.ecafeteria.domain.kitchen.Lot;
import eapli.ecafeteria.domain.kitchen.MealLot;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.LotRepository;
import eapli.ecafeteria.persistence.MaterialRepository;
import eapli.ecafeteria.persistence.MealLotRepository;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

/**
 *
 * @author Pedro Rodrigues (1140572)
 */
public class RegisterLotsUsedInMealController implements Controller {

    private final LotRepository lRepository = PersistenceContext.repositories().lots();
    private final MealLotRepository mlRepository = PersistenceContext.repositories().mealLots();
    private final MaterialRepository matRepository = PersistenceContext.repositories().materials();
    private final MealRepository mealRepository = PersistenceContext.repositories().meals();

    public Lot registerLot(int lotCode, String ingredientCode, int quantity)
            throws DataIntegrityViolationException, DataConcurrencyException {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);
        Material ingredient = matRepository.findByAcronym(ingredientCode).get();
        final Lot lot = new Lot(lotCode, ingredient, quantity);
        return this.lRepository.save(lot);
    }

    public MealLot registerMealLot(long mealCode, int lotCode)
            throws DataIntegrityViolationException, DataConcurrencyException {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);
        Lot lot = lRepository.findByLotCode(lotCode).get();
        Meal meal = mealRepository.findById(mealCode).get();
        final MealLot mealLot = new MealLot(meal, lot);
        return this.mlRepository.save(mealLot);
    }
    
    public void listMeals(){
        for (Meal meal : mealRepository.findAll()) {
            System.out.println(meal.getId() + " - " + meal.toString());
        }
    }
    
    public void listMaterials(){
        for (Material material : matRepository.findAll()) {
             System.out.println(material.id() + " - " + material.toString());
        }
    }
    
    public boolean checkMaterial(String ingredientCode) {
        return matRepository.findByAcronym(ingredientCode).isPresent();
    }

    public boolean checkMeal(long mealCode) {
        return mealRepository.findById(mealCode).isPresent();
    }

}
