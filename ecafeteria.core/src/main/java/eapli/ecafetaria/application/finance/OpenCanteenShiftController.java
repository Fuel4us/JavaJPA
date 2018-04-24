/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafetaria.application.finance;

import eapli.ecafetaria.domain.finance.Shift;
import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.framework.application.Controller;
import java.util.Date;

/**
 *
 * @author Josué Lapa
 */
public class OpenCanteenShiftController implements Controller{
    
   // private final CanteenShiftRepository canteenShiftRepository = new PersistenceContext.repositories().CanteenShiftRepository();
   //private final MealTypeRepository mealTypeRepository = new PersistenceContext.repositories().MealTypeRepository();  
    
    Shift newShift; //USAR FACTORY???
    
    public void setUpShiftInformation(Date shiftDate, MealType mealType){
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.SELECT_MEAL);
        
        //Fará sentido existir a classe ShiftDate?
        newShift = new Shift(shiftDate, mealType);
        
        //canteenShiftRepository.
        
    }
    
    public boolean verifyShift(){
        //verificar se o shift ja existe
        return false;
        
    }
    
    public void saveShift(){
        //guarda o shift no repositorio
    }
}
