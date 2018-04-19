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
public class ChangeMealTypeController implements Controller {
    
    private final ListMealTypeService svc = new ListMealTypeService();

    private final MealTypeRepository repo = PersistenceContext.repositories().mealTypes();

    public MealType changeMealType(MealType theMealType, String newDescription)
            throws DataConcurrencyException, DataIntegrityViolationException {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);

        if (theMealType == null) {
            throw new IllegalArgumentException();
        }

        theMealType.changeDescriptionTo(newDescription);

        return this.repo.save(theMealType);
    }

    /**
     * in the context of this use case only active dish types are meaningful.
     *
     * @return
     */
    public Iterable<MealType> listMealTypes() {
        return this.svc.activeMealTypes();
    }
    
}
