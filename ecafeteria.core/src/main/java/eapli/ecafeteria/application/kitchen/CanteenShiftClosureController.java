package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.finance.POS;
import eapli.ecafeteria.domain.kitchen.CanteenShift;
import eapli.ecafeteria.persistence.CanteenShiftRepository;
import eapli.ecafeteria.persistence.POSRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CanteenShiftClosureController implements Controller {

    private final POSRepository posRepository = PersistenceContext.repositories().POS();
    private final CanteenShiftRepository csRepository = PersistenceContext.repositories().canteenShift();

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
            csRepository.save(currentCanteenShift);
        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            Logger.getLogger(CanteenShiftClosureController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        return true;
    }

}
