/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.meals;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.persistence.MealTypeRepository;
import eapli.ecafeteria.persistence.PersistenceContext;

/**
 *
 * @author Bernardo Carreira
 */
public class ListMealTypeService {
    
    private final MealTypeRepository mealTypeRepository = PersistenceContext.repositories().mealTypes();

    public Iterable<MealType> allMealTypes() {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);

        return this.mealTypeRepository.findAll();
    }

    public Iterable<MealType> activeMealTypes() {
        AuthorizationService
                .ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);

        return this.mealTypeRepository.activeMealTypes();
    }
}
