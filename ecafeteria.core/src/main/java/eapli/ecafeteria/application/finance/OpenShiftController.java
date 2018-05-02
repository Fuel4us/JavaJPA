/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.finance;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.finance.Shift;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.ShiftRepository;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Josué Lapa
 */
public class OpenShiftController implements Controller {

    private final ShiftRepository shiftRepository = PersistenceContext.repositories().shift();

    public boolean verifyShift(Date shiftDay, MealType mealType) {
        //verificar se já existe para a refeição do dia escolhido.
        return shiftRepository.checkShift(shiftDay, mealType);
    }

    public void saveShift(Date shiftDay, MealType mealType) {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.SELECT_MEAL);
        //guarda o shift no repositorio
        try {
            shiftRepository.save(new Shift(shiftDay, mealType));
        } catch (DataConcurrencyException | DataIntegrityViolationException e) {
            Logger.getLogger(OpenShiftController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
