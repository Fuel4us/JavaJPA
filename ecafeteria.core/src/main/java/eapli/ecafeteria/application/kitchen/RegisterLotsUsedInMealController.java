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
import eapli.ecafeteria.persistence.MealLotRepository;
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

    public Lot registerLot(int lotCode, Material ingredientCode, int quantity)
            throws DataIntegrityViolationException, DataConcurrencyException {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);

        final Lot lot = new Lot(lotCode, ingredientCode, quantity);
        return this.lRepository.save(lot);
    }

    public MealLot registerMealLot(Meal meal, Lot lot) 
            throws DataIntegrityViolationException, DataConcurrencyException {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);
        
        final MealLot mealLot = new MealLot(meal, lot);
        return this.mlRepository.save(mealLot);
    }
}
