package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.finance.POS;
import eapli.ecafeteria.domain.kitchen.CanteenShift;
import eapli.ecafeteria.domain.kitchen.Execution;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.*;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.util.DateTime;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CanteenShiftClosureController implements Controller {

    private Meal meal;
    private final POSRepository posRepository = PersistenceContext.repositories().POS();
    private final CanteenShiftRepository csRepository = PersistenceContext.repositories().canteenShift();
    private final ExecutionRepository executionRepository = PersistenceContext.repositories().execution();
    private final MealRepository mealRepository = PersistenceContext.repositories().meals();
    private final MealPlanItemQuantityRepository mealPlanItemQuantityRepository = PersistenceContext.repositories().mealplanitemquantities();

    /**
     * It does not return false if there are no open POS 
     * @return true if sucess on closing the current canteen shift
     */
    public boolean canteenShiftClosure() {
        
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);
        
        List<POS> listPOS = posRepository.findOpenToClose();
        
        if(!listPOS.isEmpty()){
            try {
                for(POS pos : listPOS){
                    posRepository.save(pos);
                }
            } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
                Logger.getLogger(CanteenShiftClosureController.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        
        CanteenShift currentCanteenShift = csRepository.close();
        
        if(currentCanteenShift == null)
            return false;
        
        try {

            Date date = new java.sql.Date(Calendar.getInstance().getTimeInMillis());

           
            for (Meal meal:
                 mealRepository.findMealByDate(date)) {
                Execution execution;
                Iterable<Execution>it = executionRepository.getExecutionByMeal(meal);
                if(!it.iterator().hasNext()){
                    System.out.println("Execution of the meal not found");
                }else {
                    int mealsPicked = executionRepository.getPickedMeals(meal);
                    int mealsActuallyDone = executionRepository.getMealActuallyServed(meal);

                    execution = it.iterator().next();
                    execution.getLeftover().setQuantity(mealsActuallyDone - mealsPicked);
                    executionRepository.save(execution);
                }
            }

            csRepository.save(currentCanteenShift);
        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            Logger.getLogger(CanteenShiftClosureController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        return true;
    }

    public void setMeal(Meal meal){
        this.meal=meal;
    }

}
