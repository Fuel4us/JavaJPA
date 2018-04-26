/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafetaria.application.finance;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.framework.application.Controller;
import java.util.Date;

/**
 *
 * @author Josué Lapa
 */
public class OpenShiftController implements Controller {

    //private final ShiftRepository shiftRepository = new PersistenceContext.repositories().ShiftRepository();
    public boolean verifyShift(Date shiftDay, MealType mealType) {
        //verificar se já existe para a refeição do dia escolhido
//        if(shiftRepository.checkShift(shiftDay, mealType)){
//            return true;
//        }
        return false;
    }
    
    

    public void saveShift(Date shiftDay, MealType mealType) {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.SELECT_MEAL);
        //guarda o shift no repositorio
        //shiftRepository.addShift(shiftDay, mealType);

    }
}
