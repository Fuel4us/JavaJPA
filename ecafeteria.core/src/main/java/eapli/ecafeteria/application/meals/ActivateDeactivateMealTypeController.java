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
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

/**
 *
 * @author Bernardo Carreira
 */
public class ActivateDeactivateMealTypeController implements Controller {
    
    private final ListMealTypeService svc = new ListMealTypeService();
    private final MealTypeRepository dishTypeRepository = PersistenceContext.repositories().mealTypes();

    public Iterable<MealType> allMealTypes() {
        return this.svc.allMealTypes();
    }

    public void changeDishTypeState(MealType mType) throws DataConcurrencyException, DataIntegrityViolationException {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);
        if (mType == null) {
            throw new IllegalArgumentException();
        }
        mType.toogleState();
        this.dishTypeRepository.save(mType);
    }
}
